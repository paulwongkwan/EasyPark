package prism6.com.easypark.repository

import dagger.Module
import prism6.com.easypark.model.CarparkResponse
import prism6.com.infiniteimgur.uilitiy.Resource

@Module
class CarparkRemoteRepository : RemoteRepository() {
    suspend fun getCarPark() = getResult { apiService.carPark() }
    suspend fun getVacancy() = getResult { apiService.vacancy() }
    suspend fun getAll(): Resource<CarparkResponse> {
        var carpark = getCarPark()
        var vacancy = getVacancy()

        var carparkinfo = carpark.data!!.car_park
        var vacancyinfo = vacancy.data!!.car_park

        for(c in carparkinfo){
            var tmp = vacancyinfo.find { it.park_id.equals(c.park_id) }

            if(tmp!=null)
                c.vehicle_type = tmp.vehicle_type
        }

        return carpark;
    }
}