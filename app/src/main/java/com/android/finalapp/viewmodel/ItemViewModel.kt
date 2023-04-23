package com.android.finalapp.viewmodel

import androidx.lifecycle.*
import com.android.finalapp.model.Item
import com.android.finalapp.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    fun getAllItems(): List<Item> {
        return itemRepository.getAllItems()
    }

    private val _itemLiveData = MutableLiveData<Item?>()
    val itemLiveData: LiveData<Item?> = _itemLiveData

    fun getItemById(id: Int) {
        viewModelScope.launch {
            val item = itemRepository.getItemById(id)
            _itemLiveData.postValue(item)
        }
    }
}