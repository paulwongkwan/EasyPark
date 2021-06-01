package prism6.com.easypark.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import io.nlopez.smartlocation.SmartLocation
import prism6.com.easypark.R
import prism6.com.easypark.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), OnMapReadyCallback {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mapFragment = childFragmentManager
            .findFragmentById(R.id.gmap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    SmartLocation.with(context).location().lastLocation!!.latitude,
                    SmartLocation.with(context).location().lastLocation!!.longitude
                ),
                15f
            )
        )

        dashboardViewModel.carpark.observe(this, {
            if(it.data != null) {
                it.data!!.forEach {
                    val pos = LatLng(it.latitude, it.longitude)
                    googleMap!!.addMarker(
                        MarkerOptions()
                            .position(pos)
                            .title(it.name_tc)
                    )
                }
            }
        })

        googleMap?.apply {
        }
    }
}