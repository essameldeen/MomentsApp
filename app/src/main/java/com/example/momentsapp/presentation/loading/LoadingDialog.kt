package com.example.momentsapp.presentation.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.momentsapp.R

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window?.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
    }

    override fun show() {
        super.show()
        val lp = WindowManager.LayoutParams()
        val window = window
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}