package prism6.com.easypark.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import prism6.com.infiniteimgur.model.ImagesTypeConverter

data class CarparkResponse(
    val car_park: List<CarParkInfo>
)

@Entity(tableName = "CarPark")
data class CarParkInfo(
    val carpark_photo: String,
    val contactNo: String,
    val displayAddress_en: String,
    val displayAddress_sc: String,
    val displayAddress_tc: String,
    val district_en: String,
    val district_sc: String,
    val district_tc: String,
    val height: Int,
    val latitude: Double,
    val longitude: Double,
    val name_en: String,
    val name_sc: String,
    val name_tc: String,
    val opening_status: String,
    @PrimaryKey
    val park_id: String,
    val remark_en: String,
    val remark_sc: String,
    val remark_tc: String,
    val website_en: String,
    val website_sc: String,
    val website_tc: String
)