package me.leorblx.classicnfsw.core;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlUtils
{
    public static String getFromFile(String file)
    {
//        System.out.println(Paths.get("").toFile().getAbsolutePath());
        
        Path first = Paths.get(String.format("%s.xml", file));
        Path second = Paths.get(String.format("%s", file));

        try {
            if (Files.exists(first))
                return new String(Files.readAllBytes(first), StandardCharsets.UTF_8);
            else if (Files.exists(second))
                return new String(Files.readAllBytes(second), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> String marshal(T obj)
    {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(obj, stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    public static <T> T unmarshal(String xml, Class<T> tClass)
    {
        T objTmp = null;
        
        try {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
            XMLStreamReader xsr = xmlInputFactory.createXMLStreamReader(reader);
            XMLReaderWithoutNamespace xr = new XMLReaderWithoutNamespace(xsr);

//            System.out.println("xml = " + xml);

            objTmp = (T) jaxbUnmarshaller.unmarshal(xr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objTmp;
    }
}
