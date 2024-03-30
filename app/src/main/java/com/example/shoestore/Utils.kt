package com.example.shoestore

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter


@BindingAdapter("hideIfEmpty")

fun bindShoeMetaData(editText: EditText, shoeModel: ShoeModel) {
    if (shoeModel.qty == -1 || shoeModel.price == 0.0) {
        editText.text.clear()
    }
}

@BindingAdapter("onValChanged")


fun EditText.onValChanged(listener: ValueChangedListener) {
    addTextChangedListener(object : TextWatcher {


        override fun afterTextChanged(v: Editable?) {
            listener.onChanged(v?.toString() ?: "")
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            return
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            return
        }
    })
}


interface ValueChangedListener {
    fun onChanged(v: String)
}