package com.epam.valevataya.handler;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardHandler extends DefaultHandler {
  private Set<BaseOldCard> baseCards;
  private Set<SpecialOldCard> specialCards;
  private CardXmlTag currentXmlTag;
  public CardHandler() {
    baseCards = new HashSet<BaseOldCard>();
    specialCards = new HashSet<SpecialOldCard>();

  }
  public Set<BaseOldCard> getBaseCards() {
    return baseCards;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialCards;
  }

  /*public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equals("baseOldCard")) {
      String id = attributes.getValue("id");
      String author = attributes.getValue("author");
      String thema=attributes.getValue("thema");
      int year= Integer.parseInt(attributes.getValue("year"));
      BaseOldCard card = BaseOldCard.newBuilder()
              .setId(id)
              .setAuthor(author)
              .setThema(thema)
              .setYear(year)
              .build();
      baseCards.add(card);
    }
  }
   */
}
