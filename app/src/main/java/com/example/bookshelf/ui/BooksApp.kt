package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier
){
    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)

    //добавление панели поиска вместо страндартного тул бара
    val searchWidgetState = booksViewModel.searchWidgetState
    val searchTextState = booksViewModel.searchTextState
    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = {
            //TopAppBar(title = {Text(stringResource(id = R.string.app_name)) }) }
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = {
                    booksViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    booksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    booksViewModel.getBooks(it)
                },
                onSearchTriggered = {
                    booksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    )
        {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colors.background
            ){
                HomeScreen(booksUiState = booksViewModel.booksUiState, retryAction = {booksViewModel::getBooks})
            }
    }
}