package com.optimus.githubfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.githubfinal.R
import com.optimus.githubfinal.di.Injector
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.ui.activities.MainActivity
import com.optimus.githubfinal.ui.adapters.RepoAdapter
import com.optimus.githubfinal.viewmodels.MainViewModel
import com.optimus.githubfinal.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_book_list.*
import javax.inject.Inject


class RepoListFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repoListAdapter: RepoAdapter
    private var listener: OnRepoListItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.getAppComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? OnRepoListItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        repoListAdapter = RepoAdapter {
            listener?.onRepoListItemClick(it)
        }
        recycler_view_repo.layoutManager = LinearLayoutManager(activity)
        recycler_view_repo.adapter = repoListAdapter
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getSearchString().observe(viewLifecycleOwner, Observer {
            Log.e("M_RepoListFragment", "invoke search $it")
            mainViewModel.getGitRepositories(it)
        })

        mainViewModel.getRepositories().observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            repoListAdapter.updateData(it)
        })
    }

    interface OnRepoListItemClickListener {
        fun onRepoListItemClick(gitRepository: GitRepository)
    }
}



