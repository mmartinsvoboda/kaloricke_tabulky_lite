package com.example.kaloricketabulkylite.data.remote.response.search

import com.example.kaloricketabulkylite.data.local.entity.search.PotravinaEntity
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "search", strict = false)
data class SearchResponse(
    @field:ElementList(name = "potravina", inline = true, required = false) @param:ElementList(name = "potravina", inline = true, required = false)
    val potravina: List<PotravinaEntity>,
    @field:Attribute(name = "q") @param:Attribute(name = "q")
    val q: String,
    @field:Attribute(name = "jedn") @param:Attribute(name = "jedn")
    val jedn: String
)
