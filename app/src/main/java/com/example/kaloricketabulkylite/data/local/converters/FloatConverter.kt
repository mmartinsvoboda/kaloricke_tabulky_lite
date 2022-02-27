package com.example.kaloricketabulkylite.data.local.converters

import org.simpleframework.xml.convert.Converter
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

class FloatConverter : Converter<Float?> {
    override fun read(node: InputNode?): Float {
        return node?.value?.toFloatOrNull() ?: 1f
    }

    override fun write(node: OutputNode?, value: Float?) {
        if (node != null) {
            node.value = value.toString()
        }
    }
}