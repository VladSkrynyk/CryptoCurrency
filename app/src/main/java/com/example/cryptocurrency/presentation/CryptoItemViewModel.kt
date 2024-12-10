package com.example.cryptocurrency.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.CryptoItem
import com.example.cryptocurrency.domain.usecases.GetItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoItemViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {

    private val _itemLiveData = MutableLiveData<CryptoItem>()
    val itemLiveData: LiveData<CryptoItem>
        get() = _itemLiveData

    private val _finishEditLiveData = MutableLiveData<Unit>()
    val finishEditLiveData: LiveData<Unit>
        get() = _finishEditLiveData

    fun getItem(id: Int) {
        viewModelScope.launch {
            val shopItem = getItemUseCase(id)
            _itemLiveData.value = shopItem
        }
    }
}
