package prism6.com.easypark.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.ui.home.HomeViewModel
import prism6.com.easypark.viewholder.CarparkViewHolder

class HomeAdapter(private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<CarparkViewHolder>() {

    var list: List<CarParkInfo>? = viewModel.carpark.value?.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarparkViewHolder {
        return CarparkViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CarparkViewHolder, position: Int) {
        val item = list!![position]
        holder.bind(viewModel, item)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}