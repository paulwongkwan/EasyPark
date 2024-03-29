package prism6.com.easypark.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import prism6.com.easypark.model.CarParkInfo

@Database(entities = arrayOf(CarParkInfo::class), version = 1, exportSchema = false)
abstract class CarParkDB : RoomDatabase() {

    abstract fun carparkDao() : CarparkDAO

    companion object {

        @Volatile
        private var INSTANCE: CarParkDB? = null

        fun getDataseClient(context: Context) : CarParkDB {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, CarParkDB::class.java, "CarPark_DB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}