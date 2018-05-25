package DictoXml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToDicFunction {
	public static void xmltodic(File filexml)throws IOException, ParserConfigurationException, TransformerException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(filexml);
		Element racine = document.getDocumentElement();
		
		String FileName;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Donner le Nom de Fichie Dic :");
			FileName = sc.nextLine();
		}while(FileName.isEmpty());
		
		File filedic = new File("C:\\Users\\Abdo\\workspace\\DicToXml_v2\\"+FileName+".dic");
		
		if(!filedic.exists()){
			filedic.createNewFile();
		}
		
		FileWriter fl = new FileWriter(filedic);
		BufferedWriter bf = new BufferedWriter(fl);
		
		NodeList nodelist = racine.getChildNodes();
		final int nombre = nodelist.getLength();
		for (int i=0; i<nombre; i++) {
			Node node = nodelist.item(i);
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
					if(node.getNodeName().equals("GRAMMARS")){
							NodeList Grammars = node.getChildNodes();
								for(int j=0;j<Grammars.getLength();j++){
									if(Grammars.item(j).getNodeName().equals("GRAMMAR")){
										Element grammarText = (Element) Grammars.item(j);
										bf.write("#use "+grammarText.getTextContent());
										bf.newLine();
									}
								}
					}else if(node.getNodeName().equals("ENTRIES")){
						NodeList Entries = node.getChildNodes();
						for (int j=0;j<Entries.getLength();j++){
							if(Entries.item(j).getNodeType() == Node.ELEMENT_NODE || Entries.item(j).getNodeType() == Node.COMMENT_NODE){
								if(Entries.item(j).getNodeType() == Node.COMMENT_NODE){
									bf.write("# "+Entries.item(j).getNodeValue());
									bf.newLine();
								}else {
									
								//VERB
									if(Entries.item(j).getNodeName().equals("VERB")){ 
										Element verb = (Element) Entries.item(j);
										//Element lemma =  (Element) verb.getFirstChild().getNextSibling();
										NamedNodeMap attlist = verb.getAttributes();
										if(!verb.getAttribute("Root").isEmpty()){
											//System.out.println(verb.getAttribute("Root"));
											bf.write(verb.getAttribute("Root")+","+verb.getAttribute("LEMMA")+",V");
										}else{
											//System.out.println("empty");
											bf.write(verb.getAttribute("LEMMA")+",V");
										}
										
										for(int k=(attlist.getLength()-1);k>=0;k--){
											Attr attr = (Attr) attlist.item(k);
											String attrName = attr.getNodeName();
											String attrValue = attr.getNodeValue();
											if(attrName != "LEMMA")
												bf.write("+"+attrName+"="+attrValue);
											
											
										}
										bf.newLine();
										
									// NOUN
									}else if(Entries.item(j).getNodeName().equals("NOUN")){
										Element noun = (Element) Entries.item(j);
										//Element lemma =  (Element) noun.getFirstChild().getNextSibling();
										NamedNodeMap attlist = noun.getAttributes();
										if(!noun.getAttribute("Root").isEmpty()){
											bf.write(noun.getAttribute("Root")+","+noun.getAttribute("LEMMA")+",N");
										}else{
											bf.write(noun.getAttribute("LEMMA")+",N");
										}
										if(!noun.getAttribute("Derivation").isEmpty()){
											bf.write("+"+noun.getAttribute("Derivation"));
										}
										for(int k=(attlist.getLength()-1);k>=0;k--){
											Attr attr = (Attr) attlist.item(k);
											String attrName = attr.getNodeName();
											String attrValue = attr.getNodeValue();
											if(attrName != "LEMMA" && attrName != "Derivation")
												bf.write("+"+attrName+"="+attrValue);
										}
										bf.newLine();
									}else if(Entries.item(j).getNodeName().equals("DETERMINER")){
										Element det = (Element) Entries.item(j);
									//	Element lemma =  (Element) det.getFirstChild().getNextSibling();
										NamedNodeMap attlist = det.getAttributes();
										
											bf.write(det.getAttribute("LEMMA")+",DET");

										for(int k=(attlist.getLength()-1);k>=0;k--){
											Attr attr = (Attr) attlist.item(k);
											String attrName = attr.getNodeName();
											String attrValue = attr.getNodeValue();
											if(attrName != "LEMMA")
												bf.write("+"+attrName+"="+attrValue);
										}
										bf.newLine();
									}else if(Entries.item(j).getNodeName().equals("PART")){
										Element prep = (Element) Entries.item(j);
									//	Element lemma =  (Element) prep.getFirstChild().getNextSibling();
										NamedNodeMap attlist = prep.getAttributes();
										bf.write(prep.getAttribute("LEMMA")+",PART");
										for(int k=(attlist.getLength()-1);k>=0;k--){
											Attr attr = (Attr) attlist.item(k);
											String attrName = attr.getNodeName();
											String attrValue = attr.getNodeValue();
											if(attrName != "LEMMA")
												bf.write("+"+attrName+"="+attrValue);
										}
										bf.newLine();
									}else if(Entries.item(j).getNodeName().equals("ADVERB")){
										Element adv = (Element) Entries.item(j);
									//	Element lemma =  (Element) adv.getFirstChild().getNextSibling();
										NamedNodeMap attlist = adv.getAttributes();
										bf.write(adv.getAttribute("LEMMA")+",ADV");
										for(int k=(attlist.getLength()-1);k>=0;k--){
											Attr attr = (Attr) attlist.item(k);
											String attrName = attr.getNodeName();
											String attrValue = attr.getNodeValue();
											if(attrName != "LEMMA")
												bf.write("+"+attrName+"="+attrValue);
										}
										bf.newLine();
									}
								}
								
							}
							
						}
						
						
					}
					
			}

		 }
			//bf.write(Grammar.item(i).getTextContent());


		System.out.println("Done");
		bf.close();
	}
}
