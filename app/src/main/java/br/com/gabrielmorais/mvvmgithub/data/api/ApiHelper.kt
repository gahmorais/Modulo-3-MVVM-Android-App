package br.com.gabrielmorais.mvvmgithub.data.api

class ApiHelper(private val apiService: ApiService) {
  fun getUsers() = apiService.getUsers()
}