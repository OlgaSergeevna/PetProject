package com.sylko.petproject1.screen.back

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.OneItemBinding

/**
 * Адаптер для списка товаров (back)
 */
class BackFragmentAdapter(
        private  val saleList: List<Sale>,
        private val listener: OnItemClickListener
) :
        RecyclerView.Adapter<BackFragmentAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_item, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSale = saleList[position]

        holder.name.text = currentSale.name
        holder.num.text = currentSale.num.toString()
    }

    override fun getItemCount() = saleList.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = OneItemBinding.bind(view)

        val name = binding.tvName
        val num = binding.tvNum
        private val cv = binding.root

        init {
            cv.setOnClickListener(this)
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

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}

