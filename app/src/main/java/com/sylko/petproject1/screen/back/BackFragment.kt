package com.sylko.petproject1.screen.back

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.*
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.FragmentBackBinding

/**
 * Основной пользовательский интерфейс списка товаров (back)
 */
class BackFragment : Fragment(R.layout.fragment_back), BackFragmentAdapter.onItemClickListener {

    private var viewModel: BackFragmentViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentBackBinding
    private var adapter: BackFragmentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBackBinding.bind(view)

        viewModel = ViewModelProvider(this).get(BackFragmentViewModel::class.java)

        binding.btnOpenEditor.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_backFragment_to_editorFragment2) }

        recyclerView = binding.rvSale

        initView()
        observeLiveData()
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun observeLiveData() {
        viewModel?.getAllSaleList()?.observe(viewLifecycleOwner, { data ->
            initAdapter(data)
        })
    }

    private fun initAdapter(data: List<Sale>) {
        adapter = BackFragmentAdapter(data, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {

        val uid = adapter?.getId(position)
        val name = adapter?.getName(position)
        val cost = adapter?.getCost(position)
        val num = adapter?.getNum(position)

        val bundle = bundleOf(
                Pair("KEY_UID", uid),
                Pair("KEY_NAME", name),
                Pair("KEY_COST", cost),
                Pair("KEY_NUM", num),
        )

        NavHostFragment.findNavController(this).navigate(R.id.action_backFragment_to_editorFragment2, bundle)
    }
}