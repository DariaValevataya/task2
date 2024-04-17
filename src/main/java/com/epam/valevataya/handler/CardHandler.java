package com.epam.valevataya.handler;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;
import com.epam.valevataya.exception.CardException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardHandler extends DefaultHandler {
  private Set<BaseOldCard> baseCardsSet;
  private BaseOldCard baseCard;
  private Set<SpecialOldCard> specialCardsSet;
  private SpecialOldCard specialCard;
  private CardXmlTag cardXmlTag;

  public CardHandler() {
    baseCardsSet = new HashSet<BaseOldCard>();
    specialCardsSet = new HashSet<SpecialOldCard>();
  }

  public Set<BaseOldCard> getBaseCards() {
    return baseCardsSet;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialCardsSet;
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equals(CardXmlTag.BASE_OLD_CARD.getTagValue())) {
      baseCard = BaseOldCard.newBuilder()
              .setId(attributes.getValue(CardXmlTag.ID.getTagValue()))
              .setAuthor(attributes.getValue(CardXmlTag.AUTHOR.getTagValue()))
              .setThema(attributes.getValue(CardXmlTag.THEMA.getTagValue()))
              .setYear(Integer.parseInt(attributes.getValue(CardXmlTag.YEAR.getTagValue())))
              .build();
    }
    if (qName.equals(CardXmlTag.SPECIAL_OLD_CARD.getTagValue())) {
      specialCard = (SpecialOldCard) SpecialOldCard.newBuilder()
              .setId(attributes.getValue(CardXmlTag.ID.getTagValue()))
              .setAuthor(attributes.getValue(CardXmlTag.AUTHOR.getTagValue()))
              .setThema(attributes.getValue(CardXmlTag.THEMA.getTagValue()))
              .setYear(Integer.parseInt(attributes.getValue(CardXmlTag.YEAR.getTagValue())))
              .build();
    }
  }

  public void endElement(String uri, String localName, String qName) {
    if (qName.equals(CardXmlTag.BASE_OLD_CARD.getTagValue())) {
      baseCardsSet.add(baseCard);
    }
    if (qName.equals(CardXmlTag.SPECIAL_OLD_CARD.getTagValue())) {
      specialCardsSet.add(specialCard);
    }
  }

  public void characters(char[] ch, int start, int length) {
    String data = new String(ch, start, length).strip();
    if (cardXmlTag != null) {
      switch (cardXmlTag) {
        case COUNTRY -> baseCard = BaseOldCard.newBuilder().setCountry(data).build();
        case TYPE -> baseCard = BaseOldCard.newBuilder().setType(data).build();
        case VALUABLE -> specialCard = SpecialOldCard.newBuilder().setValuable(data).build();
        default -> throw new EnumConstantNotPresentException(cardXmlTag.getDeclaringClass(), cardXmlTag.name());
      }
    }
    cardXmlTag = null;
  }

}
