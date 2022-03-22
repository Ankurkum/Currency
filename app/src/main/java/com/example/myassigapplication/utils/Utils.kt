package com.example.myassigapplication.utils

import android.content.Context
import android.widget.Toast

object Utils {

    fun showToast(context: Context?, error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

}