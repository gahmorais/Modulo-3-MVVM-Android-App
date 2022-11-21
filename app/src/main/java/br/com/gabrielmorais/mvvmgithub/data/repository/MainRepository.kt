package br.com.gabrielmorais.mvvmgithub.data.repository

import br.com.gabrielmorais.mvvmgithub.data.api.ApiHelper
import br.com.gabrielmorais.mvvmgithub.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
  fun getUsers(): Single<List<User>> {
    return apiHelper.getUsers()
  }
}