/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author steeb
 */
public class Kochrezept {

    private String titel;
    private String untertitel;
    private String kurzbeschreibung;
    private String author;
    private String kategorie;
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

        public String getBezeichner() {
            return bezeichner;
        }

        public String getMenge() {
            return menge;
        }

        public String getName() {
            return name;
        }
        
        @Override
        public String toString() {
            return this.name + " (" + this.menge + " " + this.bezeichner + ")"; 
        }
        
        public Element getAsXMLElement(Document doc) {
            Element eZutat = doc.createElement("zutat");
            eZutat.setAttribute("menge", this.getMenge());
            eZutat.setAttribute("bezeichner", this.getBezeichner());
            eZutat.setTextContent(this.getName());
            return eZutat;
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
    
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public List<Zutat> getEinkaufsliste() {
        return einkaufsliste;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public List<String> getRezeptschritte() {
        return rezeptschritte;
    }

    public String getTitel() {
        return titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public void setRezeptschritte(List<String> rezeptschritte) {
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
    
    public Element getAsXMLElement(Document doc) {
        Element eKochbuch = doc.createElement("kochbuch");
        eKochbuch.setAttribute("koch", this.getAuthor());
        eKochbuch.setAttribute("datum", new SimpleDateFormat("yyyy-mm-dd").format(this.getDate()));
        Element eInfo = doc.createElement("info");
        Element eTitel = doc.createElement("titel");
        eTitel.setTextContent(this.getTitel());
        eInfo.appendChild(eTitel);
        Element eUntertitel = doc.createElement("untertitel");
        eUntertitel.setTextContent(this.getUntertitel());
        eInfo.appendChild(eUntertitel);
        Element ekurzbeschreibung = doc.createElement("kurzbeschreibung");
        ekurzbeschreibung.setTextContent(this.getKurzbeschreibung());
        eInfo.appendChild(ekurzbeschreibung);
        eKochbuch.appendChild(eInfo);
        Element eEinkaufsliste = doc.createElement("einkaufsliste");
        for (Zutat z : einkaufsliste)
            eEinkaufsliste.appendChild(z.getAsXMLElement(doc));
        eKochbuch.appendChild(eEinkaufsliste);
        Element eRezept = doc.createElement("rezept");
        for (String s : rezeptschritte) {
            Element eSchritt = doc.createElement("anweisung");
            eSchritt.setTextContent(s);
            eRezept.appendChild(eSchritt);
        }
        eKochbuch.appendChild(eRezept);
        Element eKategorie = doc.createElement("kategorie");
        eKategorie.setTextContent(this.getKategorie());
        eKochbuch.appendChild(eKategorie);
        doc.appendChild(eKochbuch);
        return eKochbuch;
    }
}
