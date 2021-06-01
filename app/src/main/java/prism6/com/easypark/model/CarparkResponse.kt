package prism6.com.easypark.model

import android.location.Location
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
){
    fun location() : Location{
        var location = Location("EasyCarPark")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    fun distanceTo(location: Location) : Float {
        return location().distanceTo(location)
    }

    fun getInfo() : String{
        try{
            var str = getVacancy()
            str += "\n\n" + displayAddress_tc +
                    "\n\n" + remark_tc +
                    "\n\n" + website_tc

            return str.trim()
        }catch (e : java.lang.Exception){
            return "0"
        }
    }

    fun getVacancy() : String {
        try{
            var str = ""
            for(c in vehicle_type!!){
                str += c.type + ": " + c.service_category[0].vacancy + " (" + c.service_category[0].category + ")\n"
            }
            return str.trim()
        }catch (e : java.lang.Exception){
            return "0"
        }
    }
}