package com.epam.valevataya.validator.impl;

import com.epam.valevataya.exception.CardException;
import com.epam.valevataya.parser.handler.CardErrorHandler;
import com.epam.valevataya.validator.CardXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class CardXmlValidatorImpl implements CardXmlValidator {
  static final Logger LOGGER = LogManager.getLogger();

  @Override
  public boolean validateXml(String schemaName, String fileName) throws CardException {
    boolean flag = true;
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
    File schemaLocation = new File(schemaName);
    try {
      Schema schema = factory.newSchema(schemaLocation);
      Validator validator = schema.newValidator();
      validator.setErrorHandler(new CardErrorHandler());
      Source source = new StreamSource(fileName);
      validator.validate(source);
    } catch (SAXException | IOException e) {
      LOGGER.error(e.getMessage());
      flag = false;
    }
    return flag;
  }
}
