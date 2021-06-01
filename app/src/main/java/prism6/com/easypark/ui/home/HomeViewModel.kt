package prism6.com.easypark.ui.home

import android.app.Activity
import android.app.ActivityOptions
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import prism6.com.easypark.R
import prism6.com.easypark.component.DaggerRepositotyComponent
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.repository.CarparkRepository
import prism6.com.easypark.ui.activity.CarparkDetailActivity
import prism6.com.infiniteimgur.uilitiy.Resource
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject lateinit var repository: CarparkRepository
    var carpark: LiveData<Resource<List<CarParkInfo>>>
    var isLoading = MutableLiveData(true)

    init {
        DaggerRepositotyComponent.create().inject(this)
        carpark = repository.getCarPark()
    }

    fun openDetail(view: View, carParkInfo: CarParkInfo){
        view.transitionName = "open_detail"
        val activity = view.context as Activity

        val intent = Intent(activity, CarparkDetailActivity::class.java)
        intent.putExtra("park_id", carParkInfo.park_id)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            view,
            "open_detail"
        )
        activity.startActivity(intent, options.toBundle())
    }

    companion object {
        @JvmStatic
        @BindingAdapter("mainImage")
        fun loadImage(view: ImageView, carParkInfo: CarParkInfo) {
            try {
                Glide.with(view.context)
                    .load(carParkInfo.carpark_photo.replace("http:", "https:"))
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(view)
            }catch (e : Exception){
            }
        }
    }
}