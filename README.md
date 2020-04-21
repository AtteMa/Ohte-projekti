# Asteroids
versio Asteroids-pelistä. Pelaajalla on mahdollisuus aloittaa peli nimimerkillä. Sovellusta on mahdollista käyttää useammalla nimimerkillä ja jokaisella nimimerkillä on omat pisteet. Pisteet näkyvät reaaliajassa pelin kuluessa, mutta niitä on myös mahdollista tarkastella erilliseltä pistelistalta.


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/maarittelydokumentti.md)

[Tuntikirjanpito](https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/AtteMa/Ohte-projekti/releases/tag/Viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraprtti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

ohjelma suoritetaan komennolla

```
java -jar target/Asteroids-1.0-SNAPSHOT.jar
```

### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

