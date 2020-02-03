package edu.mahmoud.rxjavatutorial.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun Context.show(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.log() {
    Log.d("TestApp", this)
}