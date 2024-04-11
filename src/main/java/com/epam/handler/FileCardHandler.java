package com.epam.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class FileCardHandler extends DefaultHandler {
  static final Logger LOGGER = LogManager.getLogger();

  public void startElement(String nameSpaceURI, String localName, String qName, Attributes attrs) {
    String tagData = qName + " ";
    for (int i = 0; i < attrs.getLength(); i++) {
      tagData += " " + attrs.getQName(i) + "=" + attrs.getValue(i);
    }
    LOGGER.info(tagData);
  }

  public void characters(char ch[], int start, int length) throws SAXException {
    LOGGER.info(new String(ch, start, length));
  }

  public void endElement(String nameSpaceURI, String localName, String qName) {
    LOGGER.info(" "+ qName);
  }
  public void endDocument(){
    LOGGER.info("/Parsing ended");
  }
}
