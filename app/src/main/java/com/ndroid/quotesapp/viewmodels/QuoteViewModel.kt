package com.ndroid.quotesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndroid.quotesapp.repo.DatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Nityen on 09-05-2025.
 */


@HiltViewModel
class QuoteViewModel @Inject constructor(private val repo:DatabaseRepo):ViewModel() {


    fun getQuote(){
        viewModelScope.launch {

        }
    }
}