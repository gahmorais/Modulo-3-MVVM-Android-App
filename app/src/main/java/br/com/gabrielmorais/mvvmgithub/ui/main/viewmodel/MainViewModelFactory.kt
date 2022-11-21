package br.com.gabrielmorais.mvvmgithub.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gabrielmorais.mvvmgithub.data.api.ApiHelper
import br.com.gabrielmorais.mvvmgithub.data.repository.MainRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(MainViewModel::class.java)){
      return MainViewModel(mainRepository) as T
    }
    throw IllegalArgumentException("Não encontramos essa classe")
  }
}