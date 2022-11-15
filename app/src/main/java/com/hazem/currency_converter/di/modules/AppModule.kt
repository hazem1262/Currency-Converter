package com.hazem.currency_converter.di.modules

import com.hazem.currency_converter.BuildConfig
import com.hazem.currency_converter.utils.network.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIsDebug() = BuildConfig.DEBUG

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkUtils.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttp(isDebug: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        if (isDebug)
            logging.level = HttpLoggingInterceptor.Level.BODY
        else logging.level = HttpLoggingInterceptor.Level.NONE

        /*
        * hard code the token for simplicity, in live applications use
        * more secure way like keystore api.
        * */
        val authInterceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("apikey", "Z1UhWQstvMSCWv1Ej3U4yaPouAnWevfJ")
            return@Interceptor chain.proceed(builder.build())
        }
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)

        return okHttpClientBuilder.build()
    }
}