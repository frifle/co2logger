# CO2-Anzeige mit PICAXE oder Raspberry PI – kostengünstig, einfach und schnell aufzubauen.

Der Herbst ist da, und wir alle beschäftigen uns mit der Frage, uns und unseren Kindern einen möglichst sicheren Arbeits-,
Schul- und Kitabesuch zu ermöglichen. Wie können wir vermeiden, uns mit dem leidigen Corona-Virus anzustecken? Da wäre die dringende Empfehlung, die Räume regelmäßig zu lüften, um dadurch die Konzentration an Aerosolen, die wir nun mal unvermeidlich ständig ausatmen, zu reduzieren. Nur - wann soll gelüftet werden, pauschal alle 20min wie es aktuell die Schulen praktizieren? Das ist sicherlich für eine Schulklasse als grobe Faustregel gut, aber privat ist das übertrieben. Hier brauchen wir etwas Hilfe, eine Lüftungs-Anzeige wäre sehr wünschenswert.

Nun atmen wir ja nicht nur Aerosole, sondern insbesondere CO2 aus. In einem Raum, in dem sich eine Gruppe von Menschen aufhält und entsprechend atmet, steigt der Gehalt der Aerosole zusammen mit der CO2-Konzentration. Und letztere lässt sich messen. Wenn sich der CO2-Gehalt von einem Normalwert von 400ppm im Raum auf etwa 1000ppm erhöht hat, gilt als Empfehlung: Fenster auf!

Für den CO2-Gehalt der Luft gibt es zahlreiche fertige Messgeräte und Sensoren, und es gibt aktuell viele Selbstbau-Vorschläge in allen Medien. Auch das [Make-Magazin](https://www.heise.de/make/) hatte in seiner letzten Ausgabe eine CO2-Ampel ausführlich vorgestellt.

An dieser Stelle möchte ich meine persönlichen Versuche für eine CO2-Anzeige dokumentieren. Zum einen möchte ich einen Vorschlag machen, welcher sehr einfach und kostengünstig aufzubauen ist. Ein CO2-Bilderrahmen ist für unter 50€ zu bekommen, und er lässt sich daheim oder zusammen mit Schülern (und Eltern) innerhalb von etwa zwei Stunden aufbauen. In meinem zweiten Aufbau probiere ich ein paar Dinge aus, die mich schon lange in den Fingern jucken: einen IOT-Service auf dem Raspberry-Pi, gebaut mit Java und [quarkus](https://quarkus.io/) ausgewertet durch [Prometheus](https://prometheus.io) und [Grafana](https://grafana.com). Beide Varianten basieren auf dem kostengünstigen Sensor [MH-Z19b](https://www.winsen-sensor.com/sensors/co2-sensor/mh-z19b.html), siehe auch das [Datenblatt](doc/MH-Z19B-Datasheet.pdf).

Damit wir uns nicht missverstehen: so ein Selbstbau-Sensor kann natürlich immer nur zusätzliche Hinweise geben, wann das Lüften gerade notwendig sein könnte. Bitte verzichtete nicht auf den gesunden Menschenverstand! Wenn es mieft, dann ist die Luft verbraucht und entsprechend ist der Aerosolgehalt hoch. Wenn ihr selber merkt, dass es eine Lüftung braucht, dann öffnet bitte die Fenster.

## Ein CO2-Bilderraumen mit PICAXE, einfach und schnell aufgebaut.

Die [einfachste Variante](picaxe) verwendet einen PICAXE 08M2-Mikrocontroller mit einem Micro-Servo als Anzeige. Dieser Aufbau passt gut
hinter einen Bilderrahmen und kann somit auf Omas Anrichte neben den Fotos der Enkelkinder drapiert werden. Ebensogut macht er
sich auf einem Lehrerpult oder dem Aktenschrank im Büro.

<img src="doc/pics/IMG_5365.JPG" title="Der CO2-Anzeiger auf der Anrichte" width="50%"/>

Die Beschreibung für diesen Aufbau findet sich im Ordner [picaxe](picaxe). Der Bilderrahmen ist in etwa zwei Stunden zu bauen (wer noch nie einen PICAXE programmiert hat, braucht etwas länger), die Bauteile sind für unter 50€ zu haben.

## Die Profi-Version mit Raspberry-Pi, quarkus, prometheus und grafana - IOT pur.

Die RasPi-Version ist im Ordner [raspberry](raspberry) beschrieben. Hiermit sind nicht nur der aktuelle Wert der CO2-Konzentration auswertbar, sondern ganze Zeitreihen von diversen Parametern des Sensors MH-Z19b.

![Ein Screenshot einer morgentlichen Grafana-Auswertung]( doc/pics/Grafana_screenshot.png "Ein Screenshot einer morgentlichen Grafana-Auswertung")

## Arbeitsweise und Pflege vom Sensors MH-Z19b

CO2 ist bekanntlich ein Klimagas, welches Wärmestrahlung streut. Genau dieser Effekt wird durch den Sensor MH-Z19b ausgenutzt. Er verwendet zur Messung der CO2-Konzentration eine Absorptionslinie im nahen Infrarotbereich des Lichts.

Der Sensor enthält im Inneren eine kleine Messstrecke, über welche etwas Licht durch die Luft im Metallgehäuse des Sensors auf ein IR-Detektor trifft. Je weniger Licht der Detektor messen kann, desto mehr CO2 wird in der Luft sein. Der Gasaustausch zwischen Gehäusekammer und der Umgebung erfolgt durch die zwei weißen Diffusionsfenster. Denn Messvorgang kann man sogar sehen. Im Inneren des Sensors leuchtet eine Lichtquelle alle paar Sekunden auf.

Wenn man dieses Messprinzip betrachtet, wird man verstehen, dass der Sensor zwar gut relative Veränderungen messen kann, allerdings Schwierigkeiten mit absolute Werten der CO2-Konzentration hat. Hierfür braucht er eine Kalibrierung auf einen Wert, der gut bekannt ist. Empfohlen wird, den Sensor regelmäßig für mind. 20min in einen frisch gelüfteten Raum zu setzen. Dort nämlich kennen wir die CO2-Konzentration, etwa 400ppm beträgt sie durchschnittlich an der frischen Luft. Der Sensor merkt sich innerhalb von 24h den tiefsten gemessenen Wert und nimmt an, dies seien 400ppm. Er misst also immer relativ zu diesem tiefsten gemessenen Wert eines Tages.

Kalibrierung ist teuer - und unser Sensor ist billig. Dies ist mit  Sicherheit irgendwo ein Widerspruch, und entsprechend finden sich auch gelegentlich Diskussionen zur Verwendung des Sensors (zB Diskussion im [mikrocontroller.net](https://www.mikrocontroller.net/topic/470382) bei einem Messgerät im Hühnerstall). Auch mir ist bei meinen sechs Exemplaren, die ich hier zu Tests auf dem Tisch hatte, eines untergekommen, welches eher als Zufallsgenerator denn als Messgerät taugt. Es gibt ein dermaßen verrauschtes Signal aus (800ppm +- 500ppm), so dass es sogar für die sehr einfachen Zwecke des CO2-Bilderrahmens nicht zu gebrauchen ist.

Ich denke aber, dass wir für die Zwecke hier mit dem MH-Z19b einen guten Sensor gefunden haben. Allerdings gibt es Regeln zu beachten:

1. Der Sensor muss 24x7 an der Stromversorgung hängen, damit er die Kalibrierungs-Zyklen durchführen kann.

2. Mindestens einmal am Tag muss er wirklich an der Frischluft sein, so dass er stets relativ zu den 400ppm messen kann.

3. Feuchtigkeit verträgt er gar nicht - auch nicht Küchendämpfe oder Staub. Also bitte einen trockenen und sauberen Standort suchen.

4. Auch Wärme wie z.B. Heizungsluft oder direkte Sonneneinstrahlung muss gemieden werden. Zwar soll der Sensor die CO2-Konzentration temperaturkompensiert ausgeben. So recht traue ich dieser Funktion allerdings nicht.

5. Manche Sensoren sind einfach kaputt. Ein Zweitsensor zum Vergleich hilft hier.

