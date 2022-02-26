package com.daniel.pruebadeingreso.ui.users

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.daniel.domain.User
import com.daniel.pruebadeingreso.R
import com.daniel.pruebadeingreso.databinding.ViewItemUserBinding
import com.daniel.pruebadeingreso.ui.common.inflate
import com.daniel.pruebadeingreso.ui.common.mutableBasicDiffUtil
import java.util.*

class UserAdapter(private val listener: (User) -> (Unit)) : RecyclerView.Adapter<UserAdapter.ViewHolder>() ,
                                                            Filterable{
    var users: MutableList<User> by mutableBasicDiffUtil(
        mutableListOf(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    var usersFilters: MutableList<User> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
         private var binding = ViewItemUserBinding.bind(view)
         fun bind(user: User) = with(binding){
             binding.txtItemName.text = user.name
             binding.txtItemEmail.text = user.email
             binding.txtItemPhone.text = user.phone
         }
         fun getBinding() = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_item_user, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersFilters[position]
        holder.bind(user)
        holder.getBinding().txtLinkPost.setOnClickListener { listener(user) }
    }

    override fun getItemCount(): Int {
        return usersFilters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDataUser(list: MutableList<User>){
        users = list
        usersFilters = users
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constrains: CharSequence?): FilterResults {
                val charString = constrains?.toString() ?: ""
                if(charString.isEmpty()) usersFilters  = users else {
                    val filterList = mutableListOf<User>()
                    users.filter {
                                (it.name.contains(constrains!!)) or
                                        (it.name.lowercase(Locale.getDefault()).contains(constrains))
                    }.forEach { filterList.add(it) }

                    usersFilters = filterList
                }
                return FilterResults().apply { values = usersFilters }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constrains: CharSequence?, results: FilterResults?) {
                usersFilters = if(results?.values == null)
                    mutableListOf()
                else
                    results.values as MutableList<User>
                notifyDataSetChanged()
            }

        }
    }



}