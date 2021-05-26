package prism6.com.easypark.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarparkVacancy(
    @Embedded val carParkInfo: CarParkInfo,
    @Relation(
        parentColumn = "park_id",
        entityColumn = "park_id"
    )
    val carParkStatus: CarParkStatus
) {
}