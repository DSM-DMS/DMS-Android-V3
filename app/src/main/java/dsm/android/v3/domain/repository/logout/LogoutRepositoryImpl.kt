package dsm.android.v3.domain.repository.logout

import dsm.android.v3.data.local.shared.LocalStorage

class LogoutRepositoryImpl(val localStorage: LocalStorage): LogoutRepository {
    override fun logout() = localStorage.removeToken()
}