package dsm.android.v3.data.local.shared

import io.reactivex.Observable

interface LocalStorage {
    fun saveToken(token: String)
    fun getToken(): Observable<String>
    fun removeToken(token: String)
}