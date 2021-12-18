package com.aderenchuk.brest.service.impl.sax_parsing;

import com.aderenchuk.brest.model.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler  extends DefaultHandler {

    private  Client client = new Client();
      List<Client> clientList = new ArrayList<>();
    private String currentTagName;
    private boolean isClients = false;
    private boolean isClient = false;

    private String firstName;
    private String lastName;
    private  int tour_id;

    public List<Client> getClients() {
        return clientList;
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("End document" + clientList.size());
        System.out.println(clientList);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Start Element" + qName);
        currentTagName = qName;
        if(currentTagName.equals("clients")) {
            isClients = true;
        } else if(currentTagName.equals("client")) {
            isClient = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("End Element" + qName);
            if(qName.equals("clients")) {
                isClients = false;
            } else if (qName.equals("client")) {
                isClient = false;

                Client client = new Client(firstName, lastName, tour_id);
//                System.out.println(client);
                clientList.add(client);
            }

        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        System.out.println("characters " + new String(ch, start, length));

        if(currentTagName == null) {
            return;
        }

        if(isClients && isClient ) {
            if (currentTagName.equals("firstName")) {
                firstName = new String(ch, start, length);
            } else if (currentTagName.equals("lastName")) {
                lastName = new String(ch, start, length);
            } else if (currentTagName.equals("tour_id")) {
                tour_id = Integer.parseInt(new String(ch, start, length));
            }
        }
    }
}
