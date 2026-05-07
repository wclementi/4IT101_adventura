package cz.vse.adventura.logika;

public class HerniPlan {
    
    private Prostor aktualniProstor;
    
    public HerniPlan() {
        zalozProstoryHry();
    }
    
    private void zalozProstoryHry() {
        Prostor domecek = new Prostor("domeček","domeček, ve kterém bydlí Karkulka");
        Prostor chaloupka = new Prostor("chaloupka", "chaloupka, ve které bydlí babička Karkulky");
        Prostor jeskyne = new Prostor("jeskyně","stará plesnivá jeskyně");
        Prostor les = new Prostor("les","les s jahodami, malinami a pramenem vody");
        Prostor hlubokyLes = new Prostor("hluboký_les","temný les, ve kterém lze potkat vlka");

        domecek.setVychod(les);
        les.setVychod(domecek);
        les.setVychod(hlubokyLes);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(jeskyne);
        hlubokyLes.setVychod(chaloupka);
        jeskyne.setVychod(hlubokyLes);
        chaloupka.setVychod(hlubokyLes);
                
        aktualniProstor = domecek;
    }
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
}