package com.argumento.appcafeteriarev2.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

private val TemaClaro = lightColorScheme(

    primary = BROWN400,
    onPrimary = BLACK80,
    secondary = WHITE,
    tertiary = BROWN500

)

private val TemaEscuro = darkColorScheme(

    primary = BROWN400,
    onPrimary = BLACK80,
    secondary = WHITE,
    tertiary = BROWN500

)

@Composable
fun MyTheme(
    theme: String,
    content: @Composable () -> Unit
){

    val colorScheme = when  {
        theme == "1" -> TemaClaro
        else -> TemaEscuro
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

    val window = (LocalContext.current as? Activity)?.window
    window?.statusBarColor = colorScheme.primary.toArgb()

}