package com.example.binaryminification.presentation.history

data class HistoryItem(
    val id: String,
    val input: String,
    val output: String,
    val timestamp: Long,
    val isExpanded: Boolean,
)