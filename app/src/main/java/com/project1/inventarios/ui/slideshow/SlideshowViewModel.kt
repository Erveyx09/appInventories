package com.project1.inventarios.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.repository.CardInventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val cardInventoryRepository: CardInventoryRepository
): ViewModel() {

    private val _inventories = MutableLiveData<Int?>()
    val inventories: LiveData<Int?> = _inventories

    fun putCardInventories(quantity:Int, id:Int) = viewModelScope.launch {
        //_genres.postValue(Resource.Loading())
        try {
            _inventories.postValue(cardInventoryRepository.putInventory(quantity, id))
        } catch (e:Exception){
            null
        }
    }

    fun clear(){
        _inventories.value = null


    }
}