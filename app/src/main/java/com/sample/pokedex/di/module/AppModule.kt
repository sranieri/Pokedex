package com.sample.pokedex.di.module

import com.sample.pokedex.presentation.api.CloudApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideMoshi() }
    single { provideOkHttpClient() }
    single { provideCloudService(get(), get()) }
}

private fun provideMoshi(): Moshi = Moshi.Builder().build()

private fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

private fun provideCloudService(
    moshi: Moshi,
    authClient: OkHttpClient
): CloudApi {
    return Retrofit.Builder()
        // manage staging configuration
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(authClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(CloudApi::class.java)
}