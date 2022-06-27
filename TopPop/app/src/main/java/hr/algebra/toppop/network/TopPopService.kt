package hr.algebra.toppop.network

import hr.algebra.toppop.models.Data
import hr.algebra.toppop.models.DataX
import retrofit2.Response
import retrofit2.http.GET

interface TopPopService {

    @GET("0/tracks")
    suspend fun getData(): Response<Data<DataX>>


}