package com.epam.validator;

import org.xml.sax.SAXException;

import java.io.IOException;

public interface CardXmlValidator {
 boolean validateXml() throws IOException, SAXException;
}
