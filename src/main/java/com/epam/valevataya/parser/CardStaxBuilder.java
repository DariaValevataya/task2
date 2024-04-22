package com.epam.valevataya.parser;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;
import com.epam.valevataya.parser.handler.CardXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CardStaxBuilder {
  static final Logger LOGGER = LogManager.getLogger();
  private Set<BaseOldCard> baseOldCardSet;
  private Set<SpecialOldCard> specialOldCardSet;
  private BaseOldCard.Builder baseBuilder = BaseOldCard.newBuilder();
  private SpecialOldCard.Builder specialBuilder = SpecialOldCard.newBuilder();

  private XMLInputFactory inputFactory;

  public CardStaxBuilder() {
    inputFactory = XMLInputFactory.newInstance();
    baseOldCardSet = new HashSet<BaseOldCard>();
    specialOldCardSet = new HashSet<SpecialOldCard>();
  }

  public Set<BaseOldCard> getBaseCards() {
    return baseOldCardSet;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialOldCardSet;
  }

  public void buildSetBaseCards(String filename) {
    XMLStreamReader reader;
    String name;
    try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
      reader = inputFactory.createXMLStreamReader(inputStream);
      while (reader.hasNext()) {
        int type = reader.next();
        if (type == XMLStreamConstants.START_ELEMENT) {
          name = reader.getLocalName();
          if (name.equals(CardXmlTag.BASE_OLD_CARD.getTagValue())) {
            BaseOldCard baseCard = buildBaseCard(reader);
            baseOldCardSet.add(baseCard);
          }
        }
      }
    } catch (XMLStreamException | IOException e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void buildSetSpecialCards(String filename) {
    XMLStreamReader reader;
    String name;
    try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
      reader = inputFactory.createXMLStreamReader(inputStream);
      while (reader.hasNext()) {
        int type = reader.next();
        if (type == XMLStreamConstants.START_ELEMENT) {
          name = reader.getLocalName();
          if (name.equals(CardXmlTag.SPECIAL_OLD_CARD.getTagValue())) {
            SpecialOldCard specialCard = buildSpecialCard(reader);
            specialOldCardSet.add(specialCard);
          }
        }
      }
    } catch (XMLStreamException | IOException e) {
      LOGGER.error(e.getMessage());
    }
  }

  public BaseOldCard buildBaseCard(XMLStreamReader reader) throws XMLStreamException {
    BaseOldCard card = baseBuilder
            .setId(reader.getAttributeValue(null, CardXmlTag.ID.getTagValue()))
            .setThema(reader.getAttributeValue(null, CardXmlTag.THEMA.getTagValue()))
            .setAuthor(reader.getAttributeValue(null, CardXmlTag.AUTHOR.getTagValue()))
            .setYear(Integer.parseInt(reader.getAttributeValue(null, CardXmlTag.YEAR.getTagValue())))
            .build();
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (CardXmlTag.valueOf(name.toUpperCase())) {
            case COUNTRY -> baseBuilder.setCountry(getXMLText(reader));
            case TYPE -> baseBuilder.setType(getXMLText(reader));
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (CardXmlTag.valueOf(name.toUpperCase().replace("BASEOLDCARD", "BASE_OLD_CARD")).equals(CardXmlTag.BASE_OLD_CARD)) {
            baseBuilder=BaseOldCard.newBuilder();
            return card;
          }
      }
    }
    return card;
  }

  public SpecialOldCard buildSpecialCard(XMLStreamReader reader) throws XMLStreamException {
    SpecialOldCard card = (SpecialOldCard) specialBuilder
            .setId(reader.getAttributeValue(null, CardXmlTag.ID.getTagValue()))
            .setThema(reader.getAttributeValue(null, CardXmlTag.THEMA.getTagValue()))
            .setAuthor(reader.getAttributeValue(null, CardXmlTag.AUTHOR.getTagValue()))
            .setYear(Integer.parseInt(reader.getAttributeValue(null, CardXmlTag.YEAR.getTagValue())))
            .build();
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (CardXmlTag.valueOf(name.toUpperCase())) {
            case COUNTRY -> specialBuilder.setCountry(getXMLText(reader));
            case TYPE -> specialBuilder.setType(getXMLText(reader));
            case VALUABLE -> specialBuilder.setValuable(getXMLText(reader));
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (CardXmlTag.valueOf(name.toUpperCase().replace("SPECIALOLDCARD", "SPECIAL_OLD_CARD")).equals(CardXmlTag.SPECIAL_OLD_CARD)) {
            specialBuilder=SpecialOldCard.newBuilder();
            return card;
          }
      }
    }
    return card;
  }

  private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
    String text = null;
    if (reader.hasNext()) {
      reader.next();
      text = reader.getText();
    }
    return text;
  }
}
