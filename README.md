# Textová adventura – Semestrální práce 4IT101

Tento projekt je semestrální prací pro předmět **4IT101** na VŠE. Jedná se o klasickou textovou adventuru s implementací v jazyce Java, běžící čistě v konzoli.

## 🎓 Autor
**Kajetan Franciszek Wielgosz**

## 📦 Verze
**Verze:** 1.0.0 

## 📖 O hře
Příběh se odehrává v kampusu VŠE. Hráč se ocitá v učebně informatiky ihned po skončení přednášky. Trpí extrémní žízní a navíc musí co nejrychleji najít cestu na další povinnou přednášku ekonomie do Likešovy auly. Cílem hry je zajistit si zdroj pitné vody (přes interakce s NPC postavami, nákup z automatů nebo plnění lahví), ukojit žízeň a bezpečně dorazit do cílové místnosti před vypršením časového limitu (počtu tahů).

## 🚀 Jak hru spustit
Hru je možné spustit dvěma způsoby:

1. **Manuální hraní v konzoli:**
   Spusťte hlavní třídu `Start.java`. Hra se spustí v interaktivním textovém režimu, kde zadáváte příkazy ručně.
   
2. **Automatické hraní ze souboru:**
   Ve třídě `Start.java` lze upravit spuštění tak, aby hra četla příkazy z předpřipraveného souboru `prikazy.txt` a svůj výstup logovala do `vystup.txt`. Toto je ideální pro rychlé otestování funkčnosti a kompletního průchodu hrou.

## 🛠️ Funkcionality
Projekt využívá klasické principy objektově orientovaného programování:
- **Návrhový vzor Command (Příkaz):** Každý příkaz (např. `jdi`, `seber`, `promluv`) má vlastní třídu a logiku.
- **Batoh (Singleton):** Inventář hráče s omezenou kapacitou a nosností.
- **NPC postavy:** Ve hře vystupují postavy (Dominik, Týpečka, atd.), se kterými lze mluvit (`promluv`) a provádět s nimi výměnné obchody (výměna učebnice za lahev atd.).
- **Omezené tahy:** Hráč má k dispozici pouze omezené množství tahů, než zmešká přednášku a prohraje.
- **Historie (Log):** Hra si pamatuje celou historii příkazů a dialogů, kterou lze kdykoliv vypsat příkazem `log`.

## 📚 Uživatelská příručka
Podrobnější pravidla, popis všech příkazů a tipy pro dohrání najdete v přiloženém souboru [prirucka.md](prirucka.md).

## 🧪 Testování
Projekt obsahuje sadu JUnit testů ve složce `tests/`. Tyto testy ověřují celkovou integritu hry (sebrání věcí, limity batohu, vyhrávající a prohrávající scénáře) tak, aby se logika dala spolehlivě zkontrolovat.
