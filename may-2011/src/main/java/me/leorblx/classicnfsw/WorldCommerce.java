package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.CarSlotInfoTrans;
import me.leorblx.classicnfsw.jaxb.CommerceSessionTransType;
import me.leorblx.classicnfsw.jaxb.CustomCarType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class WorldCommerce
{
    private static Functions fx = new Functions();

    public CustomCarType save(String commerceSession)
    {
        try {
            int carId = Integer.parseInt(fx.readCarIndex());
            final String pathName = "www/nfsw/Engine.svc/personas/" + HttpState.getPersonaId() + "/carslots.xml";
            Path path = Paths.get(pathName);
            CarSlotInfoTrans carSlotInfoTrans = XmlUtils.unmarshal(XmlUtils.getFromFile(pathName), CarSlotInfoTrans.class);
            CustomCarType current = carSlotInfoTrans.getCarsOwnedByPersonaList().getCustomCarList().get(carId);
            CommerceSessionTransType session = XmlUtils.unmarshal(commerceSession, CommerceSessionTransType.class);
            
            current.setPaints(session.getUpdatedCar().getPaints());
            current.setVisualParts(session.getUpdatedCar().getVisualParts());
            current.setPerformanceParts(session.getUpdatedCar().getPerformanceParts());
            current.setVinyls(session.getUpdatedCar().getVinyls());
            
            carSlotInfoTrans.getCarsOwnedByPersonaList().getCustomCarList().set(carId, current);

            Files.write(path, XmlUtils.marshal(carSlotInfoTrans).getBytes(StandardCharsets.UTF_8));
            
            return current;
            
//            Integer carId = Integer.parseInt(fx.readCarIndex());
//            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc2 = docBuilder.parse(new InputSource(new StringReader(commerceSession)));
//            Document doc = docBuilder.parse("www/nfsw/Engine.svc/personas/" + HttpState.getPersonaId() + "/carslots.xml");
//
//            Node Paints = doc.importNode(doc2.getElementsByTagName("Paints").item(0), true);
//            Node PerformanceParts = doc.importNode(doc2.getElementsByTagName("PerformanceParts").item(0), true);
//            Node Vinyls = doc.importNode(doc2.getElementsByTagName("Vinyls").item(0), true);
//            Node VisualParts = doc.importNode(doc2.getElementsByTagName("VisualParts").item(0), true);
//            Node oldPaints = doc.getElementsByTagName("Paints").item(carId);
//            Node oldPerformanceParts = doc.getElementsByTagName("PerformanceParts").item(carId);
//            Node oldVinyls = doc.getElementsByTagName("Vinyls").item(carId);
//            Node oldVisualParts = doc.getElementsByTagName("VisualParts").item(carId);
//            doc.getElementsByTagName("CustomCarTrans").item(carId).replaceChild(Paints, oldPaints);
//            doc.getElementsByTagName("CustomCarTrans").item(carId).replaceChild(PerformanceParts, oldPerformanceParts);
//            doc.getElementsByTagName("CustomCarTrans").item(carId).replaceChild(Vinyls, oldVinyls);
//            doc.getElementsByTagName("CustomCarTrans").item(carId).replaceChild(VisualParts, oldVisualParts);
//            Node customCar = doc.getElementsByTagName("CustomCarTrans").item(carId);
//            DOMImplementationLS lsImpl = (DOMImplementationLS) customCar.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
//            LSSerializer serializer = lsImpl.createLSSerializer();
//            serializer.getDomConfig().setParameter("xml-declaration", Boolean.valueOf(false));
//            String StringOwnedCar = serializer.writeToString(customCar);
//            fx.writeCar(StringOwnedCar);
//            fx.WriteXML(doc, "../nfsw/Engine.svc/personas/" + Functions.personaId + "/carslots.xml");
//
//            this.fx.log("|| Commerce data finalized. [carIndex = " + carId + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
