package prism6.com.infiniteimgur.model

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import prism6.com.easypark.model.VehicleType
import java.lang.reflect.Type

class ViehicleTypeConverter {
    @TypeConverter
    fun ListToJson(Images: List<VehicleType?>?): String? {
        if (Images == null) return null
        val type: Type = object : TypeToken<List<VehicleType?>?>() {}.type
        val json: String = Gson().toJson(Images, type)
        Log.i("JSON", "toJson: $json")
        return if (Images.isEmpty()) null else json
    }

    @TypeConverter
    fun JsonToList(json: String?): List<VehicleType>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<VehicleType?>?>() {}.type
        return gson.fromJson<List<VehicleType>>(json, type)
    }
}