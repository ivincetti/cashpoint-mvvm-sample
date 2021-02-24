package ru.vincetti.test.cashpointssample.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import ru.vincetti.test.cashpointssample.App
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.databinding.FragmentMapBinding
import ru.vincetti.test.cashpointssample.mvvm.ListViewModel
import ru.vincetti.test.cashpointssample.mvvm.ListViewModelFactory
import ru.vincetti.test.cashpointssample.mvvm.MainViewModel
import ru.vincetti.test.cashpointssample.ui.point.PointFragment
import ru.vincetti.test.cashpointssample.utils.LoadingDialog
import ru.vincetti.test.cashpointssample.utils.PermissionUtils
import ru.vincetti.test.cashpointssample.utils.imageBaseUrl
import javax.inject.Inject

class MapFragment : Fragment(),
    OnMapReadyCallback,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnMarkerClickListener {

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private lateinit var map: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel by activityViewModels<ListViewModel> { viewModelFactory }
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
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
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        viewModel.getMapStart()
        mapFragment?.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerClickListener(this)
        map.setOnCameraIdleListener(this)

        with(map.uiSettings) {
            isZoomControlsEnabled = true
            isMapToolbarEnabled = false
        }
        enableMyLocation()

        viewModel.mapReady()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        viewModel.onMarkerClicked(marker)

        return true
    }

    override fun onCameraIdle() {
        viewModel.checkArea(map)
    }

    private fun initView() {
        binding.detailsSheet.detailsSheetRoot.apply {
            bottomSheetBehavior = BottomSheetBehavior.from(this)

            this.setOnClickListener {
                viewModel.onDetailsSheetClicked()
            }
        }
    }

    private fun initObservers() {
        viewModel.needToBlockUser.observe(viewLifecycleOwner) {
            if (it) blockUI() else unBlockUI()
        }
        viewModel.needToShowBottomSheet.observe(viewLifecycleOwner) {
            showDetailSheet(it.name, it.address, it.image)
        }
        viewModel.needToNavigateToDetails.observe(viewLifecycleOwner) {
            navigateToPointFragment(it)
        }
        viewModel.needToShowNetworkError.observe(viewLifecycleOwner) {
            if (it) showError()
        }
        viewModel.points.observe(viewLifecycleOwner) {
            it?.let {
                addMarkers(it)
            }
        }
        mainViewModel.permissionGranted.observe(viewLifecycleOwner) {
            if (it) enableMyLocation()
        }

        viewModel.mapPoint.observe(viewLifecycleOwner) {
            map.moveCamera(newCameraPosition(it))
        }
    }

    private fun addMarkers(points: List<CashPointDetails>) {
        points.forEach { point ->
            addMarker(point.id, LatLng(point.latitude, point.longitude))
        }
    }

    private fun addMarker(id: String, latLng: LatLng) {
        val marker = map.addMarker(MarkerOptions().position(latLng))
        marker.tag = id
    }

    private fun enableMyLocation() {
        if (
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
        } else {
            PermissionUtils.requestPermission(
                requireActivity(),
                PermissionUtils.LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    private fun showDetailSheet(name: String, info: String, pictureUrl: String) {

        binding.detailsSheet.detailsSheetName.text = name
        binding.detailsSheet.detailsSheetInfo.text = info
        Glide.with(this)
            .load("$imageBaseUrl$pictureUrl")
            .centerCrop()
            .into(binding.detailsSheet.detailsSheetPicture)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun blockUI() {
        requireActivity().supportFragmentManager.let {
            LoadingDialog.newInstance().show(it, LoadingDialog.FRAGMENT_TAG)
            it.executePendingTransactions()
        }
    }

    private fun unBlockUI() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(LoadingDialog.FRAGMENT_TAG)
        val dialog = fragment as? DialogFragment
        dialog?.dismiss()
    }

    private fun showError() {
        Snackbar.make(
            requireView(),
            R.string.main_connectivity_error,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.main_connectivity_action_repeat) { viewModel.checkArea(map) }
            .show()
    }

    private fun navigateToPointFragment(id: String) {
        val args = Bundle().apply {
            putString(PointFragment.EXTRA_POINT_ID_KEY, id)
        }
        findNavController().navigate(R.id.action_global_pointFragment, args)
    }
}
