package com.optimus.githubfinal.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.optimus.githubfinal.R
import com.optimus.githubfinal.model.GitRepository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_repo_sell.view.*

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */
class RepoAdapter(private val listener: (GitRepository)->Unit) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private val repoList: MutableList<GitRepository> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_repo_sell, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun updateData(list: List<GitRepository>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(inflatedView: View) : RecyclerView.ViewHolder(inflatedView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView


        fun bind(repo: GitRepository) {
            Glide.with(itemView)
                .load(repo.owner.avatarUrl)
                .into(itemView.rv_image_owner)
            itemView.rv_repo_title.text = repo.name
            itemView.rv_repo_description.text = repo.description
            itemView.setOnClickListener {
                listener.invoke(repo)
            }
        }
    }
}