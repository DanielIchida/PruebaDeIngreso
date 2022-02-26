package com.daniel.pruebadeingreso.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daniel.domain.Post
import com.daniel.domain.User
import com.daniel.posts.GetListPost
import com.daniel.pruebadeingreso.CoroutinesTestRule
import com.daniel.pruebadeingreso.ui.users.UserViewModel
import com.daniel.users.GetFindUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var listPostByUser: GetListPost

    @Mock
    lateinit var findUser: GetFindUser

    private lateinit var vm: PostViewModel

    private lateinit var user: User

    @Before
    fun setUp(){
        user = User("1","Daniel","danieljaime308@gmail.com","3102343567")
    }

    @Test
    fun `observing empty posts`(){
        runTest {
            Mockito.`when`(listPostByUser.invoke(user.id.toInt())).thenReturn(listOf())
            Mockito.`when`(findUser.invoke(user.id.toInt())).thenReturn(user)
            vm = PostViewModel(user.id,listPostByUser,findUser)
            Assert.assertSame(PostViewModel.UIModel.Empty,vm.model.value)
        }
    }

    @Test
    fun `observing list posts`(){
        runTest {
            val posts = mutableListOf<Post>()
            posts.add(Post(1,"Juegos","mas jugados","1"))
            Mockito.`when`(listPostByUser.invoke(1)).thenReturn(posts)
            Mockito.`when`(findUser.invoke(1)).thenReturn(user)
            vm = PostViewModel(user.id,listPostByUser,findUser)
            when(val model = vm.model.value){
                is PostViewModel.UIModel.Content -> {
                    Assert.assertEquals(posts,model.data)
                }
                else -> {}
            }
        }
    }


}