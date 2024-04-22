package com.epam.valevataya.parser;

import java.util.HashSet;
import java.util.Set;

import com.epam.valevataya.entity.BaseOldCard;
import com.epam.valevataya.entity.SpecialOldCard;

import com.epam.valevataya.exception.CardException;
import com.epam.valevataya.parser.handler.CardXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class CardDomBuilder {
  static final Logger LOGGER = LogManager.getLogger();
  private Set<BaseOldCard> baseCards;
  private Set<SpecialOldCard> specialCards;
  private DocumentBuilder builder;


  public CardDomBuilder() throws CardException {
    baseCards = new HashSet<BaseOldCard>();
    specialCards = new HashSet<SpecialOldCard>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      LOGGER.error(e.getMessage());
      throw new CardException(e);
    }
  }

  public Set<BaseOldCard> getBaseCards() {
    return baseCards;
  }

  public Set<SpecialOldCard> getSpecialCards() {
    return specialCards;
  }

  public void buildSetBaseCards(String filename) throws CardException {
    try {
      Document document = builder.parse(new File(filename));
      Element root = document.getDocumentElement();
      NodeList baseCardsList = root.getElementsByTagName(CardXmlTag.BASE_OLD_CARD.getTagValue());
      for (int i = 0; i < baseCardsList.getLength(); i++) {
        Element baseCardElement = (Element) baseCardsList.item(i);
        BaseOldCard baseCard = buildBaseCard(baseCardElement);
        baseCards.add(baseCard);
      }
    } catch (SAXException | IOException e) {
      LOGGER.error(e.getMessage());
      throw new CardException(e);
    }
  }

  public void buildSetSpecialCards(String filename) throws CardException {
    try {
      Document document = builder.parse(new File(filename));
      Element root = document.getDocumentElement();
      NodeList specialCardsList = root.getElementsByTagName(CardXmlTag.SPECIAL_OLD_CARD.getTagValue());
      for (int i = 0; i < specialCardsList.getLength(); i++) {
        Element specialCardElement = (Element) specialCardsList.item(i);
        SpecialOldCard specialCard = buildSpecialCard(specialCardElement);
        specialCards.add(specialCard);
      }
    } catch (SAXException | IOException e) {
      LOGGER.error(e.getMessage());
      throw new CardException(e);
    }
  }

  public BaseOldCard buildBaseCard(Element baseCardElement) {
    BaseOldCard card = BaseOldCard.newBuilder()
            .setId(baseCardElement.getAttribute(CardXmlTag.ID.getTagValue()))
            .setThema(baseCardElement.getAttribute(CardXmlTag.TYPE.getTagValue()))
            .setYear(Integer.parseInt(baseCardElement.getAttribute(CardXmlTag.YEAR.getTagValue())))
            .setAuthor(baseCardElement.getAttribute(CardXmlTag.AUTHOR.getTagValue()))
            .setType(getElementTextContent(baseCardElement, CardXmlTag.TYPE.getTagValue()))
            .setCountry(getElementTextContent(baseCardElement, CardXmlTag.COUNTRY.getTagValue()))
            .build();
    return card;
  }

  public SpecialOldCard buildSpecialCard(Element specialCardElement) {
    SpecialOldCard card = (SpecialOldCard) SpecialOldCard.newBuilder()
            .setValuable(getElementTextContent(specialCardElement, CardXmlTag.VALUABLE.getTagValue()))
            .setId(specialCardElement.getAttribute(CardXmlTag.ID.getTagValue()))
            .setThema(specialCardElement.getAttribute(CardXmlTag.THEMA.getTagValue()))
            .setYear(Integer.parseInt(specialCardElement.getAttribute(CardXmlTag.YEAR.getTagValue())))
            .setAuthor(specialCardElement.getAttribute(CardXmlTag.AUTHOR.getTagValue()))
            .setType(getElementTextContent(specialCardElement, CardXmlTag.TYPE.getTagValue()))
            .setCountry(getElementTextContent(specialCardElement, CardXmlTag.COUNTRY.getTagValue()))
            .build();
    return card;
  }

  private static String getElementTextContent(Element element, String elementName) {
    NodeList nodeList = element.getElementsByTagName(elementName);
    Node node = nodeList.item(0);
    String text = null;
    if (node != null) {
      text = node.getTextContent();
    }
    return text;
  }
}
