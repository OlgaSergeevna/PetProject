package com.sylko.petproject1.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sylko.petproject1.R
import com.sylko.petproject1.databinding.ActivityMainBinding

/**
 * Основной пользовательский интерфейс для переключения между фрагментами
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        binding.btnBack.setOnClickListener { navController?.navigate(R.id.backFragment) }

        binding.btnFront.setOnClickListener { navController?.navigate(R.id.frontFragment) }
    }
}