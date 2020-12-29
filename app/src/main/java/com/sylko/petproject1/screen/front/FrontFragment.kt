package com.sylko.petproject1.screen.front

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.FragmentFrontBinding
import com.sylko.petproject1.screen.back.BackFragmentViewModel

/**
 * Основной пользовательский интерфейс для покупки товаров (front)
 */
class FrontFragment : Fragment(R.layout.fragment_front), FrontFragmentAdapter.onItemClickListener {

    private lateinit var binding: FragmentFrontBinding
    private var viewModelBack: BackFragmentViewModel? = null
    private var viewModel: FrontFragmentViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private var adapter: FrontFragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelBack = ViewModelProvider(this).get(BackFragmentViewModel::class.java)
        viewModel = ViewModelProvider(this).get(FrontFragmentViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFrontBinding.bind(view)

        recyclerView = binding.rvSaleOne

        initView()
        observeLiveData()
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
    }

    private fun observeLiveData() {
        viewModel?.getPositiveSaleList()?.observe(viewLifecycleOwner, { data ->
            initAdapter(data)
        })
    }

    private fun initAdapter(data: List<Sale>) {
        adapter = FrontFragmentAdapter(data, this)
        recyclerView.adapter = adapter
        recyclerView.onFlingListener = null
        viewModel?.attachSnapHelper()?.attachToRecyclerView(recyclerView)
    }

    override fun onItemClick(position: Int) {

        val uid = adapter?.getId(position)
        val name = adapter?.getName(position)
        val cost = adapter?.getCost(position)
        val num = adapter?.getNum(position)

        viewModelBack?.insertSale(
                Sale(
                        uid as String,
                        name as String,
                        cost as Double,
                        ((num as Int) - 1)
                )
        )
        observeLiveData()
    }
}