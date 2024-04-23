import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {
    private const val BASE_URL = "https://zenquotes.io/api/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() // This line builds the Retrofit instance
    }
    val quoteApi:QuoteApi = getInstance().create(QuoteApi::class.java)
}
