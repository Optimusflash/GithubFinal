package com.optimus.githubfinal.viewmodels

import android.util.AndroidException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.githubfinal.extensions.mutableLiveData
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.repository.MainRepository
import com.optimus.githubfinal.ui.fragments.RepoListFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    private var repositories=MutableLiveData<List<GitRepository>>()
    private val search = MutableLiveData<String>()
    private val isLoading = mutableLiveData(false)
    private var disposeBag: CompositeDisposable? = CompositeDisposable()

    fun getGitRepositories(repoName: String) {
        Log.e("M_MainViewModel", "getGitRepositories")
            val disposable = mainRepository.loadGitRepositoriesFromApi(repoName)
                .flatMapCompletable {
                    Log.e("M_MainViewModel", "${it.gitRepositories}")
                    mainRepository.saveGitRepoToDb(it.gitRepositories)
                    return@flatMapCompletable Completable.complete()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.value = true }
                .doOnComplete { isLoading.value = false }
                .subscribe({
                    Log.e("M_MainViewModel", "complete...")
                    mainRepository.loadGitRepositoriesFromDb().observeForever {
                        repositories.value = it
                    }
                }, {
                    Log.e("M_MainViewModel", "something was wrong... ")
                })
            disposeBag?.add(disposable)
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
    fun getRepositories() = repositories
}