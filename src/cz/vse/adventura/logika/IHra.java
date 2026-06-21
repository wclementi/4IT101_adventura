package cz.vse.adventura.logika;

/**
 * Rozhraní pro hru, zajišťuje oddělení UI od logiky hry.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public interface IHra
{
    public String vratUvitani();
    
    public String vratEpilog();
    
    public boolean konecHry();
     
    public String zpracujPrikaz(String radek);
   
    public HerniPlan getHerniPlan();
}
