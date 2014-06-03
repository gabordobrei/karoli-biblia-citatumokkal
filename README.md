karoli-biblia-citatumokkal
==========================

A neten sokfelé elérhető/letölthető a Károli féle Biblia, de olyan változatot nem találtam, amiben lennének citátumok, pláne nem olyat, ahol kezelhető formában lennének az adatok, ezért elkészítettem magam ezt a változatot. A nyers forrás <a href="http://www.bibl.u-szeged.hu/Biblia/" target="_blank">innen</a> származik.

A <code>biblia</code> mappában találhatók a letöltött (és minimálisan előkészített) fájlok, a <code>biblia.parsed</code> mappában van a feldolgozott JSON fájlok: minden fejezet külön fájlban, és az egész biblia is egyben, minden minimalizált és szép formátumban is.

A hierarchia a következő: <code>bible</code> / <code>book</code> / <code>chapter</code> / <code>verse</code> / <code>cite</code>. A citátumokról elérhető, hogy hova mutat (külön link is) és hogy az adott versben hol helyezkedik el. Minden fájl jól formázott JSON, könnyen át lehet konvertálni másik formátumra / adatbázisba tölteni, ahol már könnyű versek közötti linkeket definiálni.

Bármilyen észrevételt szívesen veszek: gabordobrei@gmail.com
