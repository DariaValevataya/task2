package com.epam.valevataya.parser.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.logging.log4j.Logger;

public class CardErrorHandler implements ErrorHandler {
  static final Logger LOGGER = LogManager.getLogger();

  @Override
  public void warning(SAXParseException exception) throws SAXException {
    LOGGER.log(Level.WARN, getLineColumnNumber(exception) + "-" + exception.getMessage());
  }

  @Override
  public void error(SAXParseException exception) throws SAXException {
    LOGGER.log(Level.ERROR, getLineColumnNumber(exception) + "-" + exception.getMessage());
  }

  @Override
  public void fatalError(SAXParseException exception) throws SAXException {
    LOGGER.log(Level.FATAL, getLineColumnNumber(exception) + "-" + exception.getMessage());
  }

  private String getLineColumnNumber(SAXParseException exception) {
    return exception.getLineNumber() + " : " + exception.getColumnNumber();
  }

}
