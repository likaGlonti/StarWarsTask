package com.tasktest.starwars.feature.characters

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
import com.tasktest.starwars.CharacterItem
import com.tasktest.starwars.CharacterUI
import com.tasktest.starwars.R
import com.tasktest.starwars.data.error.NoMorePagesLeftToLoadException
import com.tasktest.starwars.ui.theme.StarWarsTheme

@Composable
fun StarWarsCharactersList(
    characterItems: LazyPagingItems<CharacterUI>,
    onRetry: () -> Unit,
    navigate: () -> Unit,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier)
    {
        items(characterItems.itemCount) { index ->
            characterItems[index]?.let {
                CharacterItem(character = it, onCellClick = navigate, modifier = modifier)
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
                            onRetry = onRetry,
                            modifier = modifier
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { NextPageLoaderItem(modifier = modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = (characterItems.loadState.append as LoadState.Error).error
                    val errorMessage = error.localizedMessage
                    if (error is NoMorePagesLeftToLoadException) {
                        item {
                            NoMoreDataToShow(
                                message = error.localizedMessage
                                    ?: stringResource(R.string.no_more_data_to_display),
                                modifier
                            )
                        }
                    } else {
                        item {
                            ErrorMessage(
                                errorMessage = errorMessage
                                    ?: stringResource(R.string.error_occured),
                                onRetry = onRetry,
                                modifier = modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoMoreDataToShow(message: String, modifier: Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        text = message,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun NextPageLoaderItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
@Preview
fun NextPageLoaderItemPreview() {
    StarWarsTheme {
        NextPageLoaderItem(modifier = Modifier)
    }
}

@Composable
fun ErrorMessage(errorMessage: String, onRetry: () -> Unit, modifier: Modifier) {
    Column(
        modifier
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
@Preview
fun ErrorPreview() {
    StarWarsTheme {
        ErrorMessage(
            errorMessage = stringResource(R.string.error_occured),
            onRetry = { /*TODO*/ },
            modifier = Modifier
        )
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
fun SkeletonPreview() {
    SkeletonPlaceHolder(modifier = Modifier)
}
