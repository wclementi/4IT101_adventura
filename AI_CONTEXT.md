# AI Context: 4IT101 Adventura (Větve `dev`)

## O projektu (Project Overview)
Tento projekt je semestrální prací typickou pro předměty z rodiny 4IT na VŠE. Jedná se o textovou adventuru v jazyce Java. 
**Příběh a cíl:** Hráč je studentem na VŠE. Nachází se v aule a má obrovskou žízeň. Cílem hry je najít automat "lokni si" (v bufetu), pomocí mincí si koupit nápoj a vypít ho, čímž hráč vyhraje (jinak zkolabuje).

## Architektura a Návrhové vzory
Tato verze hry pokročila (12. týden) a implementuje několik pokročilých OOP principů a návrhových vzorů:
*   **Factory Pattern:** Třída `PrikazFactory` zodpovídá za instancování a vkládání potřebných závislostí do jednotlivých příkazů.
*   **Singleton Pattern:** Inventář hráče (`Batoh`) je implementován jako návrhový vzor Singleton (přístupný přes `Batoh.getInstance()`).
*   **Stavový automat (Enum):** Výhra, prohra a průběh hry jsou řízeny výčtovým typem `StavHry` (PROBIHA, VYHRA, PROHRA).
*   **Zpracování chyb:** Nesprávné herní interakce (např. pití neexistující věci, přenášení nepřenositelných objektů) jsou ošetřeny vyhazováním a chytáním výjimek (`throws Exception`).

## Současný stav balíčku `logika`
*   **Lokace (Prostory):** aula, chodba, bufet.
*   **Předměty (Věci):** mince (přenositelná), automat (nepřenositelný), nápoj (přenositelný, lze jej koupit v automatu).
*   **Příkazy:** 
    *   `jdi`, `konec`, `nápověda`
    *   `seber <věc>`, `polož <věc>` (manipulace s prostorem a batohem)
    *   `prozkoumej <věc>` (získání popisu)
    *   `kup <věc>` (nákup v automatu za mince)
    *   `pij <věc>` (vypitím nápoje se aktivuje `StavHry.VYHRA`)
    *   `vypiš` (výpis obsahu batohu)
*   **Postavy (NPC):** K dispozici jsou třídy `NPCPouzeMluvici` a `NPCSpoluzak`, které zatím nejsou plně zapojeny do hlavní mapy.

## Instrukce pro AI Agenty (Guidelines for AI)
1.  **Jazyk a konvence:** Kód i komentáře se píší v **češtině**. Jména proměnných a tříd jsou bez diakritiky, výpisy pro uživatele (Strings) s diakritikou.
2.  **Přidávání příkazů:** Jakýkoliv nový příkaz musí implementovat `IPrikaz`. Jeho vytvoření a předání závislostí (např. `HerniPlan`, `Batoh`, `Hra`) MUSÍ proběhnout přes `PrikazFactory`.
3.  **Přístup k inventáři:** Pro operace s inventářem z jakékoliv třídy využívejte `Batoh.getInstance()`. Nepředávejte si ho jako nový objekt.
4.  **Ošetřování chybových stavů:** Všechny netriviální logické chyby při manipulaci s předměty v `HerniPlan` zpracovávejte pomocí výjimek, nikoliv pouhým vracením textu. Příkazy tyto výjimky zachytí v bloku `try-catch` a teprve poté vrátí chybovou hlášku jako text pro UI.
5.  **Oddělení UI:** Logika nikdy přímo nepíše na konzoli (`System.out.println`). Vše vracejí příkazy nebo hlavní třída `Hra` jako `String`.
