package ru.vincetti.test.cashpointssample.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vincetti.test.cashpointssample.core.network.TinkoffService
import ru.vincetti.test.cashpointssample.utils.baseUrl
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun appServiceProvider(retrofit: Retrofit): TinkoffService {
        return retrofit.create(TinkoffService::class.java)
    }
}
