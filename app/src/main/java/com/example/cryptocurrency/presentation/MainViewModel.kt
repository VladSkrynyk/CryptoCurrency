package com.example.cryptocurrency.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptocurrency.domain.usecases.GetItemsUseCase
import com.example.cryptocurrency.domain.usecases.PostItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemUseCase: GetItemsUseCase,
    private val postItemsUseCase: PostItemsUseCase,

) : ViewModel() {

    val itemsLiveData
        get() = getItemUseCase()


    fun fetchData() {
//        retrofitObject.getAndroid() { response ->
//            Log.d("XXX", "fetchData:${response}") // ${response}
//        }
        CoroutineScope(Dispatchers.Default).launch {
            postItemsUseCase()
        }

    }

}