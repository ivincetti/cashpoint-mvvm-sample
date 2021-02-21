package ru.vincetti.test.cashpointssample.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.vincetti.test.cashpointssample.App
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.models.CashPoint
import ru.vincetti.test.cashpointssample.models.ListViewModel
import ru.vincetti.test.cashpointssample.models.ListViewModelFactory
import ru.vincetti.test.cashpointssample.models.MainViewModel
import ru.vincetti.test.cashpointssample.utils.PermissionUtils
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
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
        viewModel.onMapReady()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        viewModel.onMarkerClicked(marker)

        return true
    }

    override fun onCameraIdle() {
        viewModel.checkArea(map)
    }

    private fun initView() {
        val bottomSheet = requireActivity().findViewById<ConstraintLayout>(R.id.details_sheet_root)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheet.setOnClickListener {
            viewModel.onDetailsSheetClicked()
        }
    }

    private fun initObservers() {
        viewModel.needToShowBottomSheet.observe(viewLifecycleOwner) {
            showDetailSheet(it.name, it.id.toString())
        }
        viewModel.needToNavigateToDetails.observe(viewLifecycleOwner) {
            if (it) navigateToPointFragment()
        }
        viewModel.points.observe(viewLifecycleOwner) {
            addMarkers(it)
        }
        mainViewModel.permissionGranted.observe(viewLifecycleOwner) {
            if (it) enableMyLocation()
        }
    }

    private fun addMarkers(points: List<CashPoint>) {
        points.forEach { point ->
            addMarker(point)
        }
    }

    private fun addMarker(point: CashPoint) {
        val marker = map.addMarker(MarkerOptions().position(point.latLong).title(point.name))
        marker.tag = point.id
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

    private fun showDetailSheet(name: String, info: String) {
        val bottomSheetNameView = requireActivity().findViewById<TextView>(R.id.details_sheet_name)
        val bottomSheetInfoView = requireActivity().findViewById<TextView>(R.id.details_sheet_info)

        bottomSheetNameView.text = name
        bottomSheetInfoView.text = info
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun navigateToPointFragment() {
        findNavController().navigate(R.id.action_mapFragment_to_pointFragment)
    }
}
