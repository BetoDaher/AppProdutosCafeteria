package com.argumento.appcafeteriarev2.datasource

import com.argumento.appcafeteriarev2.R
import com.argumento.appcafeteriarev2.model.Produto
import javax.inject.Inject

class DataSource @Inject constructor(

) {

    fun getProdutos(
        response: (MutableList<Produto>) -> Unit
    )
     {

          val listaProdutos = mutableListOf<Produto>(

              Produto(
                  foto = R.drawable.cappuccino_chocolate,
                  nome = "Capuccino Gelado Sabor Chocolate",
                  preco = "25.00"
              ),
              Produto(
                  foto = R.drawable.cappuccino_pistache,
                  nome = "Capuccino com Pistache",
                  preco = "22.99"
              ),
              Produto(
                  foto = R.drawable.bolo_morango,
                  nome = "Bolo de Morango (Fatia)",
                  preco = "30.90"
              ),
              Produto(
                  foto = R.drawable.pudim,
                  nome = "Pudim de Leite (Fatia)",
                  preco = "16.00"
              ),
              Produto(
                  foto = R.drawable.cappuccino_tradicional,
                  nome = "Capuccino Tradicional",
                  preco = "15.00"
              ),
              Produto(
                  foto = R.drawable.cafe_expresso,
                  nome = "Café Expresso",
                  preco = "9.90"
              )
          )
        response(listaProdutos)
    }

}