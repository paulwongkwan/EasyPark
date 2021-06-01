package prism6.com.easypark.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import prism6.com.easypark.component.DaggerCarParkDetailComponent
import prism6.com.easypark.component.DaggerRepositotyComponent
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.repository.CarparkRepository
import prism6.com.infiniteimgur.uilitiy.Resource
import javax.inject.Inject

class CarparkDetailViewModel : ViewModel() {
    @Inject
    lateinit var repository: CarparkRepository
    lateinit var CarParkInfo: MutableLiveData<CarParkInfo>

    init {
        DaggerCarParkDetailComponent.create().inject(this)
    }
}