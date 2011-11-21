/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author steeb
 */
public class INSAufgabe06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException {
        KochbuchParser kochbuchParser = new KochbuchParser("/home/steeb/studium.git/ins/praktikum/uebung5/kochrezepte.xml");
        kochbuchParser.debugPrint();
    }
}
