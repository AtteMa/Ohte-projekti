![alt text](https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/kuvat/luokkakaavio.png)

sovellusta kuvaava luokkakaavio.

## Käyttöliittymä

Sovelluksen käyttöliittymässä on kolme näkymää:

-alkuvalikko
-pelinäkymä
-pistelista

Kaikki ovat omia Scene-olioita ja vain yksi näkymä on aina kerrallaan stagen käytössä.
Käyttöliittymän ohjelmakoodi löytyy luokasta Asteroids.ui.AsteroidsUi.

Sovelluslogiikka on eriytetty käyttöliittymästä. Käyttöliittymä kutsuu playerService-olion metodeja sovelluslogiikan toteuttamiseksi.

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
