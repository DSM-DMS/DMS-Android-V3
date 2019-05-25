package dsm.android.v3.domain.repository.logout

import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage

class LogoutRepositoryImpl(val localStorage: LocalStorage, val authDao: AuthDao):
    LogoutRepository {
    override fun logout() {
        localStorage.removeToken()
        authDao.delete(authDao.getAuth())
    }
}