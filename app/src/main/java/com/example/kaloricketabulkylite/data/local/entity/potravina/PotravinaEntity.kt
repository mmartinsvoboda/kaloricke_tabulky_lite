package com.example.kaloricketabulkylite.data.local.entity.potravina

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.kaloricketabulkylite.data.local.converters.BooleanConverter
import com.example.kaloricketabulkylite.data.local.converters.JednotkaListConverter
import com.example.kaloricketabulkylite.data.local.converters.StitekListConverter
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.convert.Convert

@Entity
@TypeConverters(
    StitekListConverter::class,
    JednotkaListConverter::class
)
@Root(name = "potravina", strict = false)
data class PotravinaEntity(
    @PrimaryKey
    @field:Attribute(name = "guid_potravina") @param:Attribute(name = "guid_potravina")
    val guidPotravina: String,
    @field:Element(name = "nazev") @param:Element(name = "nazev")
    val nazev: String,
    @field:Element(name = "stav") @param:Element(name = "stav")
    val stav: String,
    @field:Element(name = "kategorie") @param:Element(name = "kategorie")
    val kategorie: String,
    @field:Element(name = "znacka") @param:Element(name = "znacka")
    val znacka: String,
    @field:Element(name = "oblibena") @param:Element(name = "oblibena")
    @Convert(BooleanConverter::class)
    var oblibena: Boolean,
//    @field:Element(name = "hodnoty") @param:Element(name = "hodnoty")
//    @Embedded(prefix = "hodnoty_")
//    val hodnoty: Hodnoty,
//    @field:ElementList(
//        name = "jednotky",
//        inline = true,
//        required = false
//    ) @param:ElementList(name = "jednotky", inline = true, required = false)
//    val jednotky: List<Jednotka>,
    @field:Element(name = "popisObsah", required = false) @param:Element(name = "popisObsah", required = false)
    val popisObsah: String?,
    @field:Element(name = "popisZdravi", required = false) @param:Element(name = "popisZdravi", required = false)
    val popisZdravi: String?,
    @field:Element(name = "popisPrakticke", required = false) @param:Element(name = "popisPrakticke", required = false)
    val popisPrakticke: String?,
//    @field:ElementList(name = "stitkyVitaminy", inline = true, required = false) @param:ElementList(
//        name = "stitkyVitaminy",
//        inline = true,
//        required = false
//    )
//    val stitkyVitaminy: List<Stitek>,
//    @field:ElementList(name = "stitkyMineraly", inline = true, required = false) @param:ElementList(
//        name = "stitkyMineraly",
//        inline = true,
//        required = false
//    )
//    val stitkyMineraly: List<Stitek>,
//    @field:ElementList(name = "stitkyZdravi", inline = true, required = false) @param:ElementList(
//        name = "stitkyZdravi",
//        inline = true,
//        required = false
//    )
//    val stitkyZdravi: List<Stitek>,
//    @field:ElementList(
//        name = "stitkyEcka",
//        inline = true,
//        required = false
//    ) @param:ElementList(name = "stitkyEcka", inline = true, required = false)
//    val stitkyEcka: List<Stitek>,
    @field:Element(name = "foto", required = false) @param:Element(name = "foto", required = false)
    val foto: String?,
    @field:Element(name = "foto_thumb", required = false) @param:Element(
        name = "foto_thumb",
        required = false
    )
    val foto_thumb: String?
)

