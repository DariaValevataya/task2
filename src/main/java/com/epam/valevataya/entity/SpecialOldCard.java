package com.epam.valevataya.entity;

import java.time.LocalDate;
import java.util.Objects;

public class SpecialOldCard extends BaseOldCard {
  private String valuable;

  private SpecialOldCard() {
    super();
  }

  public String getValuable() {
    return valuable;
  }

  public static SpecialOldCard.Builder newBuilder() {
    return new SpecialOldCard().new Builder();
  }

  public class Builder extends BaseOldCard.Builder {
    Builder() {
    }

    public Builder setValuable(String valuable) {
      SpecialOldCard.this.valuable = valuable;
      return this;
    }

    public SpecialOldCard build() {
      return SpecialOldCard.this;
    }
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o) && Objects.equals(valuable, this.valuable);
  }

  @Override
  public String toString() {
    return super.toString().replace("BaseCard", "SpecialCard").replace("}", ", valuable='" + valuable + "'}");
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (valuable != null ? valuable.hashCode() : 0);
    return result;
  }
}
