package ru.vincetti.test.cashpointssample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.databinding.FragmentMainBinding
import ru.vincetti.test.cashpointssample.mvvm.MainViewModel
import ru.vincetti.test.cashpointssample.utils.NetworkUtils

class MainFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetwork()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkNetwork() {
        mainViewModel.setNetwork(
            NetworkUtils(requireContext()).isAvailable()
        )
    }

    private fun initObservers() {
        mainViewModel.isNetworkConnected.observe(viewLifecycleOwner) {
            if (it) {
                initPager()
            } else {
                showError()
            }
        }
    }

    private fun initPager() {
        binding.mainPager.adapter = MapListAdapter(this)
        binding.mainPager.isUserInputEnabled = false

        TabLayoutMediator(binding.mainTabLayout, binding.mainPager) { tab, position ->
            tab.setText(
                when (position) {
                    0 -> R.string.main_map_title
                    1 -> R.string.main_list_title
                    else -> error("Unknown Page")
                }
            )
        }.attach()
    }

    private fun showError() {
        Snackbar.make(
            requireView(),
            R.string.main_connectivity_error,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.main_connectivity_action_exit) { requireActivity().finish() }
            .show()
    }
}
