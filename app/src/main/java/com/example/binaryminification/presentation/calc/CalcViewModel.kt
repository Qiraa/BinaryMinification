package com.example.binaryminification.presentation.calc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binaryminification.App
import com.example.binaryminification.calcfunction.BinaryCalculator
import com.example.binaryminification.data.HistoryEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalcViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<CalcState> =
        MutableStateFlow(CalcState("", ""))
    val state: StateFlow<CalcState> = mutableState.asStateFlow()

    fun onInputUpdate(newInput: String) {
        mutableState.update { it.copy(input = newInput) }
    }

    fun onKeyboardButtonClick(buttonAction: String) {
        mutableState.update { it.copy(input = it.input + buttonAction) }
    }

    fun onDeleteButtonClick() {
        mutableState.update { it.copy(input = it.input.dropLast(1)) }
    }

    fun onClear() {
        mutableState.update { it.copy(input = "") }
    }

    fun onCalculateButtonClick() {
        val binaryCalculator = BinaryCalculator()
        try {
            binaryCalculator.calcFunction(mutableState.value.input)
            mutableState.update { it.copy(output = binaryCalculator.allPrintsString) }
            viewModelScope.launch {
                App.db.historyEntityDao().insertAll(
                    HistoryEntity(
                        input = mutableState.value.input,
                        output = binaryCalculator.allPrintsString,
                        timestamp = System.currentTimeMillis(),
                    )
                )
            }
        } catch (ex: Exception) {
            mutableState.update { it.copy(output = ex.localizedMessage ?: "Something went wrong") }
        }
    }
}