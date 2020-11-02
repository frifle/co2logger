# Eigenbau eines Programmieradapters für die PICAXE-Mikrocontroller

Das Programmierkabel von Picaxe ist nach meiner Meinung recht teuer. Aber die Kollegen von Revolution Education waren so nett,
sogar in PICAXE-Manual [Nr 1, Abschnitt Serial Download Circuit](https://picaxe.com/docs/picaxe_manual1.pdf) einen Vorschlag für eine Eigenbau-Variante des Programmierkabels zu machen. Es wird dazu ein handelsüblicher RS232-USB-Adapter benötigt, der für deutlich weniger Geld (unter 10€) zu haben und vielseitiger verwendbar ist. Der Schaltplan dazu ist simple:

<img src="Schaltplan_Programmieradapter.png" title="Schaltplan vom Programmieradapter" alt="Schaltplan vom Programmieradapter" width="50%"/>

Hier sieht man meine eigene Version eines Programmieradapters. Es ist ein fliegender Aufbau, den ich in etwas Zweikomponenten-Kleber eingegossen hab:

<img src="programmier_adapter.jpg" title="Bild vom Programmieradapter" alt="Bild vom Programmieradapter" width="50%"/>


Bauteil | Anzahl | Kosten | Bezugsquelle (Beispiel)
--------|--------|--------|------------------------
RS232-Adapter | 1 | ca 7€ | [Reichelt](https://www.reichelt.de/usb-2-0-konverter-a-stecker-auf-rs-232-1-5-m-usb2-seriell-p58641.html) oder Amazon, Ebay...
Widerstand 10kOhm | 1 | 0,10€ | [Reichelt](https://www.reichelt.de/widerstand-kohleschicht-10-kohm-0207-250-mw-5--1-4w-10k-p1338.html)
Widerstand 22kOhm | 1 | 0,10€ | [Reichelt](https://www.reichelt.de/widerstand-kohleschicht-22-kohm-0207-250-mw-5--1-4w-22k-p1384.html)
SUB-D-9 Buchse | 1 | 0,15€ | [Reichelt](https://www.reichelt.de/d-sub-buchse-9-polig-loetkelch-d-sub-bu-09-p6948.html)
Anschlusskabel-Set | 1 | 2 € | [Reichelt](https://www.reichelt.de/entwicklerboards-kabel-set-verschiedene-laengen-65er-pack--debo-kabelset18-p282690.html)