package vn.kien.tokoinchallenge.util

import java.text.SimpleDateFormat
import java.util.*

val Date.appString get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)