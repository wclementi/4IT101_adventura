# Uživatelská příručka: Přežij na VŠE (Adventura 4IT101)

**Autor:** Kajetan Franciszek Wielgosz

## O hře
Přežij na VŠE je textová adventura vytvořená jako semestrální projekt. Ocitáte se na Vysoké škole ekonomické (VŠE) hned po náročné přednášce 4IT101. Vaším cílem je uspokojit nesnesitelnou žízeň a včas dorazit na další, tentokrát povinnou přednášku, která rozhodne o vaší akademické budoucnosti.

## Cíl hry
1. Prozkoumat areál VŠE.
2. Získat peníze (mince).
3. Koupit si nápoj z automatu a vypít ho (uhasit žízeň).
4. Najít správnou aulu (Likešova aula) a včas se posadit na přednášku.

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
- `vypiš` – Vypíše aktuální obsah vašeho batohu.
- `prozkoumej <prostor/předmět>` – Podá detailní informace o věci nebo místě.
- `kup <předmět>` – Pokud stojíte u automatu, koupí požadovaný předmět.
- `naplň <lahev>` – U speciálních automatů (např. LOKNI SI) naplní prázdnou lahev vodou.
- `pij <nápoj>` – Vypije nápoj a zruší vaši žízeň.
- `promluv <postava>` – Zahájí rozhovor s NPC postavou v místnosti.
- `rozbij <předmět>` – Agresivní řešení problémů s automatem (použít na vlastní nebezpečí!).
- `sedni` – Sedne si na místo. Pokud jste ve správné aule a máte zahnánu žízeň, vyhrajete.
- `nápověda` – Zobrazí tento seznam příkazů přímo ve hře.
- `log` – Zobrazí kompletní historii vašich dosavadních kroků (příkazy a odpovědi).
- `konec` – Okamžitě hru ukončí.

## Automatické hraní ze souboru
Hra podporuje i zadávání příkazů ze souboru, což je ideální pro testování. Můžete využít přiložený soubor `prikazy.txt`.
K jeho využití stačí v kódu (třída `Start`) zavolat metodu `ui.hrajZeSouboru("prikazy.txt");` místo klasického `ui.hraj();`. Výstup celé hry se následně i s příkazy vygeneruje do souboru `vystup.txt`.

---
Hodně štěstí při studiu na VŠE a pozor na dehydrataci!
