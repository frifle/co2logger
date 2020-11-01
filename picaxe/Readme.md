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

Wir benötigen also ein Programm für den PICAXE, welches eine Pulslänge misst und diese Länge dann in einen Wert für einen Servo umrechnet. Das ist nicht schwer, und das Resultat liegt [hier](mhz19.bas). Die Details zum Programm werden unten noch weiter erläutert.

Aber nun zum Aufbau. Wir verwenden hier ein Steckboard, um die Lötarbeiten zu minimieren.

## Vorbereitung des Bilderrahmes

Der Bilderrahmen sollte Bilder vom Format 13x18cm fassen können. Ich verwende hier ein sehr preisgünstiges Exemplar aus einem Möbelhaus mit schwedisch klingendem Namen. Als Besonderheit ist die "Glasplatte" des Rahmens nicht wirklich aus Glas, sondern aus einem recht dünnen transparenten Plastik, vielleicht Plexiglas. Dadurch ist es möglich, diese Platte zu durchbohren.

Zunächst zeichnet euch eine passende CO2-Skala für den Bereich zwischen 400 und 2000ppm. Unter [../doc](../doc/CO2-Skala.pdf) liegt die Vorlage, die gerne verwendet werden kann. Die Skala wird auf die Maße des Rahmens zugeschnitten, bei mir also auf 13x18. Der Mittelpunkt der Skala ist mit einem kleinen "+" markiert, hier wird später der Zeiter vom Servo sitzen. Dieser Mittelpunkt sollte nach meiner Meinung aus optischen Gründen sowohl nach links und rechts als auch nach oben den gleichen Abstand (9cm) haben. Nach unten bleiben also 4cm Abstand zwischen Mittelpunkt und unterer Kante.

Baut die Skala in den Bilderrahmen ein, dann bohrt vorsichtig (am besten per Hand) am Mittelpunkt (+) durch Plexiglas, Skala und Rahmenrückwand ein Loch mit einem Durchmesser, so dass der Hals vom Servo dort durch passt (11mm bei mir). Der Servo ist allerdings nicht rund, sondern Birnen-förmig. Entsprechend muss das Loch noch vorsichtig ausgefeilt werden. Aber bitte vorsichtig, auch Plexiglas kann brechen. Das Resultat seht ihr hier:

<img src="../doc/pics/IMG_5344.JPG" title="Der vorbereitete Bilderrahmen" width="50%"/>
<img src="../doc/pics/IMG_5347.JPG" title="Der vorbereitete Bilderrahmen" width="50%"/>

Der Bilderrahmen hat hinten eine Stütze aus Pappe. Zwischen Rückwand und Stütze hat der Servo reichlich Platz. Der Motor kann dort mit etwas Klebstoff (ich nehme gerne 2-k-Kleber) fixiert werden.

<img src="../doc/pics/IMG_5349.JPG" title="Eingebauter Mikro-Servo, Ansicht von hinten" width="50%"/>

Der Servo wurde vermutlich mit mehreren Aufsetzen zur Montage von Zeigern o.ä. geliefert. Wählt den Aufsatz aus, welcher schon so aussieht wie ein Zeiger. Dieser kann verlängert werden, z.B. durch einen Strohhalm und etwas 2-k-Kleber.

<img src="../doc/pics/IMG_5364.JPG" title="Eingebauter Mikro-Servo, Ansicht Zeiger von vorne" width="50%"/>


## Vorbereitung vom Sensor MH-Z19b

Der Sensor MH-Z19b hat ungefähr die Maße einer halben Streichholzschachtel. Er besteht aus einer Platine und einem goldenen Metallgehäuse. Links und rechts vom Gehäuse hat die Platine 4 bzw 5 Lötaugen. Auf der Seite mit den fünf Augen ist noch eine Buchsenleiste angebaut. Letztere hat zum Gehäuse eine Sollbruchstelle, erkennbar durch eine Linie feiner Punkte.

<img src="../doc/pics/IMG_5350.JPG" title="Der Sensor MH-Z19b" width="50%"/>

Ihr könnt (müsst aber natürlich nicht) die Sollbruchstelle mit einem Cutter-Messer von beiden Seiten anritzen und die Buchsenleiste vorsichtig abbrechen. 

Die vier Lötaugen werden nun mit einer Stiftleiste versehen. Wenn ihr eine lange Stiftleiste besorgt habt, dann könnt ihr ein Teilstück von vier Stiften mit einem Drahtschneider abtrennen. Die Stiftleiste wird von unten durch die Lötaugen gesteckt, so dass die langen Stücke der Stifte nach unten zeigen.

<img src="../doc/pics/IMG_5351.JPG" title="Der Sensor MH-Z19b mit Stiftleiste von unten" width="50%"/>
<img src="../doc/pics/IMG_5352.JPG" title="Der Sensor MH-Z19b mit Stiftleiste von vorn" width="50%"/>

Die fünf Lötaugen bleiben frei, sie werden hier nicht benötigt. Für die Experimente mit dem Raspberry Pi habe ich allerdings dort eine Buchsenleiste _von oben_ angelötet.

## Die Stromversorgung.

Die Anzeige soll im Dauerbetrieb laufen, sie braucht also ein Netzteil. Der Sensor benötigt 5V mit einem Spitzenstrom von 150mA. Der Servo funktioniert bei 5V auch. Die Netzteile aus dem Fundus von alten Handy-Teilen liefern 5V bei mind. 500mA, das reicht. Ich nehme also ein solches Netzteil und baue es für den Einsatz auf dem Steckboard um.

Ich habe hier gerade ein Netzteil mit einem alten proprietären Stecker da. Den werde ich abschneiden und statt dessen ein Stück Stiftleiste montieren. Wenn ihr allerdings ein Netzteil mit USB-Stecker habt, dann würde ich mir eine Mikro-USB-Buchse besorgen und diese auf ein Stück Platine löten, siehe Vorschlag unten.

Doch nun zu meinem Stecker. Er wird abgeschnitten, die Drähte der Leitung werden getrennt und verzinnt.

<img src="../doc/pics/IMG_5354.JPG" title="Altes Netzteil" width="50%"/>
<img src="../doc/pics/IMG_5356.JPG" title="Netzteil mit abgetrenntem Stecker" width="50%"/>

Nehmt nun eine Stiftleiste mit sechs Stiften und zieht die mittleren beiden Stifte heraus. Legt die Stifte zur Seite, wir brauchen sie gleich noch. Die beiden Stiftpaare werden jeweils mit einem Draht verlötet.

<img src="../doc/pics/IMG_5357.JPG" title="Netzteil mit Stiftleiste" width="50%"/>

Wir sind nun soweit, einen ersten Test auf dem Steckboard zu machen. Hoffentlich sind die Stiftleisten durch die Wärme des 
Lötkolbens nicht zu sehr verbogen. Messt mit einem Multimeter aus, wo bei der Stromversorgung der (+)-Pol ist. Markiert ihn unbedingt, damit es nachher nicht zu Verwechselungen kommt. Die Stiftleiste der Stromversorgung k

<img src="../doc/pics/IMG_5358.JPG" title="Stromversorgung und Sensor auf dem Steckboard" width="50%"/>

Die oben angesprochene Alternative für Leute mit USB-Netzteil zeigen die folgenden zwei Bilder:

<img src="../doc/pics/IMG_5368.JPG" title="Stromversorgung-Adapter für ein USB-Netzteil" width="50%"/>

<img src="../doc/pics/IMG_5369.JPG" title="Stromversorgung-Adapter für ein USB-Netzteil" width="50%"/>
