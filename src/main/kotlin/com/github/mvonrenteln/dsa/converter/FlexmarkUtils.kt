package com.github.mvonrenteln.dsa.converter

import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.toc.TocExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet


val FLEXMARK_OPTIONS = MutableDataSet().apply {
    set(Parser.EXTENSIONS, listOf(TablesExtension.create(), TocExtension.create()))
}

var PARSER = Parser.builder(FLEXMARK_OPTIONS).build()

var RENDERER = HtmlRenderer.builder(FLEXMARK_OPTIONS).build()