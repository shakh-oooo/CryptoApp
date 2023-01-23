package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.pojo.price.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object{

        private var db : AppDataBase? = null
        private const val NAME_DB = "main.db"
        private val LOCK =Any()

        fun getIInstance(context: Context) : AppDataBase{
            synchronized(LOCK){
                db?.let { return it }
                val instance = Room.databaseBuilder(context,AppDataBase::class.java, NAME_DB).build()
                db = instance
                return instance
            }
        }
    }
    abstract fun coinPriceInfoDao():CoinPriceInfoDao
}