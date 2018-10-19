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
                "data": $daten
            }
            );
        } );
    </script>

</head>
<body class="dt-example">
<div class="container">
    <section>
        <h1>DataTables example <span>Default ordering (sorting)</span></h1>

        <table id="example" class="display" style="width:98%">
            <thead>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Datum</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Ort</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Personen</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zusammenfassung</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Abenteuer</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Kapitel</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Abend</p></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">xx. EFF 1018 BF
                    (&#8230;&#8203;tag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;, &#8230;&#8203;</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Des Greifen Leid</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">-&lt; 30.05.2018 &gt;-</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">08. EFF 1018 BF (Praiostag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Elenvina</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Jariel Praiotin XII., Hilberian
                    Praiofold III. Lumerian, Die Traumtänzer (G6)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Hilberian verkündet demütig das für
                    ihn ungünstige Ergebnis, dann feiert ganz Elenvina. Auch die beiden Heldengruppen sind dabei,
                    allerdings im Hintergrund. Denn über deren Mithilfe wird der große praiotische Mantel des Schweigens
                    gelegt.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Bahnbrechende, erhabene und
                    väterliche Worte</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">09. EFF 1018 BF (Rohalstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Elenvina</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Jariel Praiotin XII., Lutisana
                    Anguedoc, Die Traumtänzer (G6)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gruppen tauschen sich über ihre
                    Abenteuer aus. Anschließend bedankt sich Jariel Praiotin XII. quasi privat bei den neun tapferen
                    Abenteurern, welche der Praioskirche in einer ihrer größeren Krisen so tatkräftig beistanden. Jeder
                    erhält 20 Dukaten als Entschädigung. Nach der Rückkehr zur Herberge wird Glymbar per Schreiben vom
                    Zwergenkönig Aromobolosch nach Murolosch gebeten.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">10. und 11. EFF 1018 BF (Feuer- und
                    Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Elenvina</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Traumtänzer (G6)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die beiden Abenteurergruppen trennen
                    sich teilweise recht wehmütig voneinander. Zur Erinnerung schenkt Tamaric einem jeden von ihnen
                    einen bronzenen Anhänger.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">12. EFF bis 12. TRA 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Herzogtum Nordmarken</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten um Glymbar reisen
                    durch die Wildnis nach Murolosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Der Hochkönig</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">13. TRA 1018 BF (Praiostag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Murolosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zwergenkönig Arombolosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zwergenkönig Arombolosch bittet die
                    Gefährten als seine Boten zu den Zwergenvölkern zu reisen. Es soll eine neuer Hochkönig - ein
                    Angarok Rogmarok - gewählt werden.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Des Greifen Leid</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">TRA bis TSA 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Mittelaventurien</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Omgrasch Sohn des Orbal, Garbalon
                    Sohn des Gerambalosch, Gilimon Sohn des Gilim, Nirwulf Sohn des Negromon</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten tragen die Botschaft
                    von Arombolosch zunächst in die Zwergenreiche Lorgolosch, Finsterkamm und Koschim als auch zu den
                    Hügelzwergen in Angbar.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Der Hochkönig</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Flüssiges Gold</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">15. TSA 1018 BF (Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Ferdok</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Growin Sohn des Gorbosch, Rayo
                    Brabaker</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">In Ferdok wollen sie den
                    zeremoniellen Hammer Malmarok abholen, müssen diesen jedoch erst aus den Fängen eines südländischen
                    Paktierers befreien, welcher scheinbar mit Borbarad im Bunde steht.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Ferdok</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Growin Sohn des Gorbosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Der Paktierer kann entkommen, doch
                    die Gefährten haben immerhin Malmarok zurückerobert. Sie werden bei Growin vorstellig und feiern
                    schließlich mit diesem ausgiebig jenen immens wichtigen Erfolg.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zwischen Hammer und Amboss</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">TSA bis PER 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Xorlosch und Senalosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Tschubax Sohn des Tuagel, Fargol
                    Sohn des Fanderam</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Beim Überbringen der Nachricht
                    werden sie vom bartlosen Tschubax, der sich wegen der Tatsache einer Wahl schwer in seiner Ehre
                    verletzt fühlt, wütend aus Xorlosch hinaus gejagt. Ein paar Tage später in Senalosch versucht Fargol
                    wiederum das Wohlwollen der Gruppe plump zu erkaufen. Zwischen diesem und Glymbar kommt es dabei zu
                    einem ungebührlichen Gerangel um den Hammer, an dessen Ende sich die Gefährten auch hier rasch
                    verabschieden. Oswin stellt fest, dass Malmarok mit einem erzdämonischen Fluch belegt ist, welcher
                    den Träger verhext. Doch der Fluch ist so stark, dass sie ihn nicht brechen können.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">13. PER 1018 BF (Erdtag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Murolosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zwergenkönig Arombolosch</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sie kehren nach Murolosch zurück und
                    übergeben mit warnenden Worten den erzdämonisch verfluchten Zeremoniegegenstand.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">18. PER 1018 BF (Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Murolosch</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Oswin beobachtet Glymbars Verhalten
                    und die Gefährten entscheiden, dem Erzmagier Rohezal einen Besuch abzustatten.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Turmhohe Hilfe und getürmter
                    Ärger</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">19. bis 21. PER 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Murolosch</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Glymbar verabschiedet sich abermals
                    von seiner gesamten Sippschaft.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">23. PER 1018 BF (Rohalstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezals Turm</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezal, Roana vom Amboss, Rondriga
                    Conchobair</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">An Rohezals Turm treffen sich
                    Rondriga und Sherkan liebeshungrig wieder. Die beiden genießen ihre Zweisamkeit sehr ausführlich,
                    während Durrandir zumeist allein die Ruhe der Natur auf sich wirken lässt. Oswin und Rohezal
                    analysieren gemeinsam Glymbars erzdämonische Verzauberung.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">25. ING 1018 BF (Erdtag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezals Turm</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezal</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Unter großer Anstregung befreit
                    Rohezal den tapferen Glymbar von der erzdämonischen Verzauberung.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">26. ING 1018 BF (Markttag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezals Turm</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezal, Roana vom Amboss</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Roana offenbart den Gefährten ihre
                    persönliche Deutung der aktuellen Sternkonstellation.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">27. ING bis 07. RAH 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezals Turm</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Rohezal</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sie erhalten von Rohezal eine
                    gildenmagische Thesis, um möglicherweise den Fluch Malmaroks mit Hilfe mehrerer Zauberer brechen zu
                    können.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">08. RAH 1018 BF (Windstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Königreich Almada</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Urdo von Gisholm, Botenreiter</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten reisen Richtung Punin
                    und vereiteln Urdo von Gisholms Diebstahl einer alten Nachricht vom KGIA-Oberst Delian von
                    Wiedbrück. Dieser hatte die Gezeichneten von Maraskan aus um Hilfe ersucht - vor über einem
                    Jahr.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Pforte des Grauens</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">16. RAH 1018 BF (Erdtag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Punin</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Solva</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sie erreichen Punin und werden von
                    der blinden Boron-Akoluthin Solva für den Folgetag zu einer Audienz im hiesigen Boron-Tempel
                    eingeladen.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Der Hochkönig</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Simia im Raben</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">17. RAH 1018 BF (Markttag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Punin</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Salandrion Farnion Finkenfarn,
                    Solva, Der Rabe von Punin, Gernot von Mersingen, Katalinya Adranez</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten besuchen zunächst die
                    Magierakademie und schildern Salandrion ihre Erfahrungen um Borbarad. Auch Verzauberungen einzelner
                    Waffen werden ausgehandelt. Danach folgen sie der Einladung des Raben von Punin und werden ganz
                    offiziell auf eine Expedition nach Maraskan entsandt.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Pforte des Grauens</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Zauberklinge Seelenherr</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Punin</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Oswin verschwindet zu Studienzwecken
                    in der Magierakademie und lässt dort von Interessierten erneut sein Auge analysieren. Die anderen
                    drei flanieren zum Ingerimmtempel und philosophieren dort über den Sinn von Opfergaben. Am Abend
                    finden sie die Erkennungsmarken der beiden Boronkirchen und ein wenig Handgeld auf ihrem Zimmer.</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Augenblicke am Abgrund</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">18. RAH 1018 BF (Praiostag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Punin bis Orbun-Quelle</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Salandrion Farnion Finkenfarn</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die drei holen Oswin und die vier
                    mit einem ZAUBERKLINGE verstärkten Waffen ab. Sherkan ersteht noch einen faustgroßen
                    Gwen-Petryl-Stein. Dann brechen sie mit ihren Pferden auf, wechseln an der Brücke über den Yaquir
                    Teile des Geldes in die tulamidische Fremdwährung und reisen bis zu einem Landgasthaus an der
                    Flussquelle des Orbun.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Aves in der Sternenleere</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">19. bis 23. RAH 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Orbun-Quelle bis zum Sattel des
                    Blutpasses</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sie erklimmen die Westseite des
                    Blutpasses und frieren in der nächtlichen Eiseskälte des hohen Gebirgkamms.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">24. RAH 1018 BF (Marktag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sattel des Blutpasses</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten genießen den Blick auf
                    das unter ihnen liegende Land.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">25. und 26. RAH 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sattel des Blutpasses bis
                    Erkenstein</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Sie wagen den Abstieg und erreichen
                    - trotz schmaler steiniger Pfade und Hängebrücken - das Dorf Erkenstein.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">27. bis 30. RAH 1018 BF</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Erkenstein bis Samra</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten reiten am Mhanadi
                    entlang, bis sie in Vishid auf einen flachen Flusskahn nach Samra wechseln können.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">30. RAH 1018 BF (Erdtag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Samra und Zhamorrah</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Im Gasthaus erfahren sie von
                    Zhamorrah und machen sich sofort auf den Weg dorthin. Über den Ruinen beobachtet Oswin das
                    spektakulär anzuschauende ausgefranste Ende einer großen Kraftlinie, die von der Wüste Gor her
                    kommt.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">01. NML 1018 BF (Markttag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Samra und Zhamorrah</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Beim Betreten der Ruinen von
                    Zhamorrah beginnt das Rubinauge plötzlich, Oswin eine schreckliche Vernichtungsszene aus der
                    Vergangenheit zu zeigen. Während sich ein starkes Gewitter nähert und ein geisterhafter Nebel
                    aufzieht, erlebt der Zauberer den monströsen Untergang eines Magiermoguls, der einst wohl ebenfalls
                    Träger des Auges gewesen war und einen wahren Kult um jenes Artefakt erschaffen hatte. Als Oswin am
                    Ende in sich zusammenbricht, retten sich die Gefährten zurück nach Samra. Nur dank des
                    Gwen-Petryl-Steins von Sherkan, welcher den geisterhaften Nebel erfolgreich zurückhält, schaffen sie
                    es zwar völlig durchnässt aber komplett unbeschadet in die Schänke.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Samra und Zhamorrah</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Nach einer für Oswin sehr unruhigen
                    Nacht kehren die Gefährten zu den Ruinen von Zhamorrah zurück, besiegen dort einige der getarnten
                    Chimärenschemen und lassen dem Zauberer viel Zeit, sich jene uralten und originalen Textpassagen des
                    Liber Zhammoricam per Satinav einzuprägen, welche in Runenform auf allen Steinobjekten prangen.</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Schemenhafte Beobachtungen</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">02. NML 1018 BF (Praiostag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Samra und Zhamorrah</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Mitten in der Nacht bringen die
                    Gefährten Durrandirs Erinnerungsstück - einen Kiesel von den Ruinen - an seinen Platz zurück, da
                    dieser merkwürdige düstere Beschwörungsformeln in Rssahh von sich gibt. Am folgenden Nachmittag
                    schlagen sie die Zeit auf verscheidenste Weisen tot: Mit Würfeln, Studieren, Kochen und
                    Schnitzarbeiten.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">03. NML 1018 BF (Rohalstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Samra</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten nutzen das Angebot
                    eines tollkühnen Schiffers und setzen mitten in den Namenlosen Tagen in Richtung Rashdul über.</p>
                </td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">05. NML 1018 BF (Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Mherwed und Rashdul</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Mherech ben Tuleyman ben Haschabnah,
                    Belizeth Dschelefsunni</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Über Mherwed und die dortige
                    Zauberschule des Kalifen landen sie in Rashdul. Oswin trifft sich in der Pentagramm-Akademie mit
                    Belizeth Dschelefsunni und verbringt viel Zeit zum fachlichen Austausch mit ihr als auch die gesamte
                    Nacht.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">01. PRA 1019 BF (Windstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Khunchom</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Belizeth Dschelefsunni</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Liebestrunken erwacht Oswin und
                    findet ein kleines Andenken von Belizeth. Noch am Vormittag nehmen die Gefährten ein Schiff Richtung
                    Khunchom.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Horas in den Rubinen</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">-&lt; 30.08.2018 &gt;-</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">03. PRA 1019 BF (Markttag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Khunchom</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Khadil Okharim, Kardin Dralût,
                    Garumin Dralût, &#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">04. PRA 1019 BF (Praiostag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskansund</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Glymbar geht nur unwillig an Bord
                    der Perlbeißer, einem seetüchtigen Schnellsegler. Das Schiff läuft noch im Morgengrauen aus, doch
                    sie kommen bei gutem Wetter nur leidlich voran.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">05. PRA 1019 BF (Rohalstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskansund</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Perlbeißer macht kaum Fahrt und
                    dümpelt bei strahlendem Sonnenschein im Maraskansund.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">06. PRA 1019 BF (Feuertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskansund</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Der Wind nimmt endlich zu. Haimamud
                    ibn Mhukkadin weicht gekonnt drei kaiserlichen Patrouillenschiffen aus, bis sie letztlich doch von
                    der Trollzacken gestellt werden.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskansund</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer, Horathio, Kapitän der Trollzacken</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Perlbeißer mit den Gefährten an
                    Bord wird auf hoher See von Horathio kontrolliert, dem schnöseligen Kapitän der Trollzacken. Der
                    Brief des Delian von Wiedbrück und Haimamuds Bestechungsgeld helfen jedoch zur Weiterfahrt. Dann
                    allerdings bricht ein magisch durchflochtener Orkan - ein Kauca - über sie herein, bei dem Efferd
                    dankenswerter Weise die Perlbeißer vor dem Schlimmsten bewahrt.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Kauca Arcana Titane</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">07. PRA 1019 BF (Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer, Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Mit der beinahe havarierten
                    Perlbeißer landen sie auf dem Sandstrand einer einsamen Bucht - und damit in einem Hinterhalt
                    maraskanischer Freiheitskämpfer.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Kor in Konjunktion mit dem
                    Kaiserstern</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer, Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Die Gefährten teilen sich auf.
                    Glymbar wird von dem Gift der Blasrohrprojektile sofort niedergestreckt. Oswin rettet sich in die
                    Tunnel, wird jedoch gefangengenommen und letztlich betäubt. Sherkan hechtet ebenfalls in die Anlage
                    und schickt dort nach und nach vier maraskanische Angreifer mit ihrem eigenen Gift in die Paralyse.
                    Beim finalen Versuch, Glymbar und Oswin zu befreien, wird er dann doch überwältigt. Durrandir tarnt
                    sich mit Hilfe elfischer Zauberkunst und sucht sich ein Versteck am Dschungelrand. Von dort aus
                    nimmt er die verbliebenen Gegner aufs Korn, welche sich an der havarierten Perlbeißer gesammelt
                    haben.</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Giftiger Tunnelkampf</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Haimamud ibn Mhukkadin, Kapitän der
                    Perlbeißer, Orsijin vom Hira (ehem. Orsino von Ragath), Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Wipfeltiger und Sumpfkrokodile</p>
                </td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">08. PRA 1019 BF (Windstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Orsijin vom Hira (ehem. Orsino von
                    Ragath), Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Nandus an der Pforte Uthar</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Orsijin vom Hira (ehem. Orsino von
                    Ragath), Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Das Fort der Verrückten</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">09. PRA 1019 BF (Erdstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Orsijin vom Hira (ehem. Orsino von
                    Ragath), Wipfeltiger</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">07. PRA 1019 BF (Wassertag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">-&lt; 04.10.2018 &gt;-</p></td>
            </tr>
            <tr>
                <td class="tableblock halign-left valign-top"><p class="tableblock">08. PRA 1019 BF (Windstag)</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">Maraskan</p></td>
                <td class="tableblock halign-left valign-top"></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#8230;&#8203;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
                <td class="tableblock halign-left valign-top"><p class="tableblock">&#160;</p></td>
            </tr>
            </tbody>
        </table>

    </section>
</div>

</body>
</html>
"""
}