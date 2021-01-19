package com.example.android.catfacts.util

import android.view.View
import androidx.navigation.findNavController

fun navigateTo(view: View, viewId:Int) {
    view.findNavController().navigate(viewId)
}