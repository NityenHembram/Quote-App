package com.ndroid.quotesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndroid.quotesapp.viewmodels.QuoteViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListScreen() {
    val viewModel = hiltViewModel<QuoteViewModel>()

    val coroutineScope  =  rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(
        title = { Text("Quote's app" , color = Color.White)},
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    viewModel.getQuote()
                }
            }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        },
    )
    }) { paddingValues ->
        val quoteList = viewModel.quoteList.collectAsState()
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(quoteList.value.size) { index ->
                val quote = quoteList.value[index]
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Quote: ${quote.quote}")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Author: ${quote.author}")

                        Row {
                            Text("Type: ${quote.workType}".uppercase())
                        }
                    }

                }
            }
        }
    }

}