package ru.vincetti.test.cashpointssample.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import ru.vincetti.test.cashpointssample.R
import ru.vincetti.test.cashpointssample.models.CashPoint
import ru.vincetti.test.cashpointssample.models.ListViewModel
import ru.vincetti.test.cashpointssample.utils.PermissionUtils

class MapFragment : Fragment(),
    OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap

    private val viewModel by activityViewModels<ListViewModel>()

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

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerClickListener(this)

        with(map.uiSettings) {
            isZoomControlsEnabled = true
            isMapToolbarEnabled = false
        }
        addMarkers()
        enableMyLocation()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        view?.let {
            Snackbar.make(it, "onMarkerClick", Snackbar.LENGTH_SHORT).show()
        }
        return true
    }

    private fun addMarkers() {
        val list = viewModel.list
        list.forEach { point ->
            addMarker(point)
        }
    }

    private fun addMarker(point: CashPoint) {
        map.addMarker(MarkerOptions().position(point.latLong).title(point.name))
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
}
