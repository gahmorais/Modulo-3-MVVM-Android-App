package br.com.gabrielmorais.mvvmgithub.data.api

import br.com.gabrielmorais.mvvmgithub.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {
  override fun getUsers(): Single<List<User>> {
    return Rx2AndroidNetworking
      .get("http://api.github.com/users/michaelTadeu/followers?page=1&per_page=1000")
      .build()
      .getObjectListSingle(User::class.java)
  }
}