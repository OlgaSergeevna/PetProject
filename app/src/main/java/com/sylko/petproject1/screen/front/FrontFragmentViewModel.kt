package com.sylko.petproject1.screen.front

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearSnapHelper
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.data.SaleRepository

/**
 * ViewModel для списка товаров (front)
 */
class FrontFragmentViewModel(application: Application): AndroidViewModel(application) {

    private var repository: FrontFragmentRepository? = null
    private var repositorySale: SaleRepository? = null
    private var observePositive: LiveData<List<Sale>>? = null

    init {
        repository = FrontFragmentRepository()
        repositorySale = SaleRepository(application)
        observePositive = repositorySale?.observePositive()
    }

    fun attachSnapHelper() : LinearSnapHelper? {
        return  repository?.attachSnapHelper()
    }

    internal fun getPositiveSaleList(): LiveData<List<Sale>>? {
        return observePositive
    }

}