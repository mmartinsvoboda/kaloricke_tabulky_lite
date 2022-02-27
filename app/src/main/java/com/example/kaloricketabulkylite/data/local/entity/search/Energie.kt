package com.example.kaloricketabulkylite.data.local.entity.search

import com.example.kaloricketabulkylite.data.local.converters.FloatConverter
import com.example.kaloricketabulkylite.data.local.converters.LongConverter
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import org.simpleframework.xml.convert.Convert

@Root(name = "energie", strict = false)
data class Energie(
    @field:Attribute(name = "jedn", required = false)
    @param:Attribute(name = "jedn", required = false)
    val jedn: String?,
    @field:Text(required = false) @param:Text(required = false)
    @Convert(FloatConverter::class)
    val value: Float?
)