package com.github.mvonrenteln.dsa.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.io.InputStream

val yamlFactory = YAMLFactory()
    .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES) //removes quotes from strings
    .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)//gets rid of -- at the start of the file.
    .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
    .disable(YAMLGenerator.Feature.SPLIT_LINES)

val yamlMapper = ObjectMapper(yamlFactory).registerKotlinModule()

val jsonMapper = ObjectMapper().registerKotlinModule()


inline fun <reified T> loadDataFile(inputStream: InputStream, inputFileName: String): T {
    return printMeasuredTimeAndReturnResult("Eingabe-Datei $inputFileName gelesen in %d ms.") {
        if (inputFileName.toLowerCase().endsWith("yaml")) {
            loadYamlFile(inputStream)
        } else if (inputFileName.toLowerCase().endsWith("json")) {
            loadJsonFile<T>(inputStream)
        } else {
            throw IllegalArgumentException("Nur YAML oder JSON als Daten sind erlaubt!")
        }
    }
}

inline fun <reified T> loadJsonFile(path: InputStream): T {
    return jsonMapper.readValue(path, T::class.java)
}

inline fun <reified T> loadYamlFile(path: InputStream): T {
    return yamlMapper.readValue(path, T::class.java)
}

fun writeYamlFile(data: Any, file: File) {
    yamlMapper.writerWithDefaultPrettyPrinter().writeValue(file, data)
}