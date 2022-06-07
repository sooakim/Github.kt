package io.github.sooakim.github.data.util

import android.content.Context
import io.github.sooakim.github.BuildConfig
import io.github.sooakim.github.data.api.RepoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

/**
 * Created by sooakim on 06,June,2022
 */
class NetworkService private constructor(){
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val repoApi: RepoApi by create{ retrofit }

    private inline fun<reified T> create(
        crossinline factory: () -> Retrofit
    ): Lazy<T>{
        return lazy(LazyThreadSafetyMode.NONE){
            factory().create(T::class.java)
        }
    }

    companion object{
        @JvmStatic
        val instance = NetworkService()
    }
}