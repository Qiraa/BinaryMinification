package com.example.binaryminification.domain

import android.content.Context
import com.example.binaryminification.R

fun getEvaluationContentDescription(
    context: Context,
    originalText: String,
): String {
    val replacementMap = mapOf(
        "A" to R.string.just_a,
        "B" to R.string.just_b,
        "C" to R.string.just_c,
        "D" to R.string.just_d,
        "∨" to R.string.symb_or,
        "∧" to R.string.symb_and,
        "¬" to R.string.symb_not,
        "→" to R.string.symb_implication,
        "~" to R.string.symb_equal,
        "⊕" to R.string.symb_xor,
        "|" to R.string.symb_nand,
        "↓" to R.string.symb_nor,
    )
    return replacementMap.entries
        .fold(originalText) { acc, entry ->
            acc.replace(entry.key, context.getString(entry.value), ignoreCase = true)
        }
}
