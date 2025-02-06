package com.course.data.api

import com.course.data.model.BinInfoDto
import com.course.domain.model.BinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {
    @GET("{bin}")
    suspend fun getBinInfo(
        @Path("bin") bin:String
    ): BinInfoDto
}
