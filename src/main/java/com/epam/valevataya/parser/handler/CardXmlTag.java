package com.epam.valevataya.parser.handler;

public enum CardXmlTag {
  OLD_CARD("oldCards"),
  BASE_OLD_CARD("baseOldCard"),
  SPECIAL_OLD_CARD("specialOldCard"),
  ID("id"),
  THEMA("thema"),
  YEAR("year"),
  AUTHOR("author"),
  TYPE("type"),
  COUNTRY("country"),
  VALUABLE("valuable");
  private String tagValue;

  CardXmlTag(String tagValue) {
    this.tagValue = tagValue;
  }

  public String getTagValue() {
    return tagValue;
  }

}
