package hr.algebra.toppop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.toppop.framework.SharedRepository
import hr.algebra.toppop.models.DataX
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val repository= SharedRepository()

    private val _songByIdLiveData=MutableLiveData<DataX>()
    val songByLiveData : MutableLiveData<DataX> = _songByIdLiveData

    fun refreshSong(id:Int)=viewModelScope.launch{
        viewModelScope.launch {
            val response=repository.getSongById(id)
            _songByIdLiveData.postValue(response)
        }
    }
}