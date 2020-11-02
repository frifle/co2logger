servo c.4, 45
pause 1000
servopos c.4, 245
pause 1000
servopos c.4, off

main:
    pause 5000
    setfreq m1
    pause 100
    ; lies den ppm Wert ein, in Einheiten von 40us
    pulsin C.2, 1, w0
    setfreq m4    
    pause 100
   
    ; rechne in Millisekunden laut Datenblatt um
    w1 = w0 / 25 * 2 - 2
    
    ; beschneide ggf den Wert
    if w1 > 2000 then
        w1 = 2000
    end if
    if w1 < 400 then
        w1 = 400
    end if
    
    ; skaliere den ppm-Wert auf den Servo-Bereich
    ; Servobereich ca 225 - 47
    w2 = 2425 - w1 / 9 ; Achtung, gedachte Klammern
    
    ; debug
    
    ; Sende den Wert an den Servo, wenn verändert
    if b6 != b4 then
        servo c.4, b4
        pause 1000
        b6 = b4
        servopos c.4, off
    end if

	goto main
