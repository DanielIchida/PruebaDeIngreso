package com.daniel.pruebadeingreso.ui.users

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daniel.pruebadeingreso.R
import com.daniel.pruebadeingreso.databinding.ActivityMainBinding
import com.daniel.pruebadeingreso.databinding.DialogProgressBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.daniel.pruebadeingreso.ui.common.dialogPersonalize
import com.daniel.pruebadeingreso.ui.common.startActivity
import com.daniel.pruebadeingreso.ui.posts.PostActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.cancelChildren

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , TextWatcher{

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    private lateinit var dialog: MaterialAlertDialogBuilder
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = MaterialAlertDialogBuilder(this)
        adapter = UserAdapter(viewModel::onClickNavigation)
        binding.rcUsers.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.model.collect() {
                    updateUI(it)
                }
            }
        }
        binding.etSearchUser.editText?.addTextChangedListener(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUI(model: UserViewModel.UIModel){
        if(model == UserViewModel.UIModel.Loading) {
            val view = DialogProgressBinding.inflate(layoutInflater)
            alertDialog = dialog.dialogPersonalize(
                resources.getString(R.string.app_name),
                resources.getString(R.string.loading_users),
                view.root).show()
        } else alertDialog.dismiss()
        if(model == UserViewModel.UIModel.Empty) binding.txtEmptyUser.visibility = View.VISIBLE else
            binding.txtEmptyUser.visibility = View.GONE
        when(model){
            is UserViewModel.UIModel.Content -> {
                adapter.addDataUser(model.data.toMutableList())
                adapter.notifyDataSetChanged()
            }
            is UserViewModel.UIModel.Navigation -> startActivity<PostActivity> {
                putExtra("USERID",model.user.id)
            }
            is UserViewModel.UIModel.FilterData -> {
                adapter.filter.filter(model.query)
            }
            else -> {}
        }
    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        eventFilter(s.toString())
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        eventFilter(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
        eventFilter(s.toString())
    }

    private fun eventFilter(query: String){
        viewModel.onTextFilter(query,adapter.usersFilters)
        Log.d("FILTER LIST","${adapter.usersFilters.size}")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.coroutineContext.cancelChildren()
    }
}