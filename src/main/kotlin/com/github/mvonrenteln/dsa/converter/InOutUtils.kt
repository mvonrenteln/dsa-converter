package com.github.mvonrenteln.dsa.converter

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

val yamlFactory = YAMLFactory()
    .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES) //removes quotes from strings
    .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)//gets rid of -- at the start of the file.
    .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
    .disable(YAMLGenerator.Feature.SPLIT_LINES)

val yamlMapper = ObjectMapper(yamlFactory).registerKotlinModule().setSerializationInclusion(NON_NULL)

val jsonMapper = ObjectMapper().registerKotlinModule().setSerializationInclusion(NON_NULL)


inline fun <reified T> loadDataFile(file: File): T {
    return printMeasuredTimeAndReturnResult("Eingabe-Datei ${file.name} gelesen in %d ms.") {
        if (file.name.toLowerCase().endsWith("yaml")) {
            loadYamlFile(file)
        } else if (file.name.toLowerCase().endsWith("json")) {
            loadJsonFile<T>(file)
        } else {
            throw IllegalArgumentException("Nur YAML oder JSON als Daten sind erlaubt!")
        }
    }
}

inline fun <reified T> loadJsonFile(file: File): T {
    return jsonMapper.readValue(file, T::class.java)
}

inline fun <reified T> loadYamlFile(file: File): T {
    return yamlMapper.readValue(file, T::class.java)
}

fun writeYamlFile(data: Any, file: File) {
    yamlMapper.writerWithDefaultPrettyPrinter().writeValue(file, data)
}