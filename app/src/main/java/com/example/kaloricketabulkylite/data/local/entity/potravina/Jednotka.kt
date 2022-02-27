package com.example.kaloricketabulkylite.data.local.entity.potravina

import com.example.kaloricketabulkylite.data.local.converters.LongConverter
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import org.simpleframework.xml.convert.Convert

@Root(strict = false)
data class Jednotka(
    @field:Attribute(name = "nasobek", required = false)
    @param:Attribute(name = "nasobek", required = false)
    @Convert(LongConverter::class)
    val nasobek: Long?,
    @field:Text(required = false) @param:Text(required = false)
    val value: String?
)