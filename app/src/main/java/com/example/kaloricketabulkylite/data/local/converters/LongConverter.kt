package com.example.kaloricketabulkylite.data.local.converters

import org.simpleframework.xml.convert.Converter
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

class LongConverter : Converter<Long?> {
    override fun read(node: InputNode?): Long {
        return node?.value?.toLongOrNull() ?: 1
    }

    override fun write(node: OutputNode?, value: Long?) {
        if (node != null) {
            node.value = value.toString()
        }
    }
}