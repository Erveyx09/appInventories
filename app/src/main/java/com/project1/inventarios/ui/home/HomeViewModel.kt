package com.project1.inventarios.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.repository.CardInventoryRepository
import com.project1.inventarios.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cardInventoryRepository: CardInventoryRepository
): ViewModel() {

    private val _inventories = MutableLiveData<List<CardInventory>?>()
    val inventories: LiveData<List<CardInventory>?> = _inventories

    private val _inventoriesUpdate = MutableLiveData<Int?>()
    val inventoriesUpdate: LiveData<Int?> = _inventoriesUpdate

    fun getAllCardInventories(text:String?) = viewModelScope.launch {
        //_genres.postValue(Resource.Loading())
        try {
            if (text===null){
                _inventories.postValue(cardInventoryRepository.getAllInventory())
            }else{
                _inventories.postValue(cardInventoryRepository.getAllInventoryFromText(text))
            }
        } catch (e:Exception){
            null
        }
    }

    fun putCardInventories(quantity:Int, id:Int) = viewModelScope.launch {
        try {
            _inventoriesUpdate.postValue(cardInventoryRepository.putInventory(quantity, id))
        } catch (e:Exception){
            null
        }
    }

    fun clear(){
        _inventories.value = null
        _inventoriesUpdate.value = null
    }
}