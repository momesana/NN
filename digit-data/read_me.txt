===============================================================
I.)		Mitgelieferte Dateien 
===============================================================

	Die Mustererkennungsdaten sind aufgeteilt in die folgenden 
drei ASCII - Dateien (also alle direkt lesbar):

--------------------------------------------------------------------
1) DIGITS.TRN:	1000 binarisierte und skalierte Ziffern zum 
				TRAINIEREN der Klassifikatoren (Format s.u.).
2) DIGITS.TST:	 200 binarisierte und skalierte Ziffern zum 
				TESTEN der trainierten Klassifikatoren (Format s.u.).
3) VISUAL.C		Mini-Programm zum besseren Visualisieren der Ziffern.
				'Gebrauchsanweisung' s.u.
--------------------------------------------------------------------

===============================================================
II.)	Datenformate 
===============================================================

	Die Digits-Dateien sind ASCII-Dateien. Sie enthalten bereits
vorverarbeitete (binarisierte, aufgerichtete und auf eine Groesse
von 12x16 Pixeln skalierte) Bilder handgeschriebener Ziffern (0-9)
mit entsprechender Label-Information. 

	Die Bilder sind zeilenweise als Pixel abgespeichert (16 Zeilen 
a 12 Pixel), wobei jeder Pixel durch seinen Grauwert g (0<=g<=1.0)
repraesentiert wird (je dunkler der Pixel, desto groesser ist g). 

	Die Label-Information ist '1 aus N' - kodiert, d.h. im Anschluss
an jedes Muster stehen 10 Werte, wobei die Index der 1.0 genau die
Ziffer kodiert. Beispiel:

                       ==> Ziffer ist eine '3' 
                      V
-------------------------------------------------
Label : |0.0|0.0|0.0|1.0|0.0|0.0|0.0|0.0|0.0|0.0|
-------------------------------------------------
Stelle: | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
					  
					
===============================================================
III.)	Mini - Visualisierungs - Programm
===============================================================

	VISUAL.C ist ein Mini-Programm, das aus den Digit-Dateien
etwas anschaulichere Bilder generiert. Nach Kompilierung (laeuft 
unter DOS Borland - C++, hoffentlich auch unter UNIX) und Aufruf 
einfach den Namen der Namen der Digit-Datei eingeben. Das 
Programm erzeugt (oder ueberschreibt) dann eine Datei DIGITS.ASC 
zum Anschauen.
