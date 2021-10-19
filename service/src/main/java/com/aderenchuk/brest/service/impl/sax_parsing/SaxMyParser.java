package com.aderenchuk.brest.service.impl.sax_parsing;

import com.aderenchuk.brest.model.Client;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SaxMyParser {

    public List<Client> parseClient() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            System.out.println("Open SAX parser error " + e.toString());
            return null;
        }
        File file = new File("/home/artem/IdeaProjects/aderenchuk-travel.agency/clientDb.xml");
        try {
            parser. parse(file, handler);
        } catch (FileNotFoundException e) {
            System.out.println(" File not found");
        } catch (SAXException e) {
            System.out.println("Sax parsing error " + e.toString());
            return null;
        } catch (IOException e) {
            System.out.println("IO parsing error " + e.toString());
            return null;
        }
        return handler.getClients();
    }

}
