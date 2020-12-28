package com.sylko.petproject1.screen.editor

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sylko.petproject1.R
import com.sylko.petproject1.data.Sale
import com.sylko.petproject1.databinding.FragmentEditorBinding
import com.sylko.petproject1.screen.back.BackFragmentViewModel
import java.util.*

/**
 * Основной пользовательский интерфейс редактирования товаров (back)
 */
class EditorFragment : Fragment(R.layout.fragment_editor) {

    private lateinit var binding: FragmentEditorBinding
    var viewModelBack: BackFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelBack = ViewModelProvider(this).get(BackFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditorBinding.bind(view)

        //ввод только чисел
        binding.etCost.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.etQuantity.inputType = InputType.TYPE_CLASS_NUMBER

        val uid = arguments?.get("KEY_UID")?: UUID.randomUUID().toString()
        val name = arguments?.get("KEY_NAME")
        val cost = arguments?.get("KEY_COST")
        val num = arguments?.get("KEY_NUM")

        binding.etName.setText(name?.toString())
        binding.etCost.setText(cost?.toString())
        binding.etQuantity.setText(num?.toString())

        binding.btnClose.setOnClickListener { activity?.onBackPressed() }

        binding.btnSave.setOnClickListener {

            viewModelBack?.insertSale(
                    Sale(
                            uid as String,
                            binding.etName.text.toString(),
                            binding.etCost.text.toString().toDouble(),
                            binding.etQuantity.text.toString().toInt()
                    )
            )
            activity?.onBackPressed()
        }

    }
}
