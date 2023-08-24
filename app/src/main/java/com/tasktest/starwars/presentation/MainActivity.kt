package com.tasktest.starwars.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tasktest.starwars.navigation.LocalNavController
import com.tasktest.starwars.navigation.StarWarsNavGraph
import com.tasktest.starwars.presentation.characters.StarWarsCharactersScreen
import com.tasktest.starwars.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHost = rememberNavController()
                    CompositionLocalProvider(LocalNavController provides navHost) {
                        StarWarsNavGraph()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarWarsPreView() {
    StarWarsTheme {
        StarWarsCharactersScreen()
    }
}