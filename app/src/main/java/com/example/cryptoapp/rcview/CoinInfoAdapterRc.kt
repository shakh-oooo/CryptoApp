package com.example.cryptoapp.rcview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.pojo.price.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview.view.*

class CoinInfoAdapterRc(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapterRc.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()

    var onCoinClickListener: OnCoinClickListener? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName = itemView.textName
        var imageView = itemView.imageView
        var textPrice = itemView.textPrice
        var textTime = itemView.textTime

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)

        holder.textName.text = String.format(
            symbolsTemplate,
            coin.fromSymbol,
            coin.tosymbol
        ) /* coin.fromsymbol + " / " + coin.tosymbol*/
        holder.textPrice.text = coin.price.toString()
        holder.textTime.text = String.format(lastUpdateTemplate, coin.getFormatDate())
        Picasso.get().load(coin.imageUrl()).into(holder.imageView)


        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
            /*при клике на ItemView если слушатель не равен null то у него вызевется метод onCoinClick  */
        }
    }

    override fun getItemCount() = coinInfoList.size

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}