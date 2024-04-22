package com.epam.valevataya.parser.handler;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CardHandler extends DefaultHandler {
  private Set<BaseOldCard> baseCardsSet;
  private BaseOldCard baseCard;
  private Set<SpecialOldCard> specialCardsSet;
  private SpecialOldCard specialCard;
  private final EnumSet<CardXmlTag> enumCardXmlTags;
  private CardXmlTag cardXmlTag;
  private BaseOldCard.Builder baseBuilder=BaseOldCard.newBuilder();
  private SpecialOldCard.Builder specialBuilder=SpecialOldCard.newBuilder();

  private boolean isBaseCard = true;

  public CardHandler() {
    baseCardsSet = new HashSet<BaseOldCard>();
    specialCardsSet = new HashSet<SpecialOldCard>();
    enumCardXmlTags = EnumSet.range(CardXmlTag.ID, CardXmlTag.VALUABLE);
  }

  public Set<BaseOldCard> getBaseCards() {
    return baseCardsSet;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialCardsSet;
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equals(CardXmlTag.OLD_CARD.getTagValue())) {
      return;
    }
     else if (qName.equals(CardXmlTag.BASE_OLD_CARD.getTagValue())) {
        baseCard = baseBuilder
                .setId(attributes.getValue(CardXmlTag.ID.getTagValue()))
                .setAuthor(attributes.getValue(CardXmlTag.AUTHOR.getTagValue()))
                .setThema(attributes.getValue(CardXmlTag.THEMA.getTagValue()))
                .setYear(Integer.parseInt(attributes.getValue(CardXmlTag.YEAR.getTagValue())))
                .build();
        System.out.println(baseCard.toString());
        isBaseCard = true;
      }
      else if (qName.equals(CardXmlTag.SPECIAL_OLD_CARD.getTagValue())) {
        specialCard = (SpecialOldCard) specialBuilder
                .setId(attributes.getValue(CardXmlTag.ID.getTagValue()))
                .setAuthor(attributes.getValue(CardXmlTag.AUTHOR.getTagValue()))
                .setThema(attributes.getValue(CardXmlTag.THEMA.getTagValue()))
                .setYear(Integer.parseInt(attributes.getValue(CardXmlTag.YEAR.getTagValue())))
                .build();
        isBaseCard = false;
      }
      else {
        CardXmlTag temp = CardXmlTag.valueOf(qName.toUpperCase());
        if (enumCardXmlTags.contains(temp)) {
          cardXmlTag = temp;
        }
      }
  }

  public void endElement(String uri, String localName, String qName) {
    if (qName.equals(CardXmlTag.BASE_OLD_CARD.getTagValue())) {
      baseCardsSet.add(baseCard);
      baseBuilder=BaseOldCard.newBuilder();
    }
    if (qName.equals(CardXmlTag.SPECIAL_OLD_CARD.getTagValue())) {
      specialCardsSet.add(specialCard);
      specialBuilder=SpecialOldCard.newBuilder();
    }
  }

  public void characters(char[] ch, int start, int length) {
    String data = new String(ch, start, length).strip();
    if (cardXmlTag != null) {
      switch (cardXmlTag) {
        case COUNTRY:
          if (isBaseCard) {
            baseCard = baseBuilder.setCountry(data).build();
          } else {
            specialCard = (SpecialOldCard) specialBuilder.setCountry(data).build();
          }
          break;
        case TYPE:
          if (isBaseCard) {
            baseCard = baseBuilder.setType(data).build();
          } else {
            specialCard = (SpecialOldCard) specialBuilder.setType(data).build();
          }
          break;
        case VALUABLE:
          if (!isBaseCard) {
            specialCard = specialBuilder.setValuable(data).build();
          }
          break;
        default:
          throw new EnumConstantNotPresentException(cardXmlTag.getDeclaringClass(), cardXmlTag.name());
      }
    }
    cardXmlTag = null;
  }
}
