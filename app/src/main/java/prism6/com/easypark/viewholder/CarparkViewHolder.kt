package prism6.com.easypark.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import prism6.com.easypark.databinding.CarparkItemLayoutBinding
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.ui.home.HomeViewModel

class CarparkViewHolder(private val binding: CarparkItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: HomeViewModel, item: CarParkInfo) {
        binding.viewModel = viewModel
        binding.carParkInfo = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CarparkViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CarparkItemLayoutBinding.inflate(layoutInflater, parent, false)
            return CarparkViewHolder(binding)
        }
    }
}