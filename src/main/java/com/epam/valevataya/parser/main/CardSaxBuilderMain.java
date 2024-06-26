package com.epam.valevataya.parser.main;

import com.epam.valevataya.parser.CardSaxBuilder;
import com.epam.valevataya.exception.CardException;

public class CardSaxBuilderMain {
  public static void main(String[] args) throws CardException {
    CardSaxBuilder saxBuilder = new CardSaxBuilder();
    saxBuilder.buildSetBaseCards("xml/card.xml");
    saxBuilder.buildSetSpecialCards("xml/card.xml");
    System.out.println(saxBuilder.getSpecialCards());
    System.out.println(saxBuilder.getBaseCards());
  }
}
