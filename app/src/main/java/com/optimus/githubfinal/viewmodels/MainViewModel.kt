package com.optimus.githubfinal.viewmodels

import android.util.AndroidException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */
class MainViewModel: ViewModel() {

    private val mainRepository = MainRepository()
    private val repositories = MutableLiveData<List<GitRepository>>()
    private var disposeBag: CompositeDisposable? = CompositeDisposable()

    fun getGitRepositories(repoName: String): LiveData<List<GitRepository>> {
        val disposable = mainRepository.loadGitRepositoriesFromApi(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
}