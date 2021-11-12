package com.example.momentsapp.utils

import android.content.Context
import com.example.momentsapp.presentation.loading.LoadingDialog

object Loading
{
    private var loading: LoadingDialog? = null

    fun show(context: Context)
    {
        if (loading == null) loading = LoadingDialog(context)
        if (!loading!!.isShowing) loading!!.show()
    }

    fun dismiss()
    {
        if (loading != null && loading!!.isShowing) loading!!.dismiss()
    }
}