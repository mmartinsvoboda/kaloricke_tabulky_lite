package com.example.kaloricketabulkylite.data.local.entity.search

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "energie", strict = false)
data class Energie(
    @field:Attribute(name = "jedn", required = false) @param:Attribute(
        name = "jedn",
        required = false
    )
    val jedn: String?,
    @field:Text(required = false) @param:Text(required = false)
    val value: String?
)