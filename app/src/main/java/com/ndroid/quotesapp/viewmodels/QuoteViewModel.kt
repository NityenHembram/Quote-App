package com.ndroid.quotesapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndroid.quotesapp.models.QuoteModel
import com.ndroid.quotesapp.repo.DatabaseRepo
import com.ndroid.quotesapp.useCase.GetAllQuoteDbUseCase
import com.ndroid.quotesapp.useCase.GetQuoteUseCase
import com.ndroid.quotesapp.useCase.SetupPeriodicWorkRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Nityen on 09-05-2025.
 */


@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAllQuoteDbUseCase: GetAllQuoteDbUseCase,
    private val getQuoteUseCase: GetQuoteUseCase,
    private val setupPeriodicWorkRequestUseCase: SetupPeriodicWorkRequestUseCase
) : ViewModel() {

    private var  _quoteList = MutableStateFlow<List<QuoteModel>>(emptyList())
    val quoteList:StateFlow<List<QuoteModel>> = _quoteList

    init {
        viewModelScope.launch {
            setupPeriodicWorkRequestUseCase.invoke()
            getAllQuoteFromDb()
        }
    }

       private fun getAllQuoteFromDb(){
          Log.d("catching", "Quote Viewmodel: $")
           viewModelScope.launch {
               getAllQuoteDbUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList()).collect{
                   _quoteList.value = it
               }


           }
    }

     suspend fun getQuote(): Unit {
        getQuoteUseCase.invoke()
    }
}
