package com.sylko.petproject1.screen.editor

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.FragmentEditorBinding
import com.sylko.petproject1.screen.back.BackFragmentViewModel
import com.sylko.petproject1.util.DecimalDigitsInputFilter
import java.util.*

/**
 * Основной пользовательский интерфейс редактирования товаров (back)
 */
class EditorFragment : Fragment(R.layout.fragment_editor) {

    private lateinit var binding: FragmentEditorBinding
    private var viewModelBack: BackFragmentViewModel? = null
    private var new: Boolean = null == true
    private var uid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModelBack = ViewModelProvider(this).get(BackFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditorBinding.bind(view)

        inputFilters()
        getAndSetBundle()

        binding.btnClose.setOnClickListener { activity?.onBackPressed() }

        binding.btnSave.setOnClickListener {

            if (checkTheEnteredValues()) {
                Toast.makeText(context, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
            }
            else {
                if (new){
                viewModelBack?.insertSale(
                        Sale(
                                uid as String,
                                binding.etName.text.toString(),
                                binding.etCost.text.toString().toDouble(),
                                binding.etQuantity.text.toString().toInt()
                        )
                )}
                else {viewModelBack?.updateSale(
                        Sale(
                                uid as String,
                                binding.etName.text.toString(),
                                binding.etCost.text.toString().toDouble(),
                                binding.etQuantity.text.toString().toInt()
                        ))}
                activity?.onBackPressed()
            }
        }
    }

    private fun checkTheEnteredValues(): Boolean{
        return ((binding.etName.text.isEmpty()) ||
                (binding.etCost.text.isEmpty()) ||
                (binding.etQuantity.text.isEmpty()))
    }

    private fun inputFilters(){
        binding.etCost.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.etCost.filters = arrayOf(DecimalDigitsInputFilter(2))
        binding.etQuantity.inputType = InputType.TYPE_CLASS_NUMBER
        binding.etName.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun getAndSetBundle(){
        new = (arguments?.get("KEY_UID")==null)

        uid = (arguments?.get("KEY_UID")?: UUID.randomUUID().toString()) as String?
        val name = arguments?.get("KEY_NAME")
        val cost = arguments?.get("KEY_COST")
        val num = arguments?.get("KEY_NUM")

        binding.etName.setText(name?.toString())
        binding.etCost.setText(cost?.toString())
        binding.etQuantity.setText(num?.toString())
    }

}

