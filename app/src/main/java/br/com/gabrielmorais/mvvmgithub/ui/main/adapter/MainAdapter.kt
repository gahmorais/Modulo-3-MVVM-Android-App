package br.com.gabrielmorais.mvvmgithub.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmorais.mvvmgithub.data.model.User
import br.com.gabrielmorais.mvvmgithub.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide

class MainAdapter(private val users: ArrayList<User>) :
  RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
  private lateinit var binding: ItemLayoutBinding

  inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
      binding.apply {
        tvUserName.text = user.login
        tvUserEmail.text = user.url
        Glide.with(ivAvatar.context)
          .load(user.avatar)
          .into(ivAvatar)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
    binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return DataViewHolder(binding.root)
  }

  override fun getItemCount(): Int = users.size

  override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
    holder.bind(users[position])

  fun addData(list: List<User>) {
    users.addAll(list)
  }
}