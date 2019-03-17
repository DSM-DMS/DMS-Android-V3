package dsm.android.v3.connecter

import okhttp3.Interceptor
import okhttp3.Response
import org.bouncycastle.jcajce.provider.digest.SHA3
import org.bouncycastle.util.encoders.Hex
import java.text.SimpleDateFormat
import java.util.*

class SecretHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val userAgent = "okhttp/3.12.0"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA)
        val time = dateFormat.format(Calendar.getInstance().time)
        val base64 = org.bouncycastle.util.encoders.Base64.encode("$userAgent$time".toByteArray())
        val sha3 = SHA3.Digest512().digest(base64)
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Date", time)
                .addHeader("User-Data", Hex.toHexString(sha3))
                .build()
        )
    }
}