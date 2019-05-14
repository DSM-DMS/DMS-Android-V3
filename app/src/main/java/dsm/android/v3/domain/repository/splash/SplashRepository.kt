package dsm.android.v3.domain.repository.splash

import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.domain.entity.Auth

interface SplashRepository {
    fun getAuth(): Auth

    fun getToken(): String
}