package com.optimus.githubfinal.ui.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.optimus.githubfinal.R
import com.optimus.githubfinal.ui.fragments.RepoDetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }
        if (savedInstanceState==null) {
            val user = intent.extras?.getString(RepoDetailFragment.USER)?:""
            val repo = intent.extras?.getString(RepoDetailFragment.REPO)?:""

            val repoDetailFragment = RepoDetailFragment.newInstance(user, repo)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_repo_container, repoDetailFragment)
                .commit()
        }
    }
}
