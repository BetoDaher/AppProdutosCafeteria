package com.argumento.appcafeteriarev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.argumento.appcafeteriarev2.screens.Carrinho
import com.argumento.appcafeteriarev2.screens.Home
import com.argumento.appcafeteriarev2.screens.Pagamento
import com.argumento.appcafeteriarev2.screens.SplashScreen
import com.argumento.appcafeteriarev2.viewmodel.CarrinhoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //desativo a barra de status
        //enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val carrinhoViewModel: CarrinhoViewModel = hiltViewModel()

            NavHost( startDestination = "splash",
                navController = navController) {

                composable("splash") {
                    SplashScreen(
                        navController = navController
                    )
                }

                composable("Home") {
                    Home(
                        navController = navController,
                        carrinhoViewModel = carrinhoViewModel
                    )
                }
                composable("Carrinho") {
                    Carrinho(
                        navController = navController,
                        carrinhoViewModel = carrinhoViewModel
                    )
                }

                composable("Pagamento") {
                    Pagamento(
                        carrinhoViewModel = carrinhoViewModel
                    )
                }
            }

        }
    }
}