package prism6.com.easypark.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import prism6.com.easypark.adapter.HomeAdapter
import prism6.com.easypark.databinding.FragmentHomeBinding
import prism6.com.infiniteimgur.uilitiy.Resource

class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    lateinit var loadingBox : View
    lateinit var recyclerView: RecyclerView
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
        loadingBox = binding.loadingBox
        recyclerView = binding.fullscreenContent

        homeViewModel.isLoading.observe(viewLifecycleOwner, {
            if(it){
                loadingBox.visibility = View.VISIBLE
            }else{
                loadingBox.visibility = View.GONE
            }
        })

        fetchCarpark()

        return root
    }

    fun fetchCarpark(){
        homeViewModel.carpark.observe(this, {
            when(it.status){
                Resource.Status.LOADING -> homeViewModel.isLoading.value = true
                Resource.Status.ERROR -> homeViewModel.isLoading.value = false
                Resource.Status.SUCCESS -> {
                    homeViewModel.isLoading.value = false
                    if(adapter == null) {
                        adapter = HomeAdapter(homeViewModel)
                        recyclerView.adapter = adapter
                    }
                    adapter!!.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}