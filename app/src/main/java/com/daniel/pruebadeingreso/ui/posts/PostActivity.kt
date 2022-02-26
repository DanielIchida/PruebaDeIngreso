package com.daniel.pruebadeingreso.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daniel.pruebadeingreso.R
import com.daniel.pruebadeingreso.databinding.ActivityPostBinding
import com.daniel.pruebadeingreso.databinding.DialogProgressBinding
import com.daniel.pruebadeingreso.ui.common.dialogPersonalize
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var adapter: PostAdapter
    private val viewModel: PostViewModel by viewModels()
    private lateinit var dialog: MaterialAlertDialogBuilder
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = MaterialAlertDialogBuilder(this)
        adapter = PostAdapter()
        binding.rcPosts.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.model.collect {
                    updateUI(it)
                }
            }
        }
    }

    fun updateUI(model: PostViewModel.UIModel){
        if(model == PostViewModel.UIModel.Loading) {
            val view = DialogProgressBinding.inflate(layoutInflater)
            alertDialog = dialog.dialogPersonalize(
                resources.getString(R.string.app_name),
                resources.getString(R.string.loading_post),
                view.root).show()
        } else alertDialog.dismiss()

        if(model == PostViewModel.UIModel.Empty) binding.txtEmptyPosts.visibility = View.VISIBLE else
            binding.txtEmptyPosts.visibility = View.GONE

        when(model){
            is PostViewModel.UIModel.Content -> adapter.posts = model.data
            is PostViewModel.UIModel.FindUser -> {
                binding.txtName.text = model.user.name
                binding.txtEmail.text = model.user.email
                binding.txtPhone.text = model.user.phone
            }
            else -> {}
        }
    }


}