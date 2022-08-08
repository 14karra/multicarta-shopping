package ru.multicarta.shopping.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.xml.transform.StringSource;
import ru.multicarta.shopping.service.JaxbService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class JaxbServiceImpl implements JaxbService {

    private final ConcurrentMap<Class<?>, JAXBContext> contexts;

    public JaxbServiceImpl() {
        this.contexts = new ConcurrentHashMap<>();
    }

    private JAXBContext getJaxbContext(Class<?> clazz) throws JAXBException {
        JAXBContext context = contexts.get(clazz);
        if (context == null) {
            context = JAXBContext.newInstance(clazz);
            contexts.put(clazz, context);
        }
        return context;
    }

    @Override
    public <T> T parse(String xmlMessage, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = getJaxbContext(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new StringSource(xmlMessage), clazz).getValue();
    }

    @Override
    public <T> String stringify(T object, Class<?> clazz) throws JAXBException {
        JAXBContext jaxbContext = getJaxbContext(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        Writer writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }
}