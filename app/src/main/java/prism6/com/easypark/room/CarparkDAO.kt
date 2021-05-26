package prism6.com.infiniteimgur.room

import androidx.lifecycle.LiveData
import androidx.room.*
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.model.CarparkVacancy
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.model.Image

@Dao
interface CarparkDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(carParkInfo: CarParkInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAll(carParkInfos: List<CarParkInfo>)

    @Query("SELECT * FROM CarPark WHERE park_id =:id")
    fun getDetail(id: String?) : LiveData<CarParkInfo>

    @Query("SELECT * FROM CarPark")
    fun getDetails() : LiveData<List<CarParkInfo>>

    @Transaction
    @Query("SELECT * FROM CarPark WHERE park_id =:id")
    fun getCarparkVacancy(id: String?) : LiveData<CarparkVacancy>

    @Transaction
    @Query("SELECT * FROM CarPark")
    fun getCarparkVacancys() : LiveData<List<CarparkVacancy>>
}
