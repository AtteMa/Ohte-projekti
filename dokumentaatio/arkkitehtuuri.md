![alt text](https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/kuvat/luokkakaavio.png)

sovellusta kuvaava luokkakaavio.

## Käyttöliittymä

Sovelluksen käyttöliittymässä on kolme näkymää:

- alkuvalikko
- pelinäkymä
- pistelista

Kaikki ovat omia Scene-olioita ja vain yksi näkymä on aina kerrallaan stagen käytössä.
Käyttöliittymän ohjelmakoodi löytyy luokasta Asteroids.ui.AsteroidsUi.

Sovelluslogiikka on eriytetty käyttöliittymästä. Käyttöliittymä kutsuu luokkien Ammunition, Asteroid, Spaceship, Player ja PlayerService metodeja sovelluslogiikan toteuttamiseksi.

## Sovelluslogiikka

Sovelluslogiikan muodostavat pääasiassa luokat Unit, PlayerService ja PolygonGenerator.

[Unit](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/Unit.java) on pelissä näkyvien piirrettyjen monikulmioiden yliluokka. Se tarjoaa seuraavat olennaiset metodit:

- void move() käsittelee monikulmion liikkumisen
- void accelerate() käsittelee liikkumiseen liittyvän kiihdytyksen
- boolean collide(Unit other) tarkistaa monikulmioiden törmäykset

[PlayerService](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/PlayerService.java) hoitaa pelaajan käsittely, ja tarjoaa seuraavat metodit:

- boolean createPlayer(String name, String points, int highScore) tallentaa uuden pelaajan jos pelaajaa ei löydy
- void updatePlayer(Player player, int highscore) päivittää pelaajan highscoren, jos samalla nimimerkillä pelataan uudestaan ja uusi highscore ylittää edellisen
- Player findPlayer(String name) palauttaa pelaajan nimen perusteella

[PolygonGenerator](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/PolygonGenerator.java) hoitaa asteroidien muodon satunnaisuuden, ja se tarjoaa yhden metodin:

- Polygon create() luo satunnaisen kokoisen ja muotoisen monikulmion

Luokkaan Unit liittyvät aliluokat [Ammunition](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/Ammunition.java), [Asteroid](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/Asteroid.java) ja [Spaceship](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/src/main/java/domain/Spaceship.java) rakentavat omat Unit-olionsa.

## Tiedon pysyväistallennus

Pakkauksen _Asteroids.dao_ luokka _FilePlayerDao_ hoitaa pelaajan tietojen tallentamisen tiedostoon.

Luokka on eristetty rajapinnan _PlayerDao_ taakse. Lisäksi luokan _PlayerService_ ansiosta luokkaa _FilePlayerDao_ ei käytetä suoraan.

### Tiedosto

Sovellus tallentaa pelaajan nimen, pisteet ja ennätyspisteet tiedostoon _playerFile.txt_. Tiedoston nimi on määritelty konfiguraatiotiedostossa [config.properties](https://github.com/AtteMa/Ohte-projekti/blob/master/Asteroids/config.properties).

Tiedot tallennetaan formaatissa

```
pelaaja1 0 0
```

ensin pelaajan nimimerkki, sitten pelin aikaiset pisteet ja lopuksi ennätyspisteet. sarakkeet erotellaan välilyönnillä.

## Sovelluksen heikkouksia

### Käyttöliittymä

Käyttöliittymän rakentava koodi on tällä hetkellä toteutettu kokonaan luokan _AsteroidsUi_ metodissa _start_. Sovelluksen eri näkymät olisivat voitu toteuttaa omina metodeinaan. Samoin pelin animoimisesta huolehtiva _AnimationTimer_-olio olisi voinut olla eriytettynä omaan metodiin, tai jopa omaan luokkaansa.
