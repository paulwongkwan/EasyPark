package prism6.com.easypark.ui.home

import android.content.ContextWrapper
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

    fun getVacancy(carParkInfo: CarParkInfo) : String {
        try{
            var str = ""
            for(c in carParkInfo.vehicle_type!!){
                str += c.type + ": " + c.service_category[0].vacancy + " (" + c.service_category[0].category + ")\n"
            }
            return str.trim()
        }catch (e : java.lang.Exception){
            return "0"
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("mainImage")
        fun loadImage(view: ImageView, carParkInfo: CarParkInfo) {
            try {
                Glide.with(view.context)
                    .load(carParkInfo.carpark_photo.replace("http:", "https:"))
                    .placeholder(R.drawable.placeholder)
                    .into(view)
            }catch (e : Exception){
            }
        }
    }
}