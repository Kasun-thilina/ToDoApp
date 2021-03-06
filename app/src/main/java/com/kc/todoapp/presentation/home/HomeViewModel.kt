package com.kc.todoapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.usecase.TodoUseCase
import com.kc.todoapp.util.extensions.setError
import com.kc.todoapp.util.extensions.setLoading
import com.kc.todoapp.util.extensions.setSuccess
import com.kc.todoapp.util.network.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val todoUseCase: TodoUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val todoListLiveData = MutableLiveData<Resource<List<DTodo>>>()
    val deleteTodoLiveData = MutableLiveData<Resource<DTodo>>()
    val updateTodoLiveData = MutableLiveData<Resource<DTodo>>()
    var isLoading = false

    fun getTodoList() {
        todoListLiveData.setLoading()
        compositeDisposable.add(
            todoUseCase.getTodoList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (!it.todoList.isNullOrEmpty()) {
                        todoListLiveData.setSuccess(it.todoList, null)
                    }
                }, {
                    todoListLiveData.setError(it.message)
                }
                )
        )
    }

    fun deleteTodo(id: Int) {
        deleteTodoLiveData.setLoading()
        compositeDisposable.add(
            todoUseCase.deleteTodo(id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    deleteTodoLiveData.setSuccess(it, null)
                }, {
                    deleteTodoLiveData.setError(it.message)
                }
                )
        )
    }

    fun updateTodo(todo: DTodo) {
        updateTodoLiveData.setLoading()
        compositeDisposable.add(
            todoUseCase.updateTodo(todo, todo.id!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    updateTodoLiveData.setSuccess(it, null)
                }, {
                    updateTodoLiveData.setError(it.message)
                }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}