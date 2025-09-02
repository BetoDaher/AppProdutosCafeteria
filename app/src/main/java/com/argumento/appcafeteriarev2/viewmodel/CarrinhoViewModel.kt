package com.argumento.appcafeteriarev2.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumento.appcafeteriarev2.model.Carrinho
import com.argumento.appcafeteriarev2.model.Produto
import com.argumento.appcafeteriarev2.repositorio.CarrinhoRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarrinhoViewModel @Inject constructor(private val carrinhoRepository: CarrinhoRepositorio) : ViewModel() {

    var produtosCarrinho = mutableStateListOf<Carrinho>()
    var totalItens = mutableIntStateOf(0)
    var numMesa = mutableIntStateOf(0)

    fun getProdutos(
        response: (MutableList<Produto>) -> Unit
    )
    {
        viewModelScope.launch {
            carrinhoRepository.getProdutos(response)

        }
    }

    fun adicionarProdutoCarrinho(produto: Carrinho){

        produtosCarrinho.add(produto)
        totalItens.intValue++

    }

    fun removerProdutoCarrinho(produto: Carrinho){

        produtosCarrinho.remove(produto)
        totalItens.intValue--

    }

    fun calcularTotal(): Double {
        return produtosCarrinho.sumOf { it.preco!!.toDouble() }
    }

}