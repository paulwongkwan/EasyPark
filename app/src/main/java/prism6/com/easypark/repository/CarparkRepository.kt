package prism6.com.easypark.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import prism6.com.infiniteimgur.uilitiy.Resource
import javax.inject.Inject

class CarparkRepository @Inject constructor(
    private val carparkRemoteRepository: CarparkRemoteRepository,
    private val carparkLocalRepository: CarparkLocalRepository
) {
    fun getCarPark() = get(
        db = {carparkLocalRepository.getAllCarpark()},
        call = {carparkRemoteRepository.getAll()},
        result = {carparkLocalRepository.insertAllCarpark(it.car_park)}
    )
}

fun <T, A> get(
    db: () -> LiveData<T>,
    call: suspend () -> Resource<A>,
    result: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = db.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = call.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            result(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }