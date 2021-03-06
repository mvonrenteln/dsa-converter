package com.github.mvonrenteln.dsa.converter

fun erstelleChronik(gruppe: String, daten: String): String {

    return """<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, user-scalable=no">
    <title>Erlebnisse der $gruppe</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
    <style type="text/css" class="init">

    </style>

    <script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
    <script type="text/javascript" language="javascript" class="init">
        ${'$'}(document).ready(function() {
            ${'$'}('#example').DataTable({
                "ordering": false,
                "language": {
                  "url": "https://cdn.datatables.net/plug-ins/1.10.18/i18n/German.json"
                },
                "scrollY":        "600px",
                "scrollCollapse": true,
                "paging":         false,
                "info":     false,
                "data": $daten,
                columns: [
                    { title: "Datum" },
                    { title: "Ort" },
                    { title: "Personen" },
                    { title: "Zusammenfassung" },
                    { title: "Abenteuer" },
                    { title: "Kapitel" },
                    { title: "Abend" }
                ]

            }
            );
        } );
    </script>

</head>
<body class="dt-example">
<div class="container">
    <section>
        <h1>Erlebnisse der $gruppe</h1>

        <table id="example" class="display" style="width:98%">

        </table>

    </section>
</div>

</body>
</html>
"""
}