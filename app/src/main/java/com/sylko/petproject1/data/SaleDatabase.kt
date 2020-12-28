package com.sylko.petproject1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Database
 */

@Database(entities = [Sale::class], version = 1)
abstract class SaleDatabase : RoomDatabase() {

    abstract fun saleDao(): SaleDao

    companion object {
        @Volatile
        private var INSTANCE: SaleDatabase? = null

        fun getInstance(context: Context): SaleDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(
                                    context
                            ).also {
                                INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        SaleDatabase::class.java, "Sales.db"
                )
                        .build()
    }

}