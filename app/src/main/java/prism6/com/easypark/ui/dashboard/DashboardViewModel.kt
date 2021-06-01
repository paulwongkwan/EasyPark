package prism6.com.easypark.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import prism6.com.easypark.component.DaggerRepositotyComponent
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.repository.CarparkRepository
import prism6.com.infiniteimgur.uilitiy.Resource
import javax.inject.Inject

class DashboardViewModel : ViewModel() {
    @Inject
    lateinit var repository: CarparkRepository
    var carpark: LiveData<Resource<List<CarParkInfo>>>

    init {
        DaggerRepositotyComponent.create().inject(this)
        carpark = repository.getCarPark()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}