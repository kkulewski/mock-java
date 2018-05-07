# Testowanie aplikacji JAVA 2017-2018
| Travis CI Status | Maintainability | BetterCodeHub | Hits |
:--:|:--:|:--:|:--:
[![Build Status](https://travis-ci.org/kkulewski/mock-java.svg?branch=master)](https://travis-ci.org/kkulewski/mock-java) | [![Maintainability](https://api.codeclimate.com/v1/badges/f1971e2079359aeaf82d/maintainability)](https://codeclimate.com/github/kkulewski/mock-java/maintainability) | [![BCH compliance](https://bettercodehub.com/edge/badge/kkulewski/mock-java?branch=master)](https://bettercodehub.com/) | [![HitCount](http://hits.dwyl.io/kkulewski/mock-java.svg)](http://hits.dwyl.io/kkulewski/mock-java)

## Projekt 2 (Maven, JUnit oraz atrapy) - temat 6 (25 pkt)

Rozważmy bardzo uproszczoną aplikacje w sklepie internetowym, która jest opisana danym diagramem związków encji:
![Diagram ERD](https://inf.ug.edu.pl/~mmiotk/Dydaktyka/2016-2017/TAJAVA2016-2017/ERD.png)

Dane do bazy mają być przechowywane w jakimś systemie bazodanowym. Zaimplementuj odpowiednie metody tej aplikacji i przetestuj ją uwzględniając wymagania zawarte w diagramie używając atrap.

Pod ocenę będą brane pod uwagę następujące elementy:
- (0.5 pkt) Kompilacja i uruchomienie bezbłędne projektu + konfiguracja TravisCi.
- (4 pkt) Uwzględnienie powyższych wymagań.
- (6 pkt) Przypadki testowe (uwzględniające wyjątki).
- (5 pkt) Przetestowanie przy użyciu ręcznie stworzonych atrap (co najmniej 10 testów, różnych od pozostałych)
- (4 pkt) Przetestowanie przy użyciu Mockito (co najmniej 10 testów, różnych od pozostałych).
- (4 pkt) Przetestowanie przy użyciu EasyMock (co najmniej 10 testów, różnych od pozostałych).
- (0.5 pkt) Pokrycie kodu (w przypadku ręcznie stworzonych atrap).
- (1 pkt) Styl kodu.

Ponadto, jako punkty dodatkowe będą brane następujące elementy: 
- (1 pkt) Użycie różnych rodzaji atrap.
- (1 pkt) Wynik z portalu BetterCodeHub.
- (2 pkt) Inne technologie dotyczące atrap, nie pokazywane na zajęciach (co najmniej po 5 testów każda z nich).
- (1 pkt) Integracja repozytorium z dowolnym serwisem.
- (1 pkt) Użycie JUnit5.

Ponadto pod ocenę jest brane również: (Brak tych elementów: -1 pkt za podpunkt od obowiązkowej
punktacji zadania!)
- Historia projektu w repozytorium.
- Różnorodne asercje (co najmniej 5 różnych).
- Struktura projektu.

