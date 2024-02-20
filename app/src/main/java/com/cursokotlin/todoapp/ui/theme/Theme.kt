package com.cursokotlin.todoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.todoapp.ui.theme.Pink40
import com.example.todoapp.ui.theme.Purple80
import com.example.todoapp.ui.theme.PurpleGrey40
import com.example.todoapp.ui.theme.PurpleGrey80

private val DarkColorPalette = darkColorScheme(
    primary = Purple80,
    secondary = Pink40,
)

private val LightColorPalette = lightColorScheme(
    primary = PurpleGrey80,
    secondary = PurpleGrey40,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TodoAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
