package com.argumento.appcafeteriarev2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.argumento.appcafeteriarev2.R
import com.argumento.appcafeteriarev2.ui.theme.BROWN
import com.argumento.appcafeteriarev2.ui.theme.BROWN400
import com.argumento.appcafeteriarev2.ui.theme.WHITE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){

    LaunchedEffect(
        key1 = true
    ) {
        delay(3000)
        navController.navigate("Home"){
            popUpTo("splash"){
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(color = BROWN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Spacer(modifier = Modifier.size(150.dp))

        Image(painter = painterResource(R.drawable.logo)
            , contentDescription = null,
            modifier = Modifier.size(250.dp))

        Text(
            text = "Cafeteria",
            fontSize = 22.sp,
            color = WHITE
        )

        Spacer(modifier = Modifier.size(150.dp))

        Text(
            text = "Argumento Consultoria",
            color = BROWN400
        )
    }


}

@Preview
@Composable
private fun SplashScreenPreview(){
    SplashScreen(navController = rememberNavController())

}
