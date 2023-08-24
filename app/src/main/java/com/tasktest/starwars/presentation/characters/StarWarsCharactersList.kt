package com.tasktest.starwars.presentation.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.tasktest.starwars.R
import com.tasktest.starwars.data.error.NoMorePagesLeftToLoadException
import com.tasktest.starwars.navigation.AppScreen
import com.tasktest.starwars.navigation.LocalNavController
import com.tasktest.starwars.ui.theme.StarWarsTheme

@Composable
fun StarWarsCharactersList(
    characterItems: LazyPagingItems<CharacterUI>,
    onRetry: () -> Unit,
    modifier: Modifier
) {
    val navController = LocalNavController.current
    LazyColumn(modifier = modifier)
    {
        items(characterItems.itemCount) { index ->
            characterItems[index]?.let {
                CharacterItem(
                    character = it,
                    onCellClick = {
                        navController.navigate(
                            AppScreen.FilmsScreen.route
                        )
                    },
                    modifier = modifier
                )
            }
        }
        characterItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    items(10) { SkeletonPlaceHolder(modifier = modifier.fillParentMaxWidth()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val errorMessage =
                        (characterItems.loadState.refresh as LoadState.Error).error.localizedMessage
                    item {
                        ErrorMessage(
                            errorMessage = errorMessage ?: stringResource(R.string.error_occured),
                            onRetry = onRetry
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { NextPageLoaderItem() }
                }

                loadState.append is LoadState.Error -> {
                    val error = (characterItems.loadState.append as LoadState.Error).error
                    HandleMessage(error = error, onRetry)
                }
            }
        }
    }
}

fun LazyListScope.HandleMessage(error: Throwable, onRetry: () -> Unit) {
    val errorMessage = error.localizedMessage
    if (error is NoMorePagesLeftToLoadException) {
        item {
            NoMoreDataToShow(
                message = error.localizedMessage
                    ?: stringResource(R.string.no_more_data_to_display)
            )
        }
    } else {
        item {
            ErrorMessage(
                errorMessage = errorMessage
                    ?: stringResource(R.string.error_occured),
                onRetry = onRetry
            )
        }
    }
}

@Composable
fun NoMoreDataToShow(message: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        text = message,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun NextPageLoaderItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorMessage(errorMessage: String, onRetry: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall
        )
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun SkeletonPlaceHolder(modifier: Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(all = 10.dp), verticalAlignment = Alignment.Top
    ) {
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.5f)
                        .background(Color.Gray)
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.5f)
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
@Preview
fun NextPageLoaderItemPreview() {
    StarWarsTheme {
        NextPageLoaderItem()
    }
}

@Composable
@Preview
fun ErrorPreview() {
    StarWarsTheme {
        ErrorMessage(
            errorMessage = stringResource(R.string.error_occured),
            onRetry = { /*TODO*/ }
        )
    }
}

@Composable
@Preview
fun SkeletonPreview() {
    SkeletonPlaceHolder(modifier = Modifier)
}
