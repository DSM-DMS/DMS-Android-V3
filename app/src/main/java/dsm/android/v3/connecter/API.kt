package dsm.android.v3.connecter

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>
}