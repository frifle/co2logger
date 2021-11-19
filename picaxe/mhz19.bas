#picaxe 08M2
#com /dev/ttyUSB0

#no_data

symbol pin_servo = C.4
symbol pin_mhz19 = C.2

servo pin_servo, 45
pause 1000
servopos pin_servo, 245
pause 1000
servopos pin_servo, off

main:
	; warte auf die nächste Messung
    pause 5000

    ; stelle den PICAXE auf 1MHz Taktfrequenz ein, damit er längere Pulse messen kann
    setfreq m1
    pause 100

    ; lies die Pulslänge ein, in Einheiten von 40us, siehe PICAXE-Manual
    pulsin pin_mhz19, 1, w0

    ; und gib wieder etwas Gas
    setfreq m4
    pause 100

    ; rechne in Millisekunden um
    w1 = w0 / 25

    ; rechne in PPM um gemäß Datenblatt vom MH-Z19
    ; Hier die Variante für einen Messbereich 0 - 2000 ppm
    w1 = w1 - 2 * 2   ; gedachte Klammern! Picaxe rechnet nicht Punkt- vor Strichrechnung.

    ; Hier die Variante für einen Messbereich 0 - 5000 ppm
    ; w1 = w1 - 2 * 5   ; gedachte Klammern! Picaxe rechnet nicht Punkt- vor Strichrechnung.


    ; beschneide ggf den Wert
    if w1 > 2000 then
        w1 = 2000
    end if
    if w1 < 400 then
        w1 = 400
    end if

    ; skaliere den ppm-Wert auf den Servo-Bereich
    ; Servobereich ca 225 - 47
    w2 = 2425 - w1 / 9 ; Achtung, wieder gedachte Klammern

    ; debug ; gibt die Werte ggf aus, damit wir u.a. den eingestellten Sensorbereich erkennen können

    ; Sende den Wert an den Servo, wenn verändert
    if b6 != b4 then
        servo pin_servo, b4
        pause 1000
        b6 = b4
        servopos pin_servo, off
    end if

    goto main
