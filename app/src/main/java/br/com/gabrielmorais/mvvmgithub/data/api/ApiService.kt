package br.com.gabrielmorais.mvvmgithub.data.api

import br.com.gabrielmorais.mvvmgithub.data.model.User
import io.reactivex.Single

interface ApiService {
  fun getUsers(): Single<List<User>>
}
