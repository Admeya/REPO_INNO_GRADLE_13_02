package Day_8_ClassLoader.datamanagement;

import Day_8_ClassLoader.dicts.StructureObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Ирина on 11.02.2017.
 */
public class SerializationManager {
    static String strType;
    static String strValue;
    static String strName;
    static Object ob;


    public void visit(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i); // текущий нод
            if (!childNode.toString().contains("text:")) {
                process(childNode); // обработка
            }
            visit(childNode); // рекурсия
        }
    }

    public void process(Node node) {
        String type;
        String val;

        System.out.println(node.getNodeName());
        NamedNodeMap attr = node.getAttributes();
        for (int i = 0; i < attr.getLength(); i++) {
            type = attr.item(i).getNodeName();
            val = attr.item(i).getNodeValue();
            System.out.println(" " + type + " = '" + val + "'");
            if (i == 0) {
                try {
                    if (type.equals(StructureObject.ATTR_TYPE)) {
                        Class people = Class.forName(val);
                        ob = people.newInstance();
                        System.out.println(ob);
                    }
                    if (type.equals(StructureObject.ATTR_ID)) {
                        identification(type, val);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } else {
                identification(type, val);
            }
            settingFields(ob);
        }

        if (node instanceof Element) {
            Element e = (Element) node; // работаем как с элементом (у него есть атрибуты и схема)
        }
    }

    public void identification(String fieldName, String fieldValue) {
        if (fieldName.equals(StructureObject.ATTR_ID)) {
            strName = fieldValue;
        } else {
            if (fieldName.equals(StructureObject.ATTR_TYPE)) {
                strType = fieldValue;
            } else if (fieldName.equals(StructureObject.ATTR_VALUE)) {
                strValue = fieldValue;
            }
        }
    }

    public void settingFields(Object ob) {
        if (strValue != null && strType != null && strName != null) {
            Field f = null;
            try {
                f = ob.getClass().getDeclaredField(strName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            f.setAccessible(true);
            try {
                switch (strType) {
                    case "Double":
                        f.set(ob, (Double) Double.valueOf(strValue));
                        break;
                    case "Integer":
                        f.set(ob, (Integer) Integer.valueOf(strValue));
                        break;
                    case "String":
                        f.set(ob, strValue);
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(ob);

            strValue = null;
            strType = null;
            strName = null;
        }
    }

    public void serialization(String xmlPath, Object obj) {
        System.out.println("Serialization start......");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        DOMImplementation impl = builder.getDOMImplementation();

        Class people = obj.getClass();
        Document doc = impl.createDocument(null, null, null); // doctype
        Element e1 = doc.createElement(StructureObject.ELEM_OBJECT);
        e1.setAttribute(StructureObject.ATTR_TYPE, people.getName());
        doc.appendChild(e1);

        Field[] fields = people.getDeclaredFields();
        for (Field field : fields) {
            Element e2 = doc.createElement(StructureObject.ELEM_FIELD);
            field.setAccessible(true);
            e2.setAttribute(StructureObject.ATTR_TYPE, field.getType().getSimpleName());
            e2.setAttribute(StructureObject.ATTR_ID, field.getName());
            try {
                e2.setAttribute(StructureObject.ATTR_VALUE, field.get(obj).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            e1.appendChild(e2);
        }
        writeObjectToXML(doc, xmlPath);
        System.out.println("Serialization successful!!!\n");
    }

    void writeObjectToXML(Document doc, String xmlPath) {
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.out);
        TransformerFactory transFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

            transformer.transform(source, new StreamResult(new FileOutputStream(xmlPath)));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deserialization(String xmlPath) {
        System.out.println("Deserialization start..........");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //создали фабрику строителей
        DocumentBuilder parser = null; // создали конкретного строителя документа
        try {
            parser = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null; // строитель построил документ
        try {
            doc = parser.parse(new File(xmlPath));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Document - тоже является нодом, и импленментирует методы
        visit(doc);
        System.out.println("Deserialisation successful!!!");
    }
}


