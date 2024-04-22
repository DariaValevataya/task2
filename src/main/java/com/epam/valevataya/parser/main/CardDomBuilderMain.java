package com.epam.valevataya.parser.main;

import com.epam.valevataya.parser.CardDomBuilder;
import com.epam.valevataya.exception.CardException;

public class CardDomBuilderMain {
  public static void main(String[] args) throws CardException {
    CardDomBuilder domBuilder = new CardDomBuilder();
    domBuilder.buildSetBaseCards("xml/card.xml");
    domBuilder.buildSetSpecialCards("xml/card.xml");
    System.out.println(domBuilder.getSpecialCards());
    System.out.println(domBuilder.getBaseCards());
  }
}
