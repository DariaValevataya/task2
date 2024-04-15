package com.epam.valevataya.SAXbuilder;

public class CardSaxBuilderMain {
  public static void main(String[] args){
    CardSaxBuilder saxBuilder = new CardSaxBuilder();
    saxBuilder.buildSetBaseCards("xml/card.xml");
    saxBuilder.buildSetSpecialCards("xml/card.xml");
    System.out.println(saxBuilder.getSpecialCards());
    System.out.println(saxBuilder.getBaseCards());
  }

}
