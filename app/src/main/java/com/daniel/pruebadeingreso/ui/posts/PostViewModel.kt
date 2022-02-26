package com.daniel.pruebadeingreso.ui.posts

import androidx.lifecycle.viewModelScope
import com.daniel.domain.Post
import com.daniel.domain.User
import com.daniel.posts.GetListPost
import com.daniel.pruebadeingreso.ui.common.ScopedViewModel
import com.daniel.pruebadeingreso.ui.users.UserViewModel
import com.daniel.users.GetFindUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PostViewModel @Inject constructor(
    @Named("userId") private val userId: String,
    private val listPostByUser: GetListPost,
    private val findUser: GetFindUser
) : ScopedViewModel(){

    private val _model = MutableStateFlow<UIModel>(UIModel.Loading)
    val model: StateFlow<UIModel> = _model

    init {
        initScope()
        viewModelScope.launch {
            _model.value = UIModel.Loading
            val user = findUser.invoke(userId.toInt())
            _model.value = UIModel.FindUser(user)
            val posts = listPostByUser.invoke(userId.toInt())
            if(posts.isEmpty()){
               _model.value = UIModel.Empty
            }else{
                _model.value = UIModel.Content(posts.toMutableList())
            }
        }
    }

    sealed class UIModel{
        object Loading : UIModel()
        object Empty : UIModel()
        class FindUser(val user: User) : UIModel()
        class Content(val data: MutableList<Post>) : UIModel()
    }

}