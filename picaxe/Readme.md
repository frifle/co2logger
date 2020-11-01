# Eine einfach aufzubauende Version mit einem PICAXE im Bilderrahmen.

Der CO2-Bilderrahmen basiert auf dem kostengünstigen Sensor MH-Z19b sowie dem bekannten PICAXE-Mikrocontroller 08M2 (siehe ausführlich im aktuellen [MAKE-Sonderheft](https://www.heise.de/news/Sonderheft-Make-PICAXE-Special-jetzt-im-heise-shop-erhaeltlich-4889649.html)), welcher insbesondere durch Schüler einfach zu programmieren ist. Als Anzeige des CO2-Wertes verwenden wir einen kleinen Servo-Motor, der als Zeigerinstrument dient. Schließlich benötigen wir noch ein Programmiergerät für den PICAXE, entweder das Nano-Axe-Board aus dem Make-Sonderheft, oder das Pogrammierkabel vom PICAXE selbst, oder gar ein Eigenbau aus einem handelsüblichen USB-RS232-Adapter, siehe Ordner [Programmieradapter](programmieradapter). Eine Computer zur Erstellung des Programms brauchen wir natürlich auch. Lötkolben, Platinen, Widerstände, Kabel und Kleinmaterial sollten sich irgendwo in einer Bastelkiste finden lassen. Eine Stückliste ist unten zu finden.

<img src="../doc/pics/IMG_5365.JPG" title="Der CO2-Anzeiger auf der Anrichte" width="50%"/>

Wie man aus dem Datenblatt des Sensors entnehmen kann, gibt der MH-Z19b seinen Messwert auf verschiedene Arten und Weisen aus. Zum einen lässt er sich über ein serielles Protokoll auslesen. Dies wird von vielen Bauvorschlägen verwendet, die sich im Internet finden lassen. Allerdings ist die Implementierung von diesem Protokoll mit etwas Programmieraufwand auf Seiten des PICAXE verbunden, der sich nicht innerhalb einer Doppelstunde realisieren lässt. Diesen Aufwand treiben wir erst bei der Version mit dem [Raspberry](../raspberry).

Die andere Auswerteart ist zwar seltener zu finden, aber unvergleichlich viel einfacher zu verwenden. Der Sensor gibt über einen PWM-Ausgang Pulse aus, deren Länge der CO2-Konzentration entspricht. Die Pulslänge muss man noch nicht mal großartig umrechnen, um den CO2-Gehalt zu bekommen. 

```
    Die Pulslänge in Millisekunden entspricht der halben CO2-Konzentration im ppm, bis auf 2ms: 

          CO2(ppm)  = ( Pulslänge(ms) – 2 ms ) * 2 
    
    Wenn man also eine Pulslänge von 442ms misst, entspricht dies einer CO2-Konzentration von 880 ppm.
```

Wir benötigen also ein Programm für den PICAXE, welches eine Pulslänge misst und diese Länge dann in einen Wert für einen Servo umrechnet. Das wars, und das Resultat liegt [hier](mhz19.bas). Die Details zum Programm werden unten noch weiter erläutert.

Aber nun zum Aufbau.

## Vorbereitung des Bilderrahmes

Der Bilderrahmen sollte Bilder vom Format 13x18cm fassen können. Ich verwende hier ein sehr preisgünstiges Exemplar aus einem Möbelhaus mit schwedisch klingendem Namen. Als Besonderheit ist die "Glasplatte" des Rahmens nicht wirklich aus Glas, sondern aus einem recht dünnen transparenten Plastik, vielleicht Plexiglas.

Zunächst zeichnet euch eine passende CO2-Skala für den Bereich zwischen 400 und 2000ppm. Unter [../doc](../doc/CO2-Skala.pdf) liegt die Vorlage, die gerne verwendet werden kann. Die Skala wird auf die Maße des Rahmens zugeschnitten, bei mir also auf 13x18. Der Mittelpunkt der Skala ist mit einem kleinen "+" markiert, hier wird später der Zeiter vom Servo sitzen. Dieser Mittelpunkt sollte nach meiner Meinung aus optischen Gründen sowohl nach links und rechts als auch nach oben den gleichen Abstand (9cm) haben. Nach unten bleiben also 4cm Abstand zwischen Mittelpunkt und unterer Kante.

Baut die Skala in den Bilderrahmen ein, dann bohrt vorsichtig (am besten per Hand) durch Plexiglas, Skala und Rahmenrückwand ein Loch mit einem Durchmesser, so dass der Hals vom Servo dort durch passt (11mm bei mir). Der Servo ist allerdings nicht rund, sondern Birnen-förmig. Entsprechend muss das Loch noch vorsichtig ausgefeilt werden. Aber bitte vorsichtig, auch Plexiglas kann brechen. Das Resultat seht ihr hier:

<img src="../doc/pics/IMG_5344.JPG" title="Der vorbereitete Bilderrahmen" width="50%"/>






