package com.github.mvonrenteln.dsa.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.net.URL

val yamlFactory = YAMLFactory()
    .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES) //removes quotes from strings
    .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)//gets rid of -- at the start of the file.
    .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
    .disable(YAMLGenerator.Feature.SPLIT_LINES)

val yamlMapper = ObjectMapper(yamlFactory).registerKotlinModule()

val jsonMapper = ObjectMapper().registerKotlinModule()

fun convertJsonToYaml() {
    val data =
        loadJsonFile(File(ClassLoader.getSystemClassLoader().getResource("DSA-Abenteuer_G7_clean.json").toURI()))
    writeYamlFile(data)
}

fun loadJsonFile(path: File): GruppenDaten {
    return jsonMapper.readValue(path, GruppenDaten::class.java)
}

fun loadYamlFile(path: URL): GruppenDaten {
    return yamlMapper.readValue(path, GruppenDaten::class.java)
}

fun writeYamlFile(data: GruppenDaten) {
    yamlMapper.writerWithDefaultPrettyPrinter().writeValue(File("out/DSA-Abenteuer_G7_clean.yaml"), data)
}