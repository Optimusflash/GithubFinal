package com.optimus.githubfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.optimus.githubfinal.R
import com.optimus.githubfinal.di.Injector
import com.optimus.githubfinal.extensions.formatDate
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.viewmodels.DetailViewModel
import com.optimus.githubfinal.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import javax.inject.Inject


class RepoDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.getAppComponent().inject(this)
    }
    companion object {
        const val USER = "user"
        const val REPO = "repo"

        fun newInstance(user: String?, repo: String?): RepoDetailFragment {
            val bundle = Bundle().apply {
                putString(USER, user)
                putString(REPO, repo)
            }
            val fragment = RepoDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getString(USER) ?: ""
        val repo = arguments?.getString(REPO) ?: ""
        initViewModel(user, repo)
    }

    private fun initViewModel(user: String, repo: String) {
        detailViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        detailViewModel.getGitRepository(user, repo).observe(viewLifecycleOwner, Observer {
            updateViews(it)
        })
    }

    private fun updateViews(repo: GitRepository) {
        Glide.with(this)
            .load(repo.owner.avatarUrl)
            .into(detail_iv_avatar)
        detail_title.text = repo.name
        detail_description.text = repo.description
        detail_stars_count.text = repo.starsCount.toString()
        detail_forks_count.text = repo.forksCount.toString()
        detail_prog_language.text = repo.progLanguage
        detail_created_date.text = repo.createdDate.formatDate()
        detail_updated_date.text = repo.updatedDate.formatDate()
    }

}
