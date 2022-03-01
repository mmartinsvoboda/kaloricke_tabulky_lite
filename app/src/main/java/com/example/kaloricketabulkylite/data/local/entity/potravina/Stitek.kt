package com.example.kaloricketabulkylite.data.local.entity.potravina

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(strict = false)
data class Stitek(
    @field:Attribute(name = "nazev", required = false)
    @param:Attribute(name = "nazev", required = false)
    val nazev: String?,
    @field:Text(required = false)
    @param:Text(required = false)
    val value: String?
)