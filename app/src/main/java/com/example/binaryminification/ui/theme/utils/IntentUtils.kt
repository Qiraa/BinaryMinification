package com.example.binaryminification.ui.theme.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setData(Uri.parse(url))
    startActivity(intent)
}