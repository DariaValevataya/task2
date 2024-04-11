package com.epam.parser;

import com.epam.handler.CardErrorHandler;
import com.epam.handler.FileCardHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser {
  public static void main(String[] args){
    try{
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();
      XMLReader reader = parser.getXMLReader();
      reader.setContentHandler(new FileCardHandler());
      reader.setErrorHandler(new CardErrorHandler());
      reader.parse("src/main/resources/card.xml");

    }
    catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

}
