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
            htmlFile.writeText(writeHtml(html, data.await().gruppe))
        }

        async {
            val htmlFile = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_APs.html")
            ApsAdocFileWriter().writeData(data.await())
            val md = ApsAdocFileWriter().writeData(data.await())
            val document = PARSER.parse(md)
            val html = RENDERER.render(document)
            htmlFile.writeText(writeHtml(html, data.await().gruppe))
        }

        async {
            val htmlChronik = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_Chronik.html")
            ChronikHtmlFileWriter(htmlChronik).writeData(data.await())
        }
    }
}


fun writeHtml(body: String, gruppe: String) =
    """<!DOCTYPE html>
<html lang="de">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Die 3 Meta-Tags oben *müssen* zuerst im head stehen; jeglicher sonstiger head-Inhalt muss *nach* diesen Tags kommen -->
    <title>Erlebnisse der $gruppe</title>

    <!-- Das neueste kompilierte und minimierte CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optionales Theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Unterstützung für Media Queries und HTML5-Elemente in IE8 über HTML5 shim und Respond.js -->
    <!-- ACHTUNG: Respond.js funktioniert nicht, wenn du die Seite über file:// aufrufst -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">
        $body
    </div>

    <!-- jQuery (wird für Bootstrap JavaScript-Plugins benötigt) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Binde alle kompilierten Plugins zusammen ein (wie hier unten) oder such dir einzelne Dateien nach Bedarf aus -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>"""




