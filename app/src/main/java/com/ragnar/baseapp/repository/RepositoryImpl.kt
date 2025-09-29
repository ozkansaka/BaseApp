package com.ragnar.baseapp.repository

import com.ragnar.baseapp.model.Popular
import com.ragnar.baseapp.remote.MovieApi
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val api: MovieApi
) : Repository {
    override suspend fun getPopular(): Popular {
        return api.getPopular()
    }
}