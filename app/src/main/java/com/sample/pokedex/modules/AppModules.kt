package com.sample.pokedex.modules

import com.sample.pokedex.presentation.api.CloudApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @CloudAuthInterceptorClient
    @Provides
    fun provideCloudOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideCloudService(
        moshi: Moshi,
        @CloudAuthInterceptorClient authClient: OkHttpClient
    ): CloudApi {
        return Retrofit.Builder()
            // manage staging configuration
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(authClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CloudApi::class.java)
    }

}