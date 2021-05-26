package prism6.com.infiniteimgur.room

import androidx.lifecycle.LiveData
import androidx.room.*
import prism6.com.easypark.model.CarParkInfo
import prism6.com.easypark.model.CarParkStatus
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.model.Image

@Dao
interface VacancyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(carParkStatus: CarParkStatus)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAll(carParkStatus: List<CarParkStatus>)

    @Query("SELECT * FROM Vacancy WHERE park_id =:id")
    fun getDetail(id: String?) : LiveData<CarParkStatus>

    @Query("SELECT * FROM Vacancy")
    fun getDetails() : LiveData<List<CarParkStatus>>
}
