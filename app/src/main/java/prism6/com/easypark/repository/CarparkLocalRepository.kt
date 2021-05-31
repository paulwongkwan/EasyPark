package prism6.com.easypark.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import prism6.com.easypark.application.mApplication
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.model.CarParkStatus
import prism6.com.easypark.room.CarParkDB
import prism6.com.easypark.room.VacancyDB

class CarparkLocalRepository constructor() {

    lateinit var carParkDB: CarParkDB
    lateinit var vacancyDB: VacancyDB

    val context : Context = mApplication.instance

    fun initializeCarkParkDB(context: Context): CarParkDB {
        return CarParkDB.getDataseClient(context)
    }

    fun initializeVacancyDB(context: Context): VacancyDB {
        return VacancyDB.getDataseClient(context)
    }

    fun insertAllCarpark(carparkInfos : List<CarParkInfo>) {

        carParkDB = initializeCarkParkDB(context)

        CoroutineScope(IO).launch {
            carParkDB!!.carparkDao().InsertAll(carparkInfos)
        }
    }

    fun insertAllVacancy(CarParkStatus : List<CarParkStatus>) {

        vacancyDB = initializeVacancyDB(context)

        CoroutineScope(IO).launch {
            vacancyDB!!.vacancyDao().InsertAll(CarParkStatus)
        }
    }

    fun getCarpark(id: String): LiveData<CarParkInfo> {

        carParkDB = initializeCarkParkDB(context)

        return carParkDB!!.carparkDao().getDetail(id)
    }

    fun getAllCarpark(): LiveData<List<CarParkInfo>> {

        carParkDB = initializeCarkParkDB(context)

        return carParkDB!!.carparkDao().getDetails()
    }
}