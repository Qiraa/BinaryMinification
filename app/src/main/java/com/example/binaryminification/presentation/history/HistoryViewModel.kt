package com.example.binaryminification.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binaryminification.App
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<HistoryState> =
        MutableStateFlow(HistoryState(emptyList()))
    val state: StateFlow<HistoryState> = mutableState.asStateFlow()

    init {
        loadHistory()
    }

    fun clearHistory() {
        viewModelScope.launch {
            App.db.historyEntityDao().deleteAll()
            mutableState.value = HistoryState(emptyList())
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            val historyEntities = App.db.historyEntityDao().getAll()
            val items = historyEntities.map {
                HistoryItem(it.id.toString(), it.input, it.output, it.timestamp, isExpanded = false)
            }
            mutableState.value = HistoryState(items)
        }
    }

    fun changeExpandStatus(id: String) {
        mutableState.update { state ->
            state.copy(
                items = state.items.map { item ->
                    if (item.id == id) {
                        item.copy(isExpanded = !item.isExpanded)
                    } else {
                        item
                    }
                }
            )
        }
    }
}