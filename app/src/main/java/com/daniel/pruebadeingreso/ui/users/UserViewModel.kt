package com.daniel.pruebadeingreso.ui.users

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.daniel.domain.User
import com.daniel.pruebadeingreso.ui.common.ScopedViewModel
import com.daniel.users.GetListUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val listUsers: GetListUsers
) : ScopedViewModel() {

    private val _model = MutableStateFlow<UIModel>(UIModel.Loading)
    val model: StateFlow<UIModel> = _model

    sealed class UIModel{
        object Loading : UIModel()
        class Content(val data: List<User>) : UIModel()
        class Navigation(val user: User) : UIModel()
        class FilterData(val query: String,val data: List<User>) : UIModel()
        object Empty : UIModel()
    }

    init {
        initScope()
        viewModelScope.launch {
            _model.value = UIModel.Loading
            val users = listUsers.invoke()
            if(users.isEmpty()){
                _model.value = UIModel.Empty
            }else{
                _model.value = UIModel.Content(users)
            }
        }
    }

    fun onClickNavigation(user: User){
        _model.value = UIModel.Navigation(user)
    }

    fun onTextFilter(query: String,data: MutableList<User>){
        _model.value = UIModel.FilterData(query,data)
        if(data.size <= 0){
            _model.value = UIModel.Empty
        }
    }



}