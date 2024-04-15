package com.epam.valevataya.SAXbuilder;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;
import com.epam.valevataya.exception.CardException;
import com.epam.valevataya.handler.CardErrorHandler;
import com.epam.valevataya.handler.CardHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class CardSaxBuilder {
  private Set<BaseOldCard> baseCards;
  private Set<SpecialOldCard> specialCards;
  private CardHandler handler = new CardHandler();
  private XMLReader reader;

  public CardSaxBuilder() throws CardException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = factory.newSAXParser();
      reader = saxParser.getXMLReader();
    } catch (ParserConfigurationException | SAXException e) {
      throw new CardException(e);
    }
    reader.setErrorHandler(new CardErrorHandler());
    reader.setContentHandler(handler);
  }

  public Set<BaseOldCard> getBaseCards() {
    return baseCards;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialCards;
  }

  public void buildSetBaseCards(String filename) throws CardException {
    try {
      reader.parse(filename);
    }catch (IOException | SAXException e){
      throw new CardException();
    }
    baseCards=handler.getBaseCards();
  }

  public void buildSetSpecialCards(String filename) throws CardException{
    try {
      reader.parse(filename);
    }catch (IOException | SAXException e){
      throw new CardException();
    }
   // baseCards=handler.getSpecialCards();
  }


}
