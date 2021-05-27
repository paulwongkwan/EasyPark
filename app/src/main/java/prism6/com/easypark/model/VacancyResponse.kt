package prism6.com.easypark.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import prism6.com.infiniteimgur.model.ViehicleTypeConverter

data class VacancyResponse(
    val car_park: List<CarParkStatus>
)

@Entity(tableName = "Vacancy")
@TypeConverters(ViehicleTypeConverter::class)
data class CarParkStatus(
    @PrimaryKey
    val park_id: String,
    val vehicle_type: List<VehicleType>
)

data class VehicleType(
    val service_category: List<ServiceCategory>,
    val type: String
)

data class ServiceCategory(
    val category: String,
    val lastupdate: String,
    val vacancy: Int,
    val vacancy_type: String
)