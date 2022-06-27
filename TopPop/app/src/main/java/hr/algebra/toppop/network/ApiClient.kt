package hr.algebra.toppop.network

import hr.algebra.toppop.models.Data
import hr.algebra.toppop.models.DataX
import retrofit2.Response

class ApiClient(
    private val topPopService: TopPopService
) {
    suspend fun getData():SimpleResponse<Data<DataX>>{
        return safeApiCall {topPopService.getData()}
    }

    private inline fun <T> safeApiCall(apiCall:()->Response<T>):SimpleResponse<T>{
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception){
            SimpleResponse.failure(e)
        }
    }


}