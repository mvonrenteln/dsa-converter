package com.github.mvonrenteln.dsa.converter

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.File
import java.io.InputStream
import kotlin.system.measureTimeMillis

val parameterDescription = """Parameter: 1. Name der Eingabe-Datei,
    |2. Ausgabe-Verzeichnis der Geschichte (Optional, Default: 'out')
    |3. Ausgabe-Verzeichnis der verschiedenen Übersichten (Optional, Default: 'out')
""".trimMargin()

private val DEFAULT_OUT = "out"

suspend fun main(args: Array<String>) {
    val time = measureTimeMillis {
        if (args.isEmpty()) {
            println(parameterDescription)
            println("Gebe Beispiel-Datei aus.")
            val inputFileName = "example.yaml"
            val input = ClassLoader.getSystemClassLoader().getResourceAsStream(inputFileName)
            convert(inputFileName, input, DEFAULT_OUT, DEFAULT_OUT)
        } else {
            val inputFileName = args[0]
            val input = File(inputFileName).inputStream()
            val storyOutputDir = args.getOrElse(1) { DEFAULT_OUT }
            val statistikenOutputDir = args.getOrElse(2) { DEFAULT_OUT }
            convert(inputFileName, input, storyOutputDir, statistikenOutputDir)
        }
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
    println("ENTER drücken zum Beenden.")
    readLine()

}

@Suppress("DeferredResultUnused")
private suspend fun convert(
    inputFileName: String,
    inputStream: InputStream,
    storyOutputDir: String,
    statistikenOutputDir: String
) {
    coroutineScope {
        val data = async { loadDataFile<GruppenDaten>(inputStream, inputFileName) }

        async {
            val htmlFile = File(storyOutputDir, File(inputFileName).nameWithoutExtension + ".html")
            val md = StoryAdocFileWriter().writeData(data.await())
            val document = PARSER.parse(md)
            val html = RENDERER.render(document)
            htmlFile.writeText(writeHtml(html))
        }

        async {
            val htmlFile = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_APs.adoc")
            ApsAdocFileWriter().writeData(data.await())
            val md = ApsAdocFileWriter().writeData(data.await())
            val document = PARSER.parse(md)
            val html = RENDERER.render(document)
            htmlFile.writeText(writeHtml(html))
        }

        async {
            val htmlChronik = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_Chronik.html")
            ChronikHtmlFileWriter(htmlChronik).writeData(data.await())
        }
    }
}


fun writeHtml(body: String) =
    """<?xml version="1.0" encoding="UTF-8"?><html>
        <head>
            <title>Hello world</title>
        </head>
        <body>
            $body
        </body>
        </html>"""




