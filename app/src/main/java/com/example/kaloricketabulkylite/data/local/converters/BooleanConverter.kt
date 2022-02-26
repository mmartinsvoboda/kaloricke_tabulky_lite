package com.example.kaloricketabulkylite.data.local.converters

import org.simpleframework.xml.convert.Converter
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

class BooleanConverter : Converter<Boolean?> {
    override fun read(node: InputNode?): Boolean {
        return when (node.toString().lowercase().replace("\"", "")) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> false
        }
    }

    override fun write(node: OutputNode?, value: Boolean?) {
        TODO("Not yet implemented")
    }
}