package com.optimus.githubfinal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */
@Singleton
class DetailViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var disposeBag: CompositeDisposable? = CompositeDisposable()
    private val gitRepository = MutableLiveData<GitRepository>()

    fun getGitRepository(user: String, repo: String): LiveData<GitRepository> {
        repository.loadGitRepositoryFromDb(user, repo).observeForever {
            it ?: return@observeForever
            gitRepository.value = it
        }
        return gitRepository
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag?.clear()
        disposeBag = null
    }
}