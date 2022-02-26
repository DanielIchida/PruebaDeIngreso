package com.daniel.pruebadeingreso.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daniel.domain.User
import com.daniel.pruebadeingreso.CoroutinesTestRule
import com.daniel.users.GetListUsers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var listUsers: GetListUsers


    private lateinit var vm: UserViewModel


    @Test
    fun `observing empty`(){
        runTest {
            Mockito.`when`(listUsers.invoke()).thenReturn(listOf())
            vm = UserViewModel(listUsers)
            Assert.assertSame(UserViewModel.UIModel.Empty,vm.model.value)
        }
    }

    @Test
    fun `observing data`(){
        runTest {
            val list = mutableListOf<User>()
            list.add(User("1","Daniel","danieljaime308@gmail.com","3102343567"))
            Mockito.`when`(listUsers.invoke()).thenReturn(list)
            vm = UserViewModel(listUsers)
            when(val model = vm.model.value){
                is UserViewModel.UIModel.Content -> {
                    Assert.assertSame(list,model.data)
                }
                else -> {}
            }
        }
    }

    @Test
    fun `observing navigation`() {
       runTest {
           val list = mutableListOf<User>()
           val user = User("1","Daniel","danieljaime308@gmail.com","3102343567")
           list.add(user)
           Mockito.`when`(listUsers.invoke()).thenReturn(list)
           vm = UserViewModel(listUsers)
           vm.onClickNavigation(user)
           when(val model = vm.model.value){
               is UserViewModel.UIModel.Navigation -> {
                   Assert.assertSame(user,model.user)
               }
               else -> {}
           }
       }
    }


}