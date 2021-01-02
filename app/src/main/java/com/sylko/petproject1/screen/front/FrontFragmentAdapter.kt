package com.sylko.petproject1.screen.front

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.OneItemFrontBinding

/**
 * Адаптер для списка товаров (front)
 */
class FrontFragmentAdapter(
        private val saleList: List<Sale>,
        private val listener: OnItemClickListener
) :
        RecyclerView.Adapter<FrontFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_item_front, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSale = saleList[position]

        holder.name.text = currentSale.name
        holder.cost.text = currentSale.cost.toString()
        holder.num.text = currentSale.num.toString()
    }

    override fun getItemCount() = saleList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = OneItemFrontBinding.bind(view)

        val name = binding.nameFront
        val cost = binding.costFront
        val num = binding.numFront
        private val buy = binding.buy

        init {
            buy.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun getId(position: Int): String {
        return saleList[position].uid
    }

    fun getName(position: Int): String{
        return saleList[position].name
    }

    fun getCost(position: Int): Double{
        return saleList[position].cost
    }

    fun getNum(position: Int): Int{
        return saleList[position].num
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}

