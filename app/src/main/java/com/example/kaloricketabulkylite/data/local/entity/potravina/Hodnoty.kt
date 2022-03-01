package com.example.kaloricketabulkylite.data.local.entity.potravina

import androidx.room.Embedded
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(strict = false)
data class Hodnoty(
    @field:Element(required = false, name = "energie")
    @param:Element(required = false, name = "energie")
    @Embedded(prefix = "energie")
    val energie: Hodnota?,
    @field:Element(required = false, name = "bilkoviny")
    @param:Element(required = false, name = "bilkoviny")
    @Embedded(prefix = "bilkoviny")
    val bilkoviny: Hodnota?,
    @field:Element(required = false, name = "sacharidy")
    @param:Element(required = false, name = "sacharidy")
    @Embedded(prefix = "sacharidy")
    val sacharidy: Hodnota?,
    @field:Element(required = false, name = "cukry")
    @param:Element(required = false, name = "cukry")
    @Embedded(prefix = "cukry")
    val cukry: Hodnota?,
    @field:Element(required = false, name = "tuky")
    @param:Element(required = false, name = "tuky")
    @Embedded(prefix = "tuky")
    val tuky: Hodnota?,
    @field:Element(required = false, name = "nasyceneMastneKyseliny")
    @param:Element(required = false, name = "nasyceneMastneKyseliny")
    @Embedded(prefix = "nasyceneMastneKyseliny")
    val nasyceneMastneKyseliny: Hodnota?,
    @field:Element(required = false, name = "transmastneKyseliny")
    @param:Element(required = false, name = "transmastneKyseliny")
    @Embedded(prefix = "transmastneKyseliny")
    val transmastneKyseliny: Hodnota?,
    @field:Element(required = false, name = "cholesterol")
    @param:Element(required = false, name = "cholesterol")
    @Embedded(prefix = "cholesterol")
    val cholesterol: Hodnota?,
    @field:Element(required = false, name = "vlaknina")
    @param:Element(required = false, name = "vlaknina")
    @Embedded(prefix = "vlaknina")
    val vlaknina: Hodnota?,
    @field:Element(required = false, name = "sodik")
    @param:Element(required = false, name = "sodik")
    @Embedded(prefix = "sodik")
    val sodik: Hodnota?,
    @field:Element(required = false, name = "vapnik")
    @param:Element(required = false, name = "vapnik")
    @Embedded(prefix = "vapnik")
    val vapnik: Hodnota?,
    @field:Element(required = false, name = "sul")
    @param:Element(required = false, name = "sul")
    @Embedded(prefix = "sul")
    val sul: Hodnota?,
    @field:Element(required = false, name = "voda")
    @param:Element(required = false, name = "voda")
    @Embedded(prefix = "voda")
    val voda: Hodnota?,
    @field:Element(required = false, name = "mononenasycene")
    @param:Element(required = false, name = "mononenasycene")
    @Embedded(prefix = "mononenasycene")
    val mononenasycene: Hodnota?,
    @field:Element(required = false, name = "polynenasycene")
    @param:Element(required = false, name = "polynenasycene")
    @Embedded(prefix = "polynenasycene")
    val polynenasycene: Hodnota?,
    @field:Element(required = false, name = "gi")
    @param:Element(required = false, name = "gi")
    @Embedded(prefix = "gi")
    val gi: Hodnota?,
    @field:Element(required = false, name = "phe")
    @param:Element(required = false, name = "phe")
    @Embedded(prefix = "phe")
    val phe: Hodnota?,
    @field:Element(required = false, name = "alcohol")
    @param:Element(required = false, name = "alcohol")
    @Embedded(prefix = "alcohol")
    val alcohol: Hodnota?,
    @field:Element(required = false, name = "tekutiny")
    @param:Element(required = false, name = "tekutiny")
    @Embedded(prefix = "tekutiny")
    val tekutiny: Hodnota?
)

@Root(strict = false)
data class Hodnota(
    @field:Attribute(name = "jedn", required = false)
    @param:Attribute(name = "jedn", required = false)
    val jedn: String? = null,
    @field:Text(required = false)
    @param:Text(required = false)
    val value: String? = null
)