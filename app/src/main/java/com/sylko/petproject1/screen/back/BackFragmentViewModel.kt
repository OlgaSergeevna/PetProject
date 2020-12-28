package com.sylko.petproject1.screen.back

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.data.SaleDatabase
import com.sylko.petproject1.data.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

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