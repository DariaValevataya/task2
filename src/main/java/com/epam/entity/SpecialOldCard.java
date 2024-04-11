package com.epam.entity;

import java.time.LocalDate;
import java.util.Objects;

public class SpecialOldCard extends BaseOldCard {
  private String valuable;

  public SpecialOldCard(String id, String thema, LocalDate year, String author, String country, String type) {
    super(id, thema, year, author, country, type);
  }

  public String getValuable() {
    return valuable;
  }

  public class Builder extends BaseOldCard.Builder{
    Builder(){};
    public Builder setValuable(String valuable){
      SpecialOldCard.this.valuable=valuable;
      return this;
    }
    public SpecialOldCard build(){
      return SpecialOldCard.this;
    }
  }
  @Override
  public boolean equals(Object o) {
    return super.equals(o) && Objects.equals(valuable, this.valuable);
  }

  @Override
  public String toString() {
    return super.toString() + "valuable" + valuable;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (valuable != null ? valuable.hashCode() : 0);
    return result;
  }
}
