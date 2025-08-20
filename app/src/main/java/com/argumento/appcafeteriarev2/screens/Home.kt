package com.argumento.appcafeteriarev2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.argumento.appcafeteriarev2.itemlista.ProdutoItem
import com.argumento.appcafeteriarev2.model.Produto
import com.argumento.appcafeteriarev2.ui.theme.BLACK80
import com.argumento.appcafeteriarev2.ui.theme.MyTheme
import com.argumento.appcafeteriarev2.ui.theme.RED
import com.argumento.appcafeteriarev2.ui.theme.WHITE
import com.argumento.appcafeteriarev2.viewmodel.CarrinhoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    carrinhoViewModel: CarrinhoViewModel
){

    var listaProdutos by remember { mutableStateOf(mutableListOf<Produto>()) }
    var produtosPesquisados by remember { mutableStateOf(mutableListOf<Produto>()) }
    var pesquisar by remember { mutableStateOf("") }

    listaProdutos = if(pesquisar.isNotEmpty()){
        produtosPesquisados.filter { produto ->
            produto.nome!!.contains(pesquisar, ignoreCase = true)
        }.toMutableList()
    }else{
        produtosPesquisados
    }

    LaunchedEffect(Unit){
        carrinhoViewModel.getProdutos { produtos ->
            listaProdutos = produtos
            produtosPesquisados = produtos
        }
    }

    MyTheme(theme = "1") {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.height(100.dp),
                    actions = {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            OutlinedTextField(
                                value = pesquisar,
                                onValueChange = {
                                    pesquisar = it
                                                },
                                label = {
                                    Text(text = "Pesquisar...")
                                },
                                modifier = Modifier
                                    .width(300.dp)
                                    .padding(10.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text
                                ),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = WHITE,
                                    focusedContainerColor = WHITE,
                                    focusedLabelColor = WHITE,
                                    unfocusedLabelColor = BLACK80,
                                    focusedIndicatorColor = WHITE
                                ),
                                maxLines = 1,
                                shape = RoundedCornerShape(30.dp)

                            )

                            Icon(
                                imageVector = Icons.Rounded.ShoppingCart,
                                contentDescription = "Carrinho",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(30.dp).clickable {
                                    if(carrinhoViewModel.totalItens.intValue > 0){
                                    navController.navigate("Carrinho")}
                                }
                            )


                            if(carrinhoViewModel.totalItens.intValue > 0){
                                Text(
                                    text = carrinhoViewModel.totalItens.intValue.toString(),
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .background(
                                            color = RED,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .size(30.dp),
                                    textAlign = TextAlign.Center
                                )
                            }


                        }
                    }

                )
            }
        ){paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(paddingValues)
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
                ) {
                    itemsIndexed(listaProdutos){_, produto ->
                        ProdutoItem(produto = produto, carrinhoViewModel = carrinhoViewModel)
                    }
                }
            }
        }

    }

}
