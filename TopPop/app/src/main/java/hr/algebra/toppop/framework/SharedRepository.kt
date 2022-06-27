package hr.algebra.toppop.framework

import hr.algebra.toppop.models.Data
import hr.algebra.toppop.models.DataX
import hr.algebra.toppop.network.NetworkLayer

class SharedRepository {

    suspend fun getSongById(songId:Int): DataX? {
        val request = NetworkLayer.apiClient.getData()

        if (request.failed){
            return null
        }

        if (!request.isSuccessful){
            return null
        }
        return request.body.data[songId]
    }

}