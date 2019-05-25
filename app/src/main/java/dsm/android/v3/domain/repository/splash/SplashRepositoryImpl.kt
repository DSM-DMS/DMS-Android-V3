package dsm.android.v3.domain.repository.splash

import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.domain.entity.Auth

class SplashRepositoryImpl(val localStorage: LocalStorage, val authDao: AuthDao): SplashRepository {
    override fun getAuth(): Auth = authDao.getAuth()

    override fun getToken(): String = localStorage.getToken()

}