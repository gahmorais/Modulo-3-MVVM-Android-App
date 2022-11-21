package br.com.gabrielmorais.mvvmgithub.ui.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gabrielmorais.mvvmgithub.data.api.ApiHelper
import br.com.gabrielmorais.mvvmgithub.data.api.ApiServiceImpl
import br.com.gabrielmorais.mvvmgithub.data.model.User
import br.com.gabrielmorais.mvvmgithub.data.repository.MainRepository
import br.com.gabrielmorais.mvvmgithub.databinding.ActivityMainBinding
import br.com.gabrielmorais.mvvmgithub.ui.main.adapter.MainAdapter
import br.com.gabrielmorais.mvvmgithub.ui.main.viewmodel.MainViewModel
import br.com.gabrielmorais.mvvmgithub.ui.main.viewmodel.MainViewModelFactory
import br.com.gabrielmorais.mvvmgithub.utils.Status

class MainActivity : AppCompatActivity() {
  private lateinit var mainViewModel: MainViewModel
  private var adapter: MainAdapter = MainAdapter(arrayListOf())

  private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
    if (it) {
      Log.i("MainActivity", ": Permissão concedida")
    } else {
      Log.i("MainActivity", ": Permissão negada")
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setupUI()
    setupViewModel()
    setupObserver()

    if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
      permission.launch(Manifest.permission.INTERNET)
    }

  }

  private fun setupUI() {

    binding.apply {
      rvItems.addItemDecoration(
        DividerItemDecoration(
          rvItems.context,
          (rvItems.layoutManager as LinearLayoutManager).orientation
        )
      )
      rvItems.adapter = adapter
    }

  }

  private fun setupObserver() {
    mainViewModel.getUsers().observe(this) {
      when (it.status) {
        Status.SUCCESS -> {
          binding.apply {
            progressBar.visibility = View.GONE
            it.data?.let { users -> renderList(users) }
            rvItems.visibility = View.VISIBLE
          }
        }
        Status.LOADING -> {
          binding.apply {
            progressBar.visibility = View.VISIBLE
            it.data?.let { users -> renderList(users) }
            rvItems.visibility = View.GONE
          }
        }
        Status.ERROR -> {
          binding.progressBar.visibility = View.GONE
          Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
      }
    }
  }

  private fun renderList(users: List<User>) {
    adapter.addData(users)
    adapter.notifyDataSetChanged()
  }

  private fun setupViewModel() {
    val mainRepository = MainRepository(ApiHelper(ApiServiceImpl()))
    val factory = MainViewModelFactory(mainRepository)
    mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
  }
}
