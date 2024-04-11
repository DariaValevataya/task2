package com.epam.validator.impl;

import com.epam.handler.CardErrorHandler;
import com.epam.validator.CardXmlValidator;
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
  static final Logger LOGGER= LogManager.getLogger();
  private static final String SCHEMA_NAME = "card.xsd";
  private static final String FILE_NAME = "card.xml";
  private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;


  @Override
  public boolean validateXml() throws IOException, SAXException {
    SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
    File schemaLocation = new File(SCHEMA_NAME);
    try{
    Schema schema = factory.newSchema(schemaLocation);
    Validator validator = schema.newValidator();
    validator.setErrorHandler(new CardErrorHandler());

    Source source = new StreamSource(FILE_NAME);
    validator.validate(source);
    }catch (SAXException e){
      LOGGER.error(e.getMessage());
      throw  new SAXException(e);
    }
    return true;
  }
}
