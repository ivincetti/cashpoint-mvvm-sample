package ru.vincetti.test.cashpointssample.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.vincetti.test.cashpointssample.App
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.core.data.CashPoint
import ru.vincetti.test.cashpointssample.databinding.FragmentListBinding
import ru.vincetti.test.cashpointssample.mvvm.ListViewModel
import ru.vincetti.test.cashpointssample.mvvm.ListViewModelFactory
import ru.vincetti.test.cashpointssample.ui.point.PointFragment
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel by activityViewModels<ListViewModel> { viewModelFactory }

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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
        viewModel.points.observe(viewLifecycleOwner) {
            it?.let {
                initAdapter(it)
            }
        }
        viewModel.needToNavigateToDetails.observe(viewLifecycleOwner) {
            navigateToPointFragment(it)
        }
    }

    private fun initAdapter(list: List<CashPoint>) {
        val adapter = PointsListAdapter(list) { id ->
            viewModel.clickOnPointInList(id)
        }

        binding.list.adapter = adapter
    }

    private fun navigateToPointFragment(id: String) {
        val args = Bundle().apply {
            putString(PointFragment.EXTRA_POINT_ID_KEY, id)
        }
        findNavController().navigate(R.id.action_global_pointFragment, args)
    }
}
