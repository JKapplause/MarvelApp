package com.info.marvelapp.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.info.marvelapp.R
import com.info.marvelapp.domain.model.Character

class CharacterListAdapter(private val context : Context, var itemList:ArrayList<Character>): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    inner class  CharacterListViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val characterName : TextView = view.findViewById(R.id.txtCharacterName)
        val thumbnail : ImageView = view.findViewById(R.id.imgCharacterImage)
        val cardCharacter : LinearLayout = view.findViewById(R.id.characterLinearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_characters,parent,false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = itemList[position]
        holder.characterName.text = list.name
        val imageUrl = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExt}"
        Glide.with(context).load(imageUrl).into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener {
            Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(characterList:ArrayList<Character>) {
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }
}