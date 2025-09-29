package com.ragnar.baseapp.remote

import com.ragnar.baseapp.model.Popular
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getPopular(): Popular
}