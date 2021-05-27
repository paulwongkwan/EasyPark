package prism6.com.easypark.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import prism6.com.infiniteimgur.model.ViehicleTypeConverter

data class CarparkResponse(
    val car_park: List<CarParkInfo>
)

@Entity(tableName = "CarPark")
@TypeConverters(ViehicleTypeConverter::class)
data class CarParkInfo(
    @PrimaryKey
    val park_id: String,
    val carpark_photo: String,
    val contactNo: String?,
    val displayAddress_en: String,
    val displayAddress_sc: String,
    val displayAddress_tc: String,
    val district_en: String?,
    val district_sc: String?,
    val district_tc: String?,
    val height: Double,
    val latitude: Double,
    val longitude: Double,
    val name_en: String,
    val name_sc: String,
    val name_tc: String,
    val opening_status: String?,
    val remark_en: String,
    val remark_sc: String,
    val remark_tc: String,
    val website_en: String,
    val website_sc: String,
    val website_tc: String,
    var vehicle_type: List<VehicleType>?
)