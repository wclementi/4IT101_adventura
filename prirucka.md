# Uživatelská příručka: Přežij na VŠE (Adventura 4IT101)

**Autor:** Kajetan Franciszek Wielgosz

## O hře
Přežij na VŠE je textová adventura vytvořená jako semestrální projekt. Ocitáte se na Vysoké škole ekonomické hned po náročné přednášce 4IT101. Vaším cílem je uspokojit nesnesitelnou žízeň a včas dorazit na další, tentokrát povinnou přednášku, která rozhodne o vaší akademické budoucnosti. Stihnete to včas?.

## Cíl hry
1. Prozkoumat areál VŠE.
2. Uhasit žízeň (existuje několik možností).
3. Dostavit se včas na další přednášku a zjištit kde se koná.

Dejte si pozor na limit tahů! Pokud se budete po škole bezcílně potulovat příliš dlouho, zmeškáte přednášku a hra končí neúspěchem.

## Jak hru spustit
Hru spustíte z příkazového řádku (CMD, PowerShell) pomocí připraveného JAR souboru.
Pro bezproblémové zobrazení české diakritiky doporučujeme použít UTF-8 kódování.

```cmd
chcp 65001
java -jar adventura.jar
```
*(Pokud je k projektu přibalen soubor `spust.bat`, můžete využít přímo ten).*

## Herní příkazy
Hra se ovládá zadáváním textových příkazů. Po spuštění na vás vždy čeká výzva `> `, kam zadáváte svůj příkaz. 

Zde je seznam platných příkazů:
- `jdi <prostor>` – Přesune vás do sousedního prostoru (např. `jdi schodiste`).
- `seber <předmět>` – Zvedne předmět a uloží ho do vašeho batohu.
- `polož <předmět>` – Vyhodí předmět z batohu do aktuální místnosti.
- `vypiš <prostor/batoh>` – Vypíše buď aktuální obsah vašeho batohu nebo prostoru.
- `prozkoumej <prostor/předmět>` – Podá detailní informace o věci nebo místě.
- `kup <předmět>` – Pokud stojíte u automatu, koupí požadovaný předmět.
- `naplň <lahev>` – U speciálních automatů (např. LOKNI SI) naplní prázdnou lahev vodou.
- `pij <nápoj>` – Vypije nápoj a zruší vaši žízeň.
- `promluv <postava>` – Zahájí rozhovor s NPC postavou v místnosti.
- `rozbij <předmět>` – zjisti ve hře co se stane (vyžaduje předmět v batohu).
- `sedni` – Sedne si na volné místo, pokud se nějaké nachází v prostoru. Nutné pro ukončení hry.
- `nápověda` – Zobrazí seznam příkazů přímo ve hře.
- `log` – Zobrazí kompletní historii vašich dosavadních kroků (příkazy a odpovědi).
- `konec` – Okamžitě hru ukončí.

## Automatické hraní ze souboru
Hra podporuje i zadávání příkazů ze souboru, což je ideální pro testování. Můžete využít přiložený soubor `prikazy.txt`.
K jeho využití stačí v kódu (třída `Start`) zavolat metodu `ui.hrajZeSouboru("prikazy.txt");` místo klasického `ui.hraj();`. Výstup celé hry se následně i s příkazy vygeneruje do souboru `vystup.txt`.

---
Hodně štěstí při studiu na VŠE a pozor na dehydrataci!
