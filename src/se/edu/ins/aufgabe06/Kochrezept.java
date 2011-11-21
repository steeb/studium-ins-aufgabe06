/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.util.Date;
import java.util.List;

/**
 *
 * @author steeb
 */
public class Kochrezept {

    private String titel;
    private String untertitel;
    private String kurzbeschreibung;
    private String author;
    private Date date;
    private List<Zutat> einkaufsliste;
    private List<String> rezeptschritte;

    public class Zutat {

        private String name;
        private String menge;
        private String bezeichner;
        
        public void setName(String name) {
            this.name = name;
        }
        
        public void setMenge(String menge) {
            this.menge = menge;
        }
        
        public void setBezeichner(String bezeichner) {
            this.bezeichner = bezeichner;
        }
        
        @Override
        public String toString() {
            return this.name + " (" + this.menge + " " + this.bezeichner + ")"; 
        }
        
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setUntertitel(String untertitel) {
        this.untertitel = untertitel;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        this.kurzbeschreibung = kurzbeschreibung;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setEinkaufsliste(List<Zutat> einkaufsliste) {
        this.einkaufsliste = einkaufsliste;
    }
    
    public void setRezeptSchritte(List<String> rezeptschritte) {
        this.rezeptschritte = rezeptschritte;
    }
    
    @Override
    public String toString() {
        String teil0 = "Titel: " + this.titel + 
                       "\nUntertitel: " + this.untertitel +
                       "\nKurzbeschreibung: " + this.kurzbeschreibung + 
                       "\nKoch: " + this.author +
                       "\nDatum: " + this.date + 
                       "\nZutat: ";
        for (Zutat z : einkaufsliste)
            teil0 += z + "\n";
        teil0 += "Schritte: ";
        for (String s : rezeptschritte)
            teil0 += s + "\n";
        return teil0;
    }
}
