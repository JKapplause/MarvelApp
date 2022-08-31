package com.info.marvelapp.data.data_source.dto


import com.google.gson.annotations.SerializedName
import com.info.marvelapp.domain.model.Character

data class Result(
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("urls")
    val urls: List<Url>
){

    fun toCharacter(): Character{
        return Character(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension,
            comics = comics.items.map{
                it.name
            }
        )
    }
}