package prism6.com.easypark.ui.home

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.rx.ObservableFactory
import prism6.com.easypark.adapter.HomeAdapter
import prism6.com.easypark.databinding.FragmentHomeBinding
import prism6.com.infiniteimgur.uilitiy.Resource
import java.util.*
import kotlin.Comparator


class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var adapter: HomeAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = homeViewModel
        recyclerView = binding.fullscreenContent
        swipeRefreshLayout = binding.homeRefresh

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { fetchCarpark() })

        homeViewModel.isLoading.observe(viewLifecycleOwner, {
            swipeRefreshLayout.setRefreshing(it)
        })

        XXPermissions.with(this)
            .permission(Permission.ACCESS_FINE_LOCATION)
            .request(OnPermissionCallback { permissions, all -> if (all) {
                fetchCarpark()
            } else {
                Toast.makeText(activity, permissions[0], Toast.LENGTH_SHORT).show()
            }})

        return root
    }

    fun fetchCarpark() {
        homeViewModel.carpark.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> homeViewModel.isLoading.value = true
                Resource.Status.ERROR -> homeViewModel.isLoading.value = false
                Resource.Status.SUCCESS -> {
                    ObservableFactory.from(SmartLocation.with(context).location())
                        .subscribe{ myLocation ->
                            Collections.sort(
                                it.data
                            ) { o1, o2 ->
                                o1.distanceTo(myLocation)
                                    .compareTo(o2.distanceTo(myLocation))
                            }

                            if (adapter == null) {
                                adapter = HomeAdapter(homeViewModel)
                                recyclerView.adapter = adapter
                            }
                            homeViewModel.isLoading.value = false
                            adapter!!.notifyDataSetChanged()
                        }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}