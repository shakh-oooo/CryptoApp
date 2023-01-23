package com.example.cryptoapp.api

import com.example.cryptoapp.pojo.price.CoinPriceInfoRawData
import com.example.cryptoapp.pojo.toplist.CoinInfoListOfData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUREY_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(QUREY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUREY_PARAM_TO_SYMBOL) tsym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUREY_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
    ):Single<CoinPriceInfoRawData>

    companion object {
        const val QUREY_PARAM_LIMIT = "limit"
        const val QUREY_PARAM_TO_SYMBOL = "tsym"
        const val QUREY_PARAM_TO_API_KEY = "api_key"

        const val CURRENCY = "USD"

        const val QUERY_PARAM_TO_SYMBOLS ="fsyms"
        const val QUERY_PARAM_FROM_SYMBOLS ="fsyms"

    }

}