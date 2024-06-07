package com.project1.inventarios.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.History
import com.project1.inventarios.repository.CardInventoryRepository
import com.project1.inventarios.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

    private val _inventories = MutableLiveData<List<History>?>()
    val inventories: LiveData<List<History>?> = _inventories

    fun getAllHistories(text:String?) = viewModelScope.launch {
        //_genres.postValue(Resource.Loading())
        try {
            if (text===null){
                _inventories.postValue(historyRepository.getAllHistory())
            }else{
                _inventories.postValue(historyRepository.getAllHistoryFromText(text))
            }
        } catch (e:Exception){
            null
        }
    }


    fun clear(){
        _inventories.value = null
    }
}