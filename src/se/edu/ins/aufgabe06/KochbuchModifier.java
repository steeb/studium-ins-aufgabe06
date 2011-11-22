/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.util.List;

/**
 *
 * @author steeb
 */
public class KochbuchModifier {
    
    private List<Kochrezept> sammlung;
    
    public KochbuchModifier(List<Kochrezept> sammlung) {
        this.sammlung = sammlung;
    }
    
    public void modifyKategorie() {
        for (Kochrezept r : sammlung)
            r.setKategorie("Essen und Trinken");
    }

    public List<Kochrezept> getKochbuch() {
        return sammlung;
    }
    
}
