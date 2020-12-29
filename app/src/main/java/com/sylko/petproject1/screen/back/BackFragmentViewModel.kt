package com.sylko.petproject1.screen.back

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.data.SaleRepository

/**
 * ViewModel для списка товаров (back)
 */
class BackFragmentViewModel(application: Application): AndroidViewModel(application) {

    private var observeAll: LiveData<List<Sale>>? = null
    private var repository: SaleRepository? = null

    init {
        repository = SaleRepository(application)
        observeAll = repository?.observeAll()
    }

    internal fun getAllSaleList(): LiveData<List<Sale>>? {
        return observeAll
    }

    fun insertSale(sale: Sale){
        repository?.insertSale(sale)
    }

}