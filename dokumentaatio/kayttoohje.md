# Käyttöohje

Lataa viimeisimmän [releasen](https://github.com/AtteMa/Ohte-projekti/releases/tag/loppupalautus) tiedostot _asteroids.jar_, _config.properties_ ja _playerFile.txt_

## Konfigurointi

Sovellus olettaa että käynnistyshakemistosta löytyy konfiguraatiotiedosto _config.properties_, joka määrittelee pelaajat tallentavan tiedoston nimen. Konfiguraatiotiedoston sisältö on:

```
playerFile=playerFile.txt
```
Sovellus olettaa siis käynnistyshakemistosta löytyvän myös tiedosto _playerFile.txt_.

## Ohjelman käynnistäminen

Ohjelma käynnistetään suorittamalla komento

```

java -jar asteroids.jar

```

## Pelin aloittaminen

Sovellus aukeaa alkuvalikkonäkymään:

<img src="https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/kuvat/start.png" width="400">

Peliin pääsee kirjoittamalla nimimerkki tekstikenttään ja painamalla _start game_

## Pelaaminen

<img src="https://github.com/AtteMa/Ohte-projekti/blob/master/dokumentaatio/kuvat/game.png" width="400">

Pelissä ohjataan avaruusalusta jolla ammutaan ja väistellään asteroideja.
Aluksen liikuttaminen tapahtuu nuolinäppäimillä:
- VASEN ja OIKEA kääntävät alusta
- YLÖS ja ALAS kiihdyttävät alusta eteen ja taakse
- Aluksella ammutaan VÄLILYÖNTI näppäimellä.
