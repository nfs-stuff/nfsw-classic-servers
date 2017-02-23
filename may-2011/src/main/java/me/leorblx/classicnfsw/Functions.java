package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.HttpState;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Objects;

public class Functions
{
    public String readCarIndex() throws ParserConfigurationException, SAXException, IOException
    {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("www/nfsw/Engine.svc/personas/" + HttpState.getPersonaId() + "/carslots.xml")
                .getElementsByTagName("DefaultOwnedCarIndex")
                .item(0)
                .getTextContent();
    }

    public String writeCar(String carData)
    {
        if (!Objects.equals("", carData)) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("www/nfsw/Engine.svc/personas/" + HttpState.getPersonaId() + "/defaultcar.xml").getAbsoluteFile()));
                bw.write(carData);
                bw.close();

                return carData.replace("<CustomCarTrans>", "<UpdatedCar>").replace("</CustomCarTrans>", "</UpdatedCar>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public void writeXML(Document doc, String location)
    {
        try
        {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(location).getAbsoluteFile()));
            bw.write(sw.toString());
            bw.close();
        }
        catch (IOException | TransformerException | TransformerFactoryConfigurationError e)
        {
            e.printStackTrace();
        }
    }
}
