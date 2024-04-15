package com.epam.valevataya.validator;

import com.epam.valevataya.exception.CardException;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface CardXmlValidator {
  boolean validateXml(String schemaName, String fileName) throws CardException;
}
