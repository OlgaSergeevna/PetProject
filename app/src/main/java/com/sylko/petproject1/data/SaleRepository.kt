package com.sylko.petproject1.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Repository
 */
class SaleRepository (application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var saleDao: SaleDao = SaleDatabase.getInstance(application).saleDao()

    fun observeAll() = saleDao.observeAll()

    fun insertSale(sale: Sale) {
        launch { insertSaleSuspend(sale) }
    }

    fun updateSale(sale: Sale) {
        launch { updateSaleSuspend(sale) }
    }

    fun observePositive() = saleDao.observePositive()

    private suspend fun insertSaleSuspend(sale: Sale) {
        withContext(Dispatchers.IO) {
            saleDao.insertSale(sale)
        }
    }
    private suspend fun updateSaleSuspend(sale: Sale) {
        withContext(Dispatchers.IO) {
            saleDao.updateSale(sale)
        }
    }
}