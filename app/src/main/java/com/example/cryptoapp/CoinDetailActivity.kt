package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        if (!intent.hasExtra(EXTRA_SYMBOL_KEY)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_SYMBOL_KEY)

        fromSymbol?.let {
            viewModel.getPriceAboutCoin(it).observe(this, Observer {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowday.toString()
                tvMaxPrice.text = it.highday.toString()
                tvLastMarket.text = it.lastmarket.toString()
                tvLastUpdate.text = it.getFormatDate()
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.tosymbol
                Picasso.get().load(it.imageUrl()).into(ivLogoCoin)

            })
        }
    }

    companion object {
        private const val EXTRA_SYMBOL_KEY = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_SYMBOL_KEY, fromSymbol)
            return intent
        }
    }

}