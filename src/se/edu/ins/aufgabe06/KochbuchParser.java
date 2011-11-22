/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author steeb
 */
public class KochbuchParser {
    
    XMLReader parser;
    List<Kochrezept> sammlung = new ArrayList<Kochrezept>();
    
    public KochbuchParser (String uri) throws SAXException, IOException {
         parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
         parser.setContentHandler(new KochbuchContentHandler());
         parser.setFeature("http://xml.org/sax/features/validation", true);
         parser.parse(uri);
    }
    
    public List<Kochrezept> getSammlung() {
        return sammlung;
    }
    
    public void debugPrint () {
        for (Kochrezept kr : sammlung) {
            System.out.println(kr);
        }
    }
    
    class KochbuchContentHandler implements ContentHandler {
        
        private Locator lctr;
        boolean activeTitel;
        boolean activeUntertitel;
        boolean activeKurzbeschreibung;
        boolean activeZutat;
        boolean activeAnweisung;
        
        Kochrezept rezept;
        List<Kochrezept.Zutat> einkaufsliste;
        Kochrezept.Zutat zutat;
        List<String> rezeptSchritte;
        String anweisung;

        @Override
        public void setDocumentLocator(Locator lctr) {
            this.lctr = lctr;
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Parsing beginns ...");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("... Parsing stops");
        }

        @Override
        public void startPrefixMapping(String string, String string1) throws SAXException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void endPrefixMapping(String string) throws SAXException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void startElement(String namespaceURI, String localName, String rawName, Attributes atrbts) throws SAXException {
            if (rawName.equals("kochrezept")) {
                rezept = new Kochrezept();
                rezept.setAuthor(atrbts.getValue("koch"));
                try {
                    rezept.setDate(new SimpleDateFormat("yyyy-mm-dd").parse(atrbts.getValue("datum")));
                } catch (ParseException ex) {
                    Logger.getLogger(KochbuchParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(rawName.equals("titel"))
                activeTitel = true;
            else if(rawName.equals("untertitel"))
                activeUntertitel = true;
            else if(rawName.equals("kurzbeschreibung"))
                activeKurzbeschreibung = true;
            else if(rawName.equals("einkaufsliste"))
                einkaufsliste = new ArrayList<Kochrezept.Zutat>();
            else if(rawName.equals("zutat")) {
                activeZutat = true;
                zutat = rezept.new Zutat();
                zutat.setBezeichner(atrbts.getValue("bezeichner"));
                zutat.setMenge(atrbts.getValue("menge"));
            } else if(rawName.equals("rezept"))
                rezeptSchritte = new ArrayList<String>();
            else if(rawName.equals("anweisung"))
                activeAnweisung = true;
        }

        @Override
        public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
            if (rawName.equals("kochrezept"))
                sammlung.add(rezept);
            else if(rawName.equals("titel"))
                activeTitel = false;
            else if(rawName.equals("untertitel"))
                activeUntertitel = false;
            else if(rawName.equals("kurzbeschreibung"))
                activeKurzbeschreibung = false;
            else if(rawName.equals("einkaufsliste"))
                rezept.setEinkaufsliste(einkaufsliste);
            else if(rawName.equals("zutat")) {
                activeZutat = false;
                einkaufsliste.add(zutat);
            } else if(rawName.equals("rezept"))
                rezept.setRezeptSchritte(rezeptSchritte);
            else if(rawName.equals("anweisung")) {
                activeAnweisung = false;
                rezeptSchritte.add(anweisung);
            }
        }

        @Override
        public void characters(char[] chars, int start, int end) throws SAXException {
            String string = new String(chars, start, end);
            if (activeTitel)
                rezept.setTitel(string);
            else if(activeUntertitel)
                rezept.setUntertitel(string);
            else if(activeKurzbeschreibung)
                rezept.setKurzbeschreibung(string);
            else if(activeZutat)
                zutat.setName(string);
            else if(activeAnweisung)
                anweisung = string;
        }

        @Override
        public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            System.out.println("Target: " + target + " and Data: " + data);
        }

        @Override
        public void skippedEntity(String string) throws SAXException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
