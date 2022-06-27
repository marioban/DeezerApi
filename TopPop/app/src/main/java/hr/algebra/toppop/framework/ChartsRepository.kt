package hr.algebra.toppop.framework

import hr.algebra.toppop.models.Data
import hr.algebra.toppop.models.DataX
import hr.algebra.toppop.network.NetworkLayer
import hr.algebra.toppop.network.SimpleResponse
import retrofit2.Call

class ChartsRepository {


    suspend fun getChartDetails(): Data<DataX>? {
        val request = NetworkLayer.apiClient.getData()

        if (request.failed){
            return null
        }

        if (!request.isSuccessful){
            return null
        }

        return request.body
    }
}