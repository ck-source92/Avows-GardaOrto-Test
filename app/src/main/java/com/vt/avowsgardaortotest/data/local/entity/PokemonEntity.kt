package com.vt.avowsgardaortotest.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "pokemons")
@Parcelize
data class PokemonEntity(

    @PrimaryKey()
    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "name")
    val name: String
): Parcelable