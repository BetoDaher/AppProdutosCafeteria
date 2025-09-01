package com.argumento.appcafeteriarev2.screens

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argumento.appcafeteriarev2.ui.theme.MyTheme
import com.argumento.appcafeteriarev2.ui.theme.RED
import com.argumento.appcafeteriarev2.viewmodel.CarrinhoViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun Pagamento(
    carrinhoViewModel: CarrinhoViewModel
){
    val numeroMesa: Int = 3
    val total = carrinhoViewModel.calcularTotal()
    val horaAtual = getCurrentFormattedDateTime()
    val produtos = carrinhoViewModel.produtosCarrinho



    MyTheme(theme = "1") {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = "Mesa nº ${numeroMesa}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = horaAtual,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Valor total: ${DecimalFormat.getCurrencyInstance().format(total)}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 30.sp
            )

            LazyColumn (
                modifier = Modifier.padding(10.dp)
            ){
                itemsIndexed(produtos){_, produto ->
                    Column(
                        modifier = Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(15.dp)
                        ).padding(5.dp)
                    ){
                        Text(
                            text = produto.nome!!,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp,5.dp)
                        )

                        Text(
                            text = "Quantidade: ${produto.quantidade}",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp,5.dp)
                        )



                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                }
            }

        }
    }

}

private fun getCurrentFormattedDateTime(): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(calendar.time)
}

@Composable
fun CurrentDateTimeText(updateIntervalMillis: Long = 1000L) {
    var currentDateTime by remember { mutableStateOf(getCurrentFormattedDateTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime = getCurrentFormattedDateTime()
            delay(updateIntervalMillis)
        }
    }

    Text(
        text = currentDateTime,
        color = MaterialTheme.colorScheme.onPrimary, // Use a cor do seu tema
        fontSize = 16.sp // Ajuste o tamanho conforme necessário
    )
}

