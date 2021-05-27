package prism6.com.easypark.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import prism6.com.easypark.model.CarParkStatus

@Database(entities = arrayOf(CarParkStatus::class), version = 1, exportSchema = false)
abstract class VacancyDB : RoomDatabase() {

    abstract fun vacancyDao() : VacancyDAO

    companion object {

        @Volatile
        private var INSTANCE: VacancyDB? = null

        fun getDataseClient(context: Context) : VacancyDB {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, VacancyDB::class.java, "CarPark_DB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}