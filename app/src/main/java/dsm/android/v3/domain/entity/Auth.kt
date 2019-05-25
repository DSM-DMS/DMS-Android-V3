package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Auth(
    @PrimaryKey val id: String,
    val password: String)