package com.github.mvonrenteln.dsa.converter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.system.measureTimeMillis

val parameterDescription = """Parameter:
    |  1. Name der Eingabe-Datei oder des Eingabe-Verzeichnisses,
    |     - bei Angabe eines Verzeichnisses werden alle YAML und JSON-Dateien in diesem Verzeichnis gelesen
    |       - die Dateien sollten durchnummeriert werden mit "(1)", "(2)" etc. am Ende des Dateinamens
    |       - Die Daten der Dateien werden zusammengefügt ihrer Nummerierung folgend
    |       - Die Daten der Gruppe (Name, Mitglieder, Titel, Verfasser, Einleitung) werden immer von der LETZTEN Datei übernommen, da davon ausgegangen wird, dass diese die aktuellste ist (in den restlichen können die Werte also einfach fehlen).
    |  2. Ausgabe-Verzeichnis der Geschichte (Optional, Default: 'out')
    |  3. Ausgabe-Verzeichnis der verschiedenen Übersichten (Optional, Default: 'out')
""".trimMargin()

private val DEFAULT_OUT = "out"

suspend fun main(args: Array<String>) {
    val time = measureTimeMillis {
        if (args.isEmpty()) {
            println(parameterDescription)
            println("Gebe Beispiel-Datei aus.")
            val beispielRessource = "beispiel.yaml"
            val beispielDatei = File(DEFAULT_OUT, beispielRessource)
            ClassLoader.getSystemClassLoader().getResourceAsStream(beispielRessource)
                .copyTo(beispielDatei.outputStream())
            convert(arrayOf(beispielDatei), DEFAULT_OUT, DEFAULT_OUT)
        } else {
            val inputFile = File(args[0])
            val inputFiles = if (inputFile.isDirectory) {
                inputFile.listFiles { _, file -> file.endsWith("yaml") || file.endsWith("json") }
            } else {
                arrayOf(inputFile)
            }
            val storyOutputDir = args.getOrElse(1) { DEFAULT_OUT }
            val statistikenOutputDir = args.getOrElse(2) { DEFAULT_OUT }
            convert(inputFiles, storyOutputDir, statistikenOutputDir)
        }
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
    println("ENTER drücken zum Beenden.")
    readLine()

}

@Suppress("DeferredResultUnused")
private suspend fun convert(
    inputFiles: Array<File>,
    storyOutputDir: String,
    statistikenOutputDir: String
) {
    coroutineScope {

        val gruppenDaten = ladeGruppenDaten(inputFiles)

        val nscs = ladeNscs(inputFiles)

        val nameBasis = inputFiles[0].nameWithoutExtension.substringBefore('(').trim()

        async {
            val htmlFile = File(storyOutputDir, nameBasis + ".html")
            val html = StoryHtmlFileWriter().writeData(gruppenDaten)
            htmlFile.writeText(generateHtml(html, gruppenDaten.gruppe))
        }

        async {
            val htmlFile = File(statistikenOutputDir, nameBasis + "_APs.html")
            val html = ApsHtmlFileWriter().writeData(gruppenDaten)
            htmlFile.writeText(generateHtml(html, gruppenDaten.gruppe))
        }

        async {
            val htmlChronik = File(statistikenOutputDir, nameBasis + "_Chronik.html")
            ChronikHtmlFileWriter(htmlChronik).writeData(gruppenDaten)
        }
    }
}


private suspend fun CoroutineScope.ladeGruppenDaten(inputFiles: Array<File>): GruppenDaten {
    return inputFiles
        .sorted()
        .filter { !it.name.contains("NSC") }
        .map {
            async { loadDataFile<GruppenDaten>(it) }
        }
        .map { it.await() }
        .reduce { gruppe1, gruppe2 ->
            GruppenDaten(
                gruppe2.gruppe,
                gruppe2.mitglieder,
                gruppe2.titel,
                gruppe2.verfasser,
                gruppe2.einleitung,
                gruppe1.abende + gruppe2.abende
            )
        }
}


private suspend fun CoroutineScope.ladeNscs(inputFiles: Array<File>): List<Nsc> {
    return inputFiles
        .filter { it.name.contains("NSC") }
        .map {
            async { loadDataFile<Array<Nsc>>(it) }
        }
        .map { it.await() }
        .flatMap { it.toList() }
}

fun generateHtml(body: String, gruppe: String) =
    """<!DOCTYPE html>
<html lang="de">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Die 3 Meta-Tags oben *müssen* zuerst im head stehen; jeglicher sonstiger head-Inhalt muss *nach* diesen Tags kommen -->
    <title>Erlebnisse der $gruppe</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.rawgit.com/afeld/bootstrap-toc/v1.0.0/dist/bootstrap-toc.min.css">

    <!-- Optionales Theme -->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">-->

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

        body {
          line-height: 1.4; // Base line height
          color: #333
        }


        h1, h2, h3, h4 {
            font-family: "Open Sans","DejaVu Sans",sans-serif;
            letter-spacing: -.01em;
            font-weight: 300;
            font-style: normal;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 3.5rem; // Double the base value for a larger gap (1.75 * 2) = 3.5
        }

        h2 {
            font-size: 2em;
            margin-bottom: 1.75rem;
            color: #ba3925;
        }

        h3 {
            font-size: 1.5em;
            margin-bottom: 1.75rem;
        }

        h4 {
            font-size: 1.25em;
            margin-bottom: 1.75rem;
        }

        p {
            font-family: 'Spectral', serif;
            font-size: 1.25em; // Base font size
            margin-bottom: 1.75rem; // Base vertical spacing: (1.4 * 1.25) = 1.75
          }

        .content {
            padding: 3.5rem 2rem;
        }

    </style>

    <!-- Unterstützung für Media Queries und HTML5-Elemente in IE8 über HTML5 shim und Respond.js -->
    <!-- ACHTUNG: Respond.js funktioniert nicht, wenn du die Seite über file:// aufrufst -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body data-spy="scroll" data-target="#toc">
    <div class="container-fluid">

        <div class="row">
          <!-- sidebar, which will move to the top on a small screen -->
          <div class="col-sm-3">
            <nav id="toc" data-toggle="toc" class="sticky-top" style="top: 20px;"></nav>
          </div>
          <!-- main content area -->
          <div class="col-sm-9 content">
            $body
          </div>
    </div>
    </div>

    <div class="container-fluid footer">
        Erstellt am ${LocalDateTime.now()
        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN))}
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src="https://rishikeshdarandale.github.io/aws-http/assets/javascripts/vendor/bootstrap-toc.min.js"></script>

    <script>
    ${'$'}(document).ready(function(){
        ${'$'}('[data-toggle="popover"]').popover({
            container: 'body',
            placement: 'auto'
        })
    });

    ${'$'}('[data-spy="scroll"]').each(function () {
      var ${'$'}spy = ${'$'}(this).scrollspy('refresh')
    })
    </script>
  </body>
</html>"""




