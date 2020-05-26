package com.optimus.githubfinal.viewmodels

import android.util.AndroidException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.repository.MainRepository
import com.optimus.githubfinal.ui.fragments.RepoListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */
@Singleton
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {


    private val repositories = MutableLiveData<List<GitRepository>>()
    private val search = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>()
    private var disposeBag: CompositeDisposable? = CompositeDisposable()

    fun getGitRepositories(repoName: String): LiveData<List<GitRepository>> {
        val disposable = mainRepository.loadGitRepositoriesFromApi(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doAfterSuccess { isLoading.value = false }
            .subscribe({
                repositories.value = it.gitRepositories
            }, {
                Log.e("M_MainViewModel", "something was wrong... Bitch")
            })
        disposeBag?.add(disposable)
        return repositories
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag?.clear()
        disposeBag = null
    }

    fun handleSearchString(query: String?) {
        search.value = query
    }

    fun getSearchString() = search
    fun getLoadingState() = isLoading

}