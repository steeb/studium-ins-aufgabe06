/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.ins.aufgabe06;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author steeb
 */
public class KochbuchWriter {

    private XMLSerializer out;
    Document xmldoc;
    Element e;
    Node n;

    public KochbuchWriter(String file) throws FileNotFoundException, UnsupportedEncodingException {
        xmldoc= new DocumentImpl();
        FileOutputStream fos = new FileOutputStream(file);
        OutputFormat of = new OutputFormat("XML","UTF-8",true);
        out = new XMLSerializer(fos,of);
    }

    public void write(List<Kochrezept> sammlung) throws IOException{
        System.out.println("start writing ...");
        Element root = xmldoc.createElement("sammlung");
        for (Kochrezept r : sammlung) {
            root.appendChild(r.getAsXMLElement(xmldoc));
        }
        xmldoc.appendChild(root);
        out.asDOMSerializer();
        out.serialize( xmldoc.getDocumentElement() );
        System.out.println("... end writing");
    }
}
