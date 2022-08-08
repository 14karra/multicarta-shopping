package ru.multicarta.shopping.service.serviceImpl;

import iso.std.ru.multicarta.tech.xsd.purchaserequest.PurchaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.exception.InvalidXmlException;
import ru.multicarta.shopping.service.XmlService;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Service
public class XmlServiceImpl implements XmlService {

    private final Schema itemsSchema;
    private final Schema purchaseRequestSchema;
    private Map<Class, Consumer<String>> validationMap = new HashMap<>();

    public XmlServiceImpl() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        itemsSchema = factory.newSchema(getItemsXsdSources());
        purchaseRequestSchema = factory.newSchema(getPurchaseRequestXsdSources());
        validationMap.put(PurchaseRequest.class, this::validatePurchaseRequest);
    }

    @Override
    public void performValidation(Class xmlClass, String xmlMessage) {
        var specificValidation = validationMap.get(xmlClass);
        if (Objects.isNull(specificValidation)) {
            throw new IllegalStateException("Validator for such class not present");
        }
        specificValidation.accept(xmlMessage);
    }

    private void validatePurchaseRequest(final String xmlMessage) throws InvalidXmlException {
        try {
            log.info("Validating 'purchase request' business message...");
            validateXml(xmlMessage, purchaseRequestSchema);
        } catch (InvalidXmlException ex) {
            log.warn("Provided 'purchase request' is invalid");
            throw new ApiException("107", "Provided xml did not pass validation");
        }
    }

    private void validateXml(final String xmlMessage, Schema schema) throws InvalidXmlException {
        log.info("Validating provided xml:\n{}", xmlMessage);
        try {
            schema.newValidator().validate(new StreamSource(new StringReader(xmlMessage)));
        } catch (SAXException ex) {
            log.warn("Provided xml is invalid");
            throw new InvalidXmlException();
        } catch (IOException ex) {
            log.warn("Internal service error while validating a xml business message. Exception message: {}", ex.getMessage());
            throw new IllegalStateException();
        }
        log.info("Provided xml is valid");
    }

    private URL getItemsXsdSources() {
        return this.getClass().getClassLoader().getResource("/xsd/Items.xsd");
    }

    private URL getPurchaseRequestXsdSources() {
        return this.getClass().getClassLoader().getResource("/xsd/PurchaseRequest.xsd");
    }
}
