package com.hazem.currency_converter.di.modules

import com.hazem.currency_converter.data.remote.currency.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {
    @Provides
    fun provideCurrencyService(retrofit: Retrofit):CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }
}