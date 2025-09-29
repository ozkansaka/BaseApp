package com.ragnar.baseapp.repository

import com.ragnar.baseapp.model.Popular

interface Repository {
    suspend fun getPopular(): Popular
}