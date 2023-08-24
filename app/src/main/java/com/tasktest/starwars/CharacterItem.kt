package com.tasktest.starwars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasktest.starwars.designsystem.icon.StarWarsIcons
import com.tasktest.starwars.feature.characters.CharacterUI
import com.tasktest.starwars.ui.theme.StarWarsTheme

@Composable
fun CharacterItem(
    character: CharacterUI,
    onCellClick: () -> Unit,
    modifier: Modifier
) {
    Column(modifier.fillMaxWidth()) {
        CharacterContent(character = character, onCellClick = { /*TODO*/ }, modifier = modifier)
        Divider()
    }
}

@Composable
fun CharacterContent(
    character: CharacterUI,
    onCellClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 21.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .weight(2.7f)
                .padding(bottom = 20.dp)
        ) {
            Icon(
                painter = painterResource(character.icon.resource),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(18.dp))
            CharacterSummary(
                name = character.name,
                height = character.height,
                mass = character.mass,
                modifier = modifier.fillMaxWidth()
            )
        }
        Icon(
            painter = painterResource(StarWarsIcons.Arrow),
            tint = character.eyeColor,
            contentDescription = null,
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun CharacterSummary(name: String, height: String, mass: String, modifier: Modifier) {
    Column(modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier.paddingFromBaseline(38.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.paddingFromBaseline(33.dp)
        ) {
            Text(
                text = stringResource(R.string.height, height),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.mass, mass),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun HMM() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize()) {
                for (i in (0..10)) {
                    CharacterItem(
                        character = CharacterUI(
                            name = "Chewbacca",
                            height = "167",
                            mass = "112",
                            icon = CharacterIconType.Alien
                        ),
                        onCellClick = { /*TODO*/ },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}