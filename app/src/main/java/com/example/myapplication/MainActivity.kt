package com.example.myapplication

import QuoteApi
import RetrofitInstance
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getQuote()
    }

    private fun getQuote() {
        setInProgress(true)
        GlobalScope.launch {
            try {
                val response = RetrofitInstance.quoteApi.getRandomQuotes()
                if (response.isSuccessful) {
                    val quote = response.body()?.firstOrNull()
                    quote?.let { setUI(it) }
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle exception
            } finally {
                setInProgress(false)
            }
        }
    }

    private fun setUI(quote: QuoteApi) {
        binding.quoteTv.text = quote.q
        binding.authorTv.text = quote.a
    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressbar.visibility = View.VISIBLE
            binding.nextBtn.visibility = View.GONE
        } else {
            binding.progressbar.visibility = View.GONE
            binding.nextBtn.visibility = View.VISIBLE
        }
    }
}
