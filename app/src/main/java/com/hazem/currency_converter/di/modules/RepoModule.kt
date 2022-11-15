package com.hazem.currency_converter.di.modules

import com.hazem.currency_converter.data.remote.currency.CurrencyRepository
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun provideCurrencyRepository(currencyRepository: CurrencyRepository): CurrencyRepositoryContract
}