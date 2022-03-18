package com.example.myassigapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myassigapplication.R
import com.example.myassigapplication.presentation.currencyExchange.CurrencyExchangeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToActivity(CurrencyExchangeFragment.newInstance())
    }

    fun addFragmentToActivity(fragment: Fragment?){
        if (fragment == null) return
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.framlayout, fragment)
        tr.addToBackStack(fragment::class.simpleName)
        tr.commit()
    }
}