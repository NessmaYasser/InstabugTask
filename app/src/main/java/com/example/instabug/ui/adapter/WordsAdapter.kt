package com.example.instabug.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instabug.R
import com.example.instabug.data.models.Word
import kotlinx.android.synthetic.main.word_item_rv.view.*

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {
    var wordsData = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.word_item_rv, parent, false)
        )
    }

    override fun getItemCount() = wordsData.size

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(
            wordsData[position]
        )
    }

    fun setData(data: MutableList<Word>) {
        wordsData = data
    }

    inner class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Word) {
            itemView.wordTV.text = data.wordText
            itemView.numTV.text = data.wordNumber.toString()
        }
    }
}