package prism6.com.infiniteimgur.network

import prism6.com.easypark.model.CarparkResponse
import prism6.com.easypark.model.VacancyResponse
import prism6.com.infiniteimgur.model.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/td/carpark/vacancy_all.json")
    suspend fun vacancy() : Response<VacancyResponse>

    @GET("/td/carpark/basic_info_all.json")
    suspend fun carPark() : Response<CarparkResponse>
}