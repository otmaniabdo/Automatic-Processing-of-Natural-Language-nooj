package DictoXml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import DictoXml.Balise.*;

@SuppressWarnings({ "unused" })
public class Convert {
		
		public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException{
		
			System.out.println("For Xml To Dic Chose 'x' And For Dic To Xml 'd' :");
			Scanner sc = new Scanner(System.in);
			char choise = sc.nextLine().charAt(0);
			if(choise == 'd'){
				File f = new File("C:\\Users\\Abdo\\workspace\\exp_dic.dic");
				DicToXmlFunction.dictoxml(f);
			}else if(choise == 'x'){
				File f = new File("C:\\Users\\Abdo\\workspace\\DicToXml_v2\\xmlfile.xml");
				XmlToDicFunction.xmltodic(f);
			}
	}

}