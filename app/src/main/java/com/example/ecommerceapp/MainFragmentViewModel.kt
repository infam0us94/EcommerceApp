package com.example.ecommerceapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repos.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel: ViewModel() {

    val products = MutableLiveData<List<Product>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Default) {
            products.postValue(ProductRepository().fetchAllProductsRetrofit())
        }
    }

    fun search(term: String) {
        viewModelScope.launch(Dispatchers.Default) {
            products.postValue(ProductRepository().searchForProducts(term))
        }
    }
}