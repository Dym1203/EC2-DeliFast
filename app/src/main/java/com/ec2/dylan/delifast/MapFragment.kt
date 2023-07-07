package com.ec2.dylan.delifast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map : GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val fragmentMap = childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        fragmentMap.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val idatPT = LatLng(-12.0670346, -77.0360235)
        val idatW = LatLng(-12.0525183, -77.0387801)
        val idatSJM = LatLng(-12.1571261, -76.9814038)
        map.addMarker(MarkerOptions().title("IDAT - Sede Petit Thouars - Número Tel: (01) 6808890").position(idatPT).draggable(true))
        map.addMarker(MarkerOptions().title("IDAT - Sede Wilson - Número Tel: 924288288").position(idatW).draggable(true))
        map.addMarker(MarkerOptions().title("IDAT - Sede SJM - Número Tel: 924288288").position(idatSJM).draggable(true))
        val bounds = LatLngBounds.Builder()
        bounds.include(idatPT)
        bounds.include(idatW)
        bounds.include(idatSJM)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 150))
    }

}