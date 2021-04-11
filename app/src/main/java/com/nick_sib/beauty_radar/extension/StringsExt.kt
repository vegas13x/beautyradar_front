package com.nick_sib.beauty_radar.extension

private val regex4 by lazy("(\\d{3})"::toRegex)
private val regex7 by lazy("(\\d{3})(\\d{3})"::toRegex)
private val regex9 by lazy("(\\d{3})(\\d{3})(\\d{2})"::toRegex)
private val regex11 by lazy("(\\d{3})(\\d{3})(\\d{2})(\\d+)"::toRegex)

fun String.phoneToDigit(): String {
    val result = this
        .replace("+7", "")
        .getPhoneNumber()
    return if (result.length > 10) result.dropWhile { it == '7' } else result
}


fun String.digitToPhone(defValue: String) = when (this.length) {
    0 -> if (defValue.length < 4) "" else defValue
    1 -> if (this == "8" || this == "+") "+7 (" else "+7 ($this"
    4 -> this.replace(regex4, "+7 ($1) ")
    7 -> this.replace(regex7, "+7 ($1) $2-")
    9 -> this.replace(regex9, "+7 ($1) $2-$3-")
    10, 11 -> this.replace(regex11, "+7 ($1) $2-$3-$4")
    else -> defValue
}

fun String.getPhoneNumber(): String = this
    .replace(" ", "")
    .replace("(", "")
    .replace(")", "")
    .replace("-", "")
    .replace("+", "")