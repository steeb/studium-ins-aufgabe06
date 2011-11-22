/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import se.edu.ins.aufgabe06.Kochrezept.Zutat;

/**
 *
 * @author steeb
 */
public class KochbuchWriter {

    private OutputStreamWriter out;

    public KochbuchWriter(String file) throws FileNotFoundException, UnsupportedEncodingException {
        out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), "UTF-8");
        System.out.println("opening file writer");
    }

    public void write(List<Kochrezept> sammlung) throws IOException{
        System.out.println("start writing ...");
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        out.write("<!DOCTYPE sammlung SYSTEM \"kochrezepte.dtd\">\n");
        out.write("<sammlung>\n");
        for (Kochrezept r : sammlung) {
            out.write("\t<kochrezept koch=\"" + r.getAuthor() + "\" datum=\"" + new SimpleDateFormat("yyyy-mm-dd").format(r.getDate()) + "\">\n");
            out.write("\t\t<info>\n");
            out.write("\t\t\t<titel>" + r.getTitel() + "</titel>\n");
            out.write("\t\t\t<untertitel>" + r.getUntertitel() + "</untertitel>\n");
            out.write("\t\t\t<kurzbeschreibung>" + r.getKurzbeschreibung() + "</kurzbeschreibung>\n");
            out.write("\t\t</info>\n");
            out.write("\t\t<einkaufsliste>\n");
            for (Zutat z : r.getEinkaufsliste())
                out.write("\t\t\t<zutat menge=\"" + z.getMenge() + "\" bezeichner=\"" + z.getBezeichner() + "\">" + z.getName() + "</zutat>\n");
            out.write("\t\t</einkaufsliste>\n");
            out.write("\t\t<rezept>\n");
            for (String s : r.getRezeptschritte())
                out.write("\t\t\t<anweisung>" + s + "</anweisung>\n");
            out.write("\t\t</rezept>\n");
            out.write("\t\t<kategorie>" + r.getKategorie() + "</kategorie>\n");
            out.write("\t</kochrezept>\n");
        }
        out.write("</sammlung>\n");
        out.close();
        System.out.println("... stop writing");
    }
}
