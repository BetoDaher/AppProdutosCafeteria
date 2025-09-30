package com.argumento.appcafeteriarev2.itemlista

import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argumento.appcafeteriarev2.model.Carrinho
import com.argumento.appcafeteriarev2.model.Produto
import com.argumento.appcafeteriarev2.viewmodel.CarrinhoViewModel

@Composable
fun ProdutoItem(
    produto: Produto,
    carrinhoViewModel: CarrinhoViewModel
){
    val precoUnitario = produto.preco!!.toDouble()
    var quantidadeProduto by rememberSaveable { mutableIntStateOf(1) }
    var novoPreco by rememberSaveable { mutableDoubleStateOf(precoUnitario) }
    //var novoPreco by remember { mutableDoubleStateOf(precoUnitario) }
    //var quantidadeProduto by remember { mutableIntStateOf(1) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ), shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .width(190.dp)
                .height(400.dp)
                .padding(8.dp)
        ) {

            Image(
                painter = painterResource(  produto.foto!!),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "${produto.nome}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                modifier = Modifier.padding(10.dp).height(60.dp),
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){

                Text(
                    text = "+",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    ).padding(10.dp).size(20.dp).clickable {
                        if(quantidadeProduto < 50){
                            quantidadeProduto++
                            novoPreco = precoUnitario * quantidadeProduto
                        }
                    },
                    textAlign = TextAlign.Center
                )
                Text(
                    text = quantidadeProduto.toString(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(10.dp,0.dp)
                )
                Text(
                    text = "-",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    ).padding(10.dp).size(20.dp).clickable {
                        if(quantidadeProduto > 1){
                            quantidadeProduto--
                            novoPreco = precoUnitario * quantidadeProduto
                        }
                    },
                    textAlign = TextAlign.Center
                )

            }
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){ Text(
                //text = "R$ ${produto.preco}",
                text = DecimalFormat.getCurrencyInstance().format(novoPreco),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            ) }



            Button(
                onClick = {
                    carrinhoViewModel.adicionarProdutoCarrinho(
                        produto = Carrinho(
                            nome = produto.nome.toString(),
                            preco = novoPreco.toString(),
                            quantidade = quantidadeProduto.toString()
                        )
                    )

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Text("Carrinho")
            }

        }
    }

}