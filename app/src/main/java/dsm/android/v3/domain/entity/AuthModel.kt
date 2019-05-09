package dsm.android.v3.domain.entity

import com.google.gson.annotations.SerializedName

data class AuthModel(@SerializedName("accessToken") val token: String,
                     @SerializedName("refreshToken") val refreshToken: String?)