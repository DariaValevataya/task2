package com.epam.valevataya.exception;

public class CardException extends Exception {
  public CardException() {
    super();
  }

  public CardException(String message) {
    super(message);
  }

  public CardException(String message, Throwable cause) {
    super(message, cause);
  }

  public CardException(Throwable cause) {
    super(cause);
  }
}
