package com.sylko.petproject1.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data Access Object
 */

@Dao
interface SaleDao {

    @Query("SELECT * FROM Sale")
    fun observeAll(): LiveData<List<Sale>>

    @Query("SELECT * FROM Sale WHERE num>0")
    fun observePositive(): LiveData<List<Sale>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSale(vararg sale: Sale)

    @Update
    fun updateSale(sale: Sale)

    @Delete
    fun deleteSale(sale: Sale)

}