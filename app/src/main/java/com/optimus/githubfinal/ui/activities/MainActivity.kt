package com.optimus.githubfinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.optimus.githubfinal.R
import com.optimus.githubfinal.di.Injector
import com.optimus.githubfinal.extensions.hideKeyboard
import com.optimus.githubfinal.extensions.isKeyboardOpen
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.ui.fragments.RepoDetailFragment
import com.optimus.githubfinal.ui.fragments.RepoListFragment
import com.optimus.githubfinal.viewmodels.MainViewModel
import com.optimus.githubfinal.viewmodels.ViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RepoListFragment.OnRepoListItemClickListener {

    private var isLandscape = false
    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var userName: String? = null
    private var repoName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.getAppComponent().inject(this)
        if (savedInstanceState == null)  hint_placeholder.visibility = View.VISIBLE
        initFragment(savedInstanceState)
        initToolBar()
        initViewModel()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getLoadingState().observe(this, Observer {
            updateViews(it)
        })
    }

    private fun updateViews(isLoading: Boolean) {
        if (isLoading){
            hint_placeholder.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
            list_repo_container.visibility = View.GONE
        } else {
            progress_bar.visibility = View.GONE
            list_repo_container.visibility = View.VISIBLE
        }
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (findViewById<View>(R.id.details_repo_container)!=null){
            isLandscape=true
        }
        if (savedInstanceState==null){
            val listFragment = RepoListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.list_repo_container, listFragment)
                .commit()
        } else{
            if (isLandscape){
                userName= savedInstanceState.getString(RepoDetailFragment.USER)
                repoName=savedInstanceState.getString(RepoDetailFragment.REPO)
                showDetailFragment()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchMenuItem = menu?.findItem(R.id.action_search)
        val searchView = searchMenuItem?.actionView as SearchView
        searchView.queryHint = "Название репозитория"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query.isNullOrBlank()) {
                    false
                } else {
                    mainViewModel.handleSearchString(query)
                    if(this@MainActivity.isKeyboardOpen()){
                        this@MainActivity.hideKeyboard()
                    }
                    true
                }
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            Toast.makeText(this@MainActivity, "Настройки", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RepoDetailFragment.USER, userName)
        outState.putString(RepoDetailFragment.REPO, repoName)
    }

    override fun onRepoListItemClick(gitRepository: GitRepository) {
        this.userName = gitRepository.owner.login
        this.repoName = gitRepository.name
        if (isLandscape){
            showDetailFragment()
        } else{
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(RepoDetailFragment.USER, userName)
            intent.putExtra(RepoDetailFragment.REPO, repoName)
            startActivity(intent)
        }
    }

    private fun showDetailFragment() {
        if (!userName.isNullOrEmpty() && !repoName.isNullOrEmpty()){
            val repoDetailFragment = RepoDetailFragment.newInstance(userName, repoName)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_repo_container, repoDetailFragment)
                .commit()
        }
    }

}
