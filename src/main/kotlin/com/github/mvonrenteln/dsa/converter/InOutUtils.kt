package com.github.mvonrenteln.dsa.converter

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.io.InputStream

val yamlFactory: YAMLFactory = YAMLFactory()
    .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES) //removes quotes from strings
    .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)//gets rid of -- at the start of the file.
    .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
    .disable(YAMLGenerator.Feature.SPLIT_LINES)

val yamlMapper: ObjectMapper = ObjectMapper(yamlFactory).registerKotlinModule().setSerializationInclusion(NON_NULL)

val jsonMapper: ObjectMapper = ObjectMapper().registerKotlinModule().setSerializationInclusion(NON_NULL)


inline fun <reified T: Any> loadDataFile(file: File): T {
    return printMeasuredTimeAndReturnResult("Eingabe-Datei ${file.name} gelesen") {
        when {
            file.name.toLowerCase().endsWith("yaml") -> {
                loadYamlFile(file)
            }
            file.name.toLowerCase().endsWith("json") -> {
                loadJsonFile(file)
            }
            else -> {
                throw IllegalArgumentException("Nur YAML oder JSON als Daten sind erlaubt!")
            }
        }
    }
}

inline fun <reified T> loadJsonFile(file: File) = loadFile<T>(file, jsonMapper)

inline fun <reified T> loadYamlFile(file: File) = loadFile<T>(file, yamlMapper)

inline fun <reified T> loadFile(file: File, mapper: ObjectMapper): T {
    return try {
        mapper.readValue(file, T::class.java)
    } catch (e: Exception) {
        val msg = "Datei $file konnte nicht gelesen werden!  ${e.message}"
        logger.error("Datei $file konnte nicht gelesen werden!  ${e.message}", e)
        throw IllegalStateException(msg, e)
    }
}

fun writeYamlFile(data: Any, file: File) {
    yamlMapper.writerWithDefaultPrettyPrinter().writeValue(file, data)
}

fun resourceAsStream(file: String): InputStream {
  return try {
      ClassLoader.getSystemClassLoader().getResourceAsStream(file)!!
  } catch (e: Exception) {
      logger.error("Datei <$file> nicht gefunden!", e)
      throw IllegalStateException(e)
  }
}