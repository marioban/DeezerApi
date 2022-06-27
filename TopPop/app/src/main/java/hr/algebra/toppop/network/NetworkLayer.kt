package hr.algebra.toppop.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hr.algebra.toppop.framework.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val topPopService: TopPopService by lazy {
        retrofit.create(TopPopService::class.java)
    }

    val apiClient=ApiClient(topPopService)
}