package com.example.kaloricketabulkylite.data.local.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kaloricketabulkylite.data.local.converters.BooleanConverter
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import org.simpleframework.xml.convert.Convert

@Entity
@Root(name = "potravina", strict = false)
data class SearchEntity(
    @PrimaryKey
    @field:Attribute(name = "guid_potravina") @param:Attribute(name = "guid_potravina")
    val guidPotravina: String,
    @field:Element(name = "nazev") @param:Element(name = "nazev")
    val nazev: String,
    @field:Element(name = "oblibena") @param:Element(name = "oblibena")
    @Convert(BooleanConverter::class)
    val oblibena: Boolean,
    @field:Element(name = "autor", required = false) @param:Element(
        name = "autor",
        required = false
    )
    val autor: String?,
    @field:Element(name = "foto", required = false) @param:Element(name = "foto", required = false)
    val foto: String?,
    @field:Element(name = "foto_thumb", required = false) @param:Element(
        name = "foto_thumb",
        required = false
    )
    val foto_thumb: String?,
    @Embedded(prefix = "energie_")
    @field:Element(name = "energie") @param:Element(name = "energie")
    val energie: Energie
)