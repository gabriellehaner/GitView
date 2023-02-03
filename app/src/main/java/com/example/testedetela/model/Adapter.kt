package com.example.testedetela.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testedetela.databinding.CardBinding

class RepositoryListAdapter(
    private val listRepository: List<ModelRepository>,
    private val onClicked: (ModelRepository) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UsersViewHolder -> {
                holder.bind(listRepository[position], onClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    class UsersViewHolder constructor(
        itemView: CardBinding
    ) : RecyclerView.ViewHolder(itemView.root){
        private val modelNome = itemView.nomeuser
        private val modelLanguage = itemView.linguagemrep
        private val modelLink = itemView.linkrepository

        fun bind(model: ModelRepository, onClicked: (ModelRepository) -> Unit){
            modelNome.text = model.name
            modelLanguage.text = model.language
            modelLink.text = model.htmlUrl

                    itemView.setOnClickListener {
            onClicked(model)
        }

        }
    }
}
