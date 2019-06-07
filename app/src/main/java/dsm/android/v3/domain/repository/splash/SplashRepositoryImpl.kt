package dsm.android.v3.domain.repository.splash

import dsm.android.v3.data.local.shared.LocalStorage

class SplashRepositoryImpl(val localStorage: LocalStorage): SplashRepository {
    override fun getToken(): String = localStorage.getToken(true)

}