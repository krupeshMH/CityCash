package com.citycash.di

import com.citycash.network.GetProductListAPI
import com.citycash.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(gson: Gson): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()

                // Customize the request
                val request = original.newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .removeHeader("Pragma")
                    .build()

                val response = chain.proceed(request)
                // Customize or return the response
                response
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @Singleton
    @Provides
    fun provideProductListApi(retrofit: Retrofit): GetProductListAPI {
        return retrofit.create(GetProductListAPI::class.java)
    }
}