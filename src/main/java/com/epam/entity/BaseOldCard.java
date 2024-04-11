package com.epam.entity;

import java.time.*;
import java.util.Objects;

public class BaseOldCard {
  private String id;
  private String thema;
  private LocalDate year;
  private String author;

  private String country;

  private String type;

  public BaseOldCard(String id, String thema, LocalDate year, String author, String country, String type) {
    this.id = id;
    this.thema = thema;
    this.year = year;
    this.author = author;
    this.country = country;
    this.type = type;
  }
  private BaseOldCard(){}
  public String getId() {
    return id;
  }

  public String getThema() {
    return thema;
  }

  public LocalDate getYear() {
    return year;
  }

  public String getAuthor() {
    return author;
  }

  public String getCountry() {
    return country;
  }

  public String getType() {
    return type;
  }
  public static Builder newBuilder(){
    return new BaseOldCard().new Builder();
  }
  public class Builder{
    Builder(){}
    public Builder setId(String id) {
      BaseOldCard.this.id = id;
      return this;
    }

    public Builder setThema(String thema) {
      BaseOldCard.this.thema = thema;
      return this;
    }

    public Builder setYear(LocalDate year) {
      BaseOldCard.this.year = year;
      return this;
    }

    public Builder setAuthor(String author) {
      BaseOldCard.this.author = author;
      return this;
    }

    public Builder setCountry(String country) {
      BaseOldCard.this.country = country;
      return this;
    }

    public Builder setType(String type) {
      BaseOldCard.this.type = type;
      return this;
    }
    public BaseOldCard build(){
      return BaseOldCard.this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseOldCard that = (BaseOldCard) o;
    return Objects.equals(id, that.id) && Objects.equals(thema, that.thema) && Objects.equals(year, that.year) && Objects.equals(author, that.author) && Objects.equals(country, that.country) && Objects.equals(type, that.type);
  }
  @Override
  public String toString() {
    return "OldCard{" +
            "id='" + id + '\'' +
            ", thema='" + thema + '\'' +
            ", year=" + year +
            ", author='" + author + '\'' +
            ", country='" + country + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (thema != null ? thema.hashCode() : 0);
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}
