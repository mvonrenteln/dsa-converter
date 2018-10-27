package com.github.mvonrenteln.dsa.converter

import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet


val FLEXMARK_OPTIONS = MutableDataSet()
    .set(TablesExtension.COLUMN_SPANS, false)
    .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
    .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
    .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true)
    .set(Parser.EXTENSIONS, listOf(TablesExtension.create()))

var PARSER = Parser.builder(FLEXMARK_OPTIONS).build()

var RENDERER = HtmlRenderer.builder(FLEXMARK_OPTIONS).build()