package com.epam.valevataya.parser.main;

import com.epam.valevataya.parser.CardStaxBuilder;
import com.epam.valevataya.exception.CardException;

public class CardStaxBuilderMain {
  public static void main(String[] args) throws CardException {
    CardStaxBuilder staxBuilder = new CardStaxBuilder();
    staxBuilder.buildSetBaseCards("xml/card.xml");
    staxBuilder.buildSetSpecialCards("xml/card.xml");
    System.out.println(staxBuilder.getSpecialCards());
    System.out.println(staxBuilder.getBaseCards());
  }
}
