package com.github.mvonrenteln.dsa.converter

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.File
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
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
            val html = StoryHtmlFileWriter().writeData(data.await())
            htmlFile.writeText(writeHtml(html, data.await().gruppe, "fliesstext"))
        }

        async {
            val htmlFile = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_APs.html")
            val html = ApsHtmlFileWriter().writeData(data.await())
            htmlFile.writeText(writeHtml(html, data.await().gruppe, ""))
        }

        async {
            val htmlChronik = File(statistikenOutputDir, File(inputFileName).nameWithoutExtension + "_Chronik.html")
            ChronikHtmlFileWriter(htmlChronik).writeData(data.await())
        }
    }
}


fun writeHtml(body: String, gruppe: String, texttyp: String) =
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

    <link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Spectral">

    <style type="text/css" class="init">
        .popover {
            max-width:600px;
        }

        .footer {
            color: rgba(255,255,255,.8);
            background-color: rgba(0,0,0,.8);
            padding: 1.25em;
        }

        h1, h2, h3 {
            font-family: "Open Sans","DejaVu Sans",sans-serif;
            letter-spacing: -.01em;
            font-weight: 300;
            font-style: normal;
            line-height: 1.2;
            margin-top: 1em;
            margin-bottom: .5em;
        }

        h1 {
            font-size: 2.75em;
        }

        h2 {
            font-size: 2.3125em;
            color: #ba3925;
        }

        p {
            font-family: 'Spectral', serif;
            font-size: 18px;
            line-height: 28px;
            margin: 0 0 28px;
        }

        .fliesstext {
            max-width: 60em;
        }
    </style>

    <!-- Unterstützung für Media Queries und HTML5-Elemente in IE8 über HTML5 shim und Respond.js -->
    <!-- ACHTUNG: Respond.js funktioniert nicht, wenn du die Seite über file:// aufrufst -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body>
    <div class="container $texttyp">
        $body
    </div>

    <div class="container-fluid footer">
        Erstellt am ${LocalDateTime.now()
        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN))}
    </div>

    <!-- jQuery (wird für Bootstrap JavaScript-Plugins benötigt) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Binde alle kompilierten Plugins zusammen ein (wie hier unten) oder such dir einzelne Dateien nach Bedarf aus -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script>
    ${'$'}(document).ready(function(){
        ${'$'}('[data-toggle="popover"]').popover();
    });
    </script>
  </body>
</html>"""




