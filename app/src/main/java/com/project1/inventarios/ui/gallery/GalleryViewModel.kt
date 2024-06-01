package com.project1.inventarios.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel  @Inject constructor(
    private val inventoryRepository: InventoryRepository
): ViewModel() {

    private val _inventories = MutableLiveData<Int?>()
    val inventories: LiveData<Int?> = _inventories

    fun insertInventory(inventory: Inventory) = viewModelScope.launch {
        //_genres.postValue(Resource.Loading())
        try {
            _inventories.postValue(inventoryRepository.insertInventory(inventory))
        } catch (e:Exception){
            null
        }
    }

    /*fun updatedInventory(inventory: Inventory) = viewModelScope.launch {
        //_genres.postValue(Resource.Loading())
        try {
            _inventories.postValue(inventoryRepository.insertInventory(inventory))
        } catch (e:Exception){
            null
        }
    }*/

    fun clear(){
        _inventories.value = null
    }
}