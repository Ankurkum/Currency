package com.example.myassigapplication

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.data.dto.CurrencyExchangeRates
import com.example.myassigapplication.data.dto.CurrencySymbols
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JsonData {

    companion object {

        private fun getCurrencyData(): String {
            return "{\n" +
                    "    \"success\": true,\n" +
                    "    \"symbols\": {\n" +
                    "        \"EUR\": \"EUR\",\n" +
                    "        \"USD\": \"USD\",\n" +
                    "        \"AUD\": \"AUD\",\n" +
                    "        \"CAD\": \"CAD\",\n" +
                    "        \"PLN\": \"PLN\",\n" +
                    "        \"MXN\": \"MXN\"\n" +
                    "    }\n" +
                    "}"
        }

        fun getCurrencyExchangeRates(): String {
            return "{\n" +
                    "    \"success\": true,\n" +
                    "    \"timestamp\": 1647421095,\n" +
                    "    \"base\": \"EUR\",\n" +
                    "    \"date\": \"2022-03-16\",\n" +
                    "    \"rates\": {\n" +
                    "        \"AED\": 4.03725,\n" +
                    "        \"AFN\": 95.341329,\n" +
                    "        \"ALL\": 123.337442,\n" +
                    "        \"AMD\": 565.006766,\n" +
                    "        \"ANG\": 1.984206,\n" +
                    "        \"AOA\": 506.35289,\n" +
                    "        \"ARS\": 120.09724,\n" +
                    "        \"AUD\": 1.519844,\n" +
                    "        \"AWG\": 1.978775,\n" +
                    "        \"AZN\": 1.866524,\n" +
                    "        \"BAM\": 1.960399,\n" +
                    "        \"BBD\": 2.22288,\n" +
                    "        \"BDT\": 94.654295,\n" +
                    "        \"BGN\": 1.957857,\n" +
                    "        \"BHD\": 0.414371,\n" +
                    "        \"BIF\": 2257.42099,\n" +
                    "        \"BMD\": 1.099167,\n" +
                    "        \"BND\": 1.503303,\n" +
                    "        \"BOB\": 7.558014,\n" +
                    "        \"BRL\": 5.676424,\n" +
                    "        \"BSD\": 1.100966,\n" +
                    "        \"BTC\": 0.000027192978,\n" +
                    "        \"BTN\": 84.212861,\n" +
                    "        \"BWP\": 12.824187,\n" +
                    "        \"BYN\": 3.662536,\n" +
                    "        \"BYR\": 21543.666187,\n" +
                    "        \"BZD\": 2.219172,\n" +
                    "        \"CAD\": 1.398343,\n" +
                    "        \"CDF\": 2213.721637,\n" +
                    "        \"CHF\": 1.033288,\n" +
                    "        \"CLF\": 0.032261,\n" +
                    "        \"CLP\": 890.192577,\n" +
                    "        \"CNY\": 6.977069,\n" +
                    "        \"COP\": 4212.743015,\n" +
                    "        \"CRC\": 708.755328,\n" +
                    "        \"CUC\": 1.099167,\n" +
                    "        \"CUP\": 29.127916,\n" +
                    "        \"CVE\": 110.522576,\n" +
                    "        \"CZK\": 24.735651,\n" +
                    "        \"DJF\": 195.996777,\n" +
                    "        \"DKK\": 7.440401,\n" +
                    "        \"DOP\": 60.397208,\n" +
                    "        \"DZD\": 156.974185,\n" +
                    "        \"EGP\": 17.267685,\n" +
                    "        \"ERN\": 16.487505,\n" +
                    "        \"ETB\": 56.490639,\n" +
                    "        \"EUR\": 1,\n" +
                    "        \"FJD\": 2.329797,\n" +
                    "        \"FKP\": 0.843016,\n" +
                    "        \"GBP\": 0.841413,\n" +
                    "        \"GEL\": 3.511815,\n" +
                    "        \"GGP\": 0.843016,\n" +
                    "        \"GHS\": 7.871846,\n" +
                    "        \"GIP\": 0.843016,\n" +
                    "        \"GMD\": 58.613073,\n" +
                    "        \"GNF\": 9834.218954,\n" +
                    "        \"GTQ\": 8.485576,\n" +
                    "        \"GYD\": 230.336807,\n" +
                    "        \"HKD\": 8.597594,\n" +
                    "        \"HNL\": 27.022731,\n" +
                    "        \"HRK\": 7.578751,\n" +
                    "        \"HTG\": 116.015377,\n" +
                    "        \"HUF\": 372.051445,\n" +
                    "        \"IDR\": 15707.97065,\n" +
                    "        \"ILS\": 3.583954,\n" +
                    "        \"IMP\": 0.843016,\n" +
                    "        \"INR\": 83.827397,\n" +
                    "        \"IQD\": 1606.869478,\n" +
                    "        \"IRR\": 46549.707477,\n" +
                    "        \"ISK\": 144.74913,\n" +
                    "        \"JEP\": 0.843016,\n" +
                    "        \"JMD\": 168.426992,\n" +
                    "        \"JOD\": 0.7793,\n" +
                    "        \"JPY\": 130.055047,\n" +
                    "        \"KES\": 125.624931,\n" +
                    "        \"KGS\": 115.412431,\n" +
                    "        \"KHR\": 4458.850926,\n" +
                    "        \"KMF\": 494.570276,\n" +
                    "        \"KPW\": 989.250363,\n" +
                    "        \"KRW\": 1355.38785,\n" +
                    "        \"KWD\": 0.334201,\n" +
                    "        \"KYD\": 0.917446,\n" +
                    "        \"KZT\": 567.45764,\n" +
                    "        \"LAK\": 12653.304037,\n" +
                    "        \"LBP\": 1664.679588,\n" +
                    "        \"LKR\": 300.011917,\n" +
                    "        \"LRD\": 168.936478,\n" +
                    "        \"LSL\": 16.608274,\n" +
                    "        \"LTL\": 3.245553,\n" +
                    "        \"LVL\": 0.664875,\n" +
                    "        \"LYD\": 5.138175,\n" +
                    "        \"MAD\": 10.746002,\n" +
                    "        \"MDL\": 20.312724,\n" +
                    "        \"MGA\": 4452.246099,\n" +
                    "        \"MKD\": 61.693671,\n" +
                    "        \"MMK\": 1957.655978,\n" +
                    "        \"MNT\": 3164.251022,\n" +
                    "        \"MOP\": 8.875666,\n" +
                    "        \"MRO\": 392.402302,\n" +
                    "        \"MUR\": 49.404118,\n" +
                    "        \"MVR\": 16.982155,\n" +
                    "        \"MWK\": 888.247088,\n" +
                    "        \"MXN\": 22.835291,\n" +
                    "        \"MYR\": 4.613749,\n" +
                    "        \"MZN\": 70.159586,\n" +
                    "        \"NAD\": 16.593427,\n" +
                    "        \"NGN\": 457.990195,\n" +
                    "        \"NIO\": 39.375766,\n" +
                    "        \"NOK\": 9.815789,\n" +
                    "        \"NPR\": 134.739789,\n" +
                    "        \"NZD\": 1.618862,\n" +
                    "        \"OMR\": 0.422632,\n" +
                    "        \"PAB\": 1.100966,\n" +
                    "        \"PEN\": 4.108134,\n" +
                    "        \"PGK\": 3.879341,\n" +
                    "        \"PHP\": 57.405629,\n" +
                    "        \"PKR\": 196.966851,\n" +
                    "        \"PLN\": 4.708632,\n" +
                    "        \"PYG\": 7664.662736,\n" +
                    "        \"QAR\": 4.002086,\n" +
                    "        \"RON\": 4.949655,\n" +
                    "        \"RSD\": 117.667503,\n" +
                    "        \"RUB\": 119.177161,\n" +
                    "        \"RWF\": 1117.624491,\n" +
                    "        \"SAR\": 4.12377,\n" +
                    "        \"SBD\": 8.846447,\n" +
                    "        \"SCR\": 15.843636,\n" +
                    "        \"SDG\": 491.327712,\n" +
                    "        \"SEK\": 10.473134,\n" +
                    "        \"SGD\": 1.496642,\n" +
                    "        \"SHP\": 1.513996,\n" +
                    "        \"SLL\": 12882.233006,\n" +
                    "        \"SOS\": 643.012565,\n" +
                    "        \"SRD\": 22.602168,\n" +
                    "        \"STD\": 22750.530285,\n" +
                    "        \"SVC\": 9.633539,\n" +
                    "        \"SYP\": 2761.106976,\n" +
                    "        \"SZL\": 16.675362,\n" +
                    "        \"THB\": 36.734699,\n" +
                    "        \"TJS\": 14.33992,\n" +
                    "        \"TMT\": 3.847083,\n" +
                    "        \"TND\": 3.244748,\n" +
                    "        \"TOP\": 2.506485,\n" +
                    "        \"TRY\": 16.110997,\n" +
                    "        \"TTD\": 7.475791,\n" +
                    "        \"TWD\": 31.388355,\n" +
                    "        \"TZS\": 2546.769039,\n" +
                    "        \"UAH\": 32.367934,\n" +
                    "        \"UGX\": 3957.924066,\n" +
                    "        \"USD\": 1.099167,\n" +
                    "        \"UYU\": 46.922354,\n" +
                    "        \"UZS\": 12000.637861,\n" +
                    "        \"VEF\": 235035047485.59503,\n" +
                    "        \"VND\": 25152.779857,\n" +
                    "        \"VUV\": 125.433164,\n" +
                    "        \"WST\": 2.882065,\n" +
                    "        \"XAF\": 657.483304,\n" +
                    "        \"XAG\": 0.044372,\n" +
                    "        \"XAU\": 0.000573,\n" +
                    "        \"XCD\": 2.970552,\n" +
                    "        \"XDR\": 0.798962,\n" +
                    "        \"XOF\": 657.489299,\n" +
                    "        \"XPF\": 120.221347,\n" +
                    "        \"YER\": 275.066661,\n" +
                    "        \"ZAR\": 16.572921,\n" +
                    "        \"ZMK\": 9893.820546,\n" +
                    "        \"ZMW\": 19.514379,\n" +
                    "        \"ZWL\": 353.93121\n" +
                    "    }\n" +
                    "}"
        }

        private fun getHistoricalRates(): String {
            return "{\n" +
                    "    \"success\": true,\n" +
                    "    \"timestamp\": 1363391999,\n" +
                    "    \"historical\": true,\n" +
                    "    \"base\": \"EUR\",\n" +
                    "    \"date\": \"2013-03-15\",\n" +
                    "    \"rates\": {\n" +
                    "        \"USD\": 1.306206,\n" +
                    "        \"AUD\": 1.256032,\n" +
                    "        \"CAD\": 1.33383,\n" +
                    "        \"PLN\": 4.157385,\n" +
                    "        \"MXN\": 16.241446,\n" +
                    "        \"EUR\": 4.03725\n" +
                    "    }\n" +
                    "}"
        }

        fun getExchangeRatesWithFlow(): Flow<Resource<CurrencyExchangeData>> = flow {
            val gson = Gson()
            val currencyExchangeRates = gson.fromJson(getHistoricalRates(), CurrencyExchangeRates::class.java)
            emit(Resource.Success(currencyExchangeRates.toCurrencyExchange()))
        }

        fun getCurrencySymbolsWithFlow(): Flow<Resource<DomainCurrencySymbolsData>> = flow {
            val gson = Gson()
            val currencyExchangeRates = gson.fromJson(getCurrencyData(), CurrencySymbols::class.java)
            emit(Resource.Success(currencyExchangeRates.toCurrencySymbols()))
        }

        fun getCurrExchangeRatesWithFlow(): Flow<Resource<CurrencyExchangeData>> = flow {
            val gson = Gson()
            val currencyExchangeRates = gson.fromJson(getCurrencyExchangeRates(), CurrencyExchangeRates::class.java)
            emit(Resource.Success(currencyExchangeRates.toCurrencyExchange()))
        }
    }
}