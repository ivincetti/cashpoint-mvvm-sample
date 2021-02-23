package ru.vincetti.test.cashpointssample.ui.point

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.vincetti.test.cashpointssample.App
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.databinding.FragmentPointBinding
import ru.vincetti.test.cashpointssample.mvvm.PointViewModel
import ru.vincetti.test.cashpointssample.mvvm.PointViewModelFactory
import javax.inject.Inject


class PointFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PointViewModelFactory

    private val viewModel by activityViewModels<PointViewModel> { viewModelFactory }

    private var _binding: FragmentPointBinding? = null
    private val binding: FragmentPointBinding
        get() = requireNotNull(_binding)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.onBackClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.load(it.getString(EXTRA_POINT_ID_KEY, EXTRA_POINT_ID_NO_DATA))
        }

        requireActivity().onBackPressedDispatcher
            .addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) showLoading() else hideLoading()
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            if (it) showError()
        }
        viewModel.needToNavigateToMap.observe(viewLifecycleOwner) {
            if (it) navigateToMap()
        }
        viewModel.point.observe(viewLifecycleOwner) {
            showDetailSheet(it.name, it.address, it.image)
        }
    }

    private fun showLoading() {
        binding.detailsContentLoadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.detailsContentLoadingContainer.visibility = View.GONE
    }

    private fun navigateToMap() {
        findNavController().navigateUp()
    }

    private fun showDetailSheet(name: String, info: String, pictureUrl: String) {
        binding.detailsContent.detailsName.text = name
        binding.detailsContent.detailsInfo.text = info
        Glide.with(this)
            .load(pictureUrl)
            .centerCrop()
            .into(binding.detailsContent.detailsImage)
    }

    private fun showError() {
        Snackbar.make(
            requireView(),
            R.string.details_fragment_loading_error_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    companion object {

        const val EXTRA_POINT_ID_KEY = "EXTRA_POINT_ID_KEY"
        const val EXTRA_POINT_ID_NO_DATA = "ERROR"
    }
}
