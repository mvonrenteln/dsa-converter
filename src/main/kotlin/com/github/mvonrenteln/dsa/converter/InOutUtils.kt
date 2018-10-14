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


fun loadDataFile(inputStream: InputStream, inputFileName: String): GruppenDaten {
    return printMeasuredTimeAndReturnResult("Eingabe-Datei $inputFileName gelesen in %d ms.") {
        if (inputFileName.toLowerCase().endsWith("yaml")) {
            loadYamlFile(inputStream)
        } else if (inputFileName.toLowerCase().endsWith("json")) {
            loadJsonFile(inputStream)
        } else {
            throw IllegalArgumentException("Nur YAML oder JSON als Daten sind erlaubt!")
        }
    }
}

fun loadJsonFile(path: InputStream): GruppenDaten {
    return jsonMapper.readValue(path, GruppenDaten::class.java)
}

fun loadYamlFile(path: InputStream): GruppenDaten {
    return yamlMapper.readValue(path, GruppenDaten::class.java)
}


fun convertJsonToYaml() {
    val data = loadJsonFile(ClassLoader.getSystemClassLoader().getResourceAsStream("DSA-Abenteuer_G7_clean.json"))
    writeYamlFile(data)
}

fun writeYamlFile(data: GruppenDaten) {
    yamlMapper.writerWithDefaultPrettyPrinter().writeValue(File("out/DSA-Abenteuer_G7_clean.yaml"), data)
}