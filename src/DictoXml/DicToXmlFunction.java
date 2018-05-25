package DictoXml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import DictoXml.Balise.ADVERB;
import DictoXml.Balise.DETERMINER;
import DictoXml.Balise.DICTIONARY;
import DictoXml.Balise.ENTRIES;
import DictoXml.Balise.GRAMMAR;
import DictoXml.Balise.GRAMMARS;
import DictoXml.Balise.NOUN;
import DictoXml.Balise.PREPOSITION;
import DictoXml.Balise.VERB;

public class DicToXmlFunction {
	public static void dictoxml(File f) throws IOException, ParserConfigurationException, TransformerException, SAXException {
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		
		
		DICTIONARY dictionnaire = new DICTIONARY(document);
		dictionnaire.AddAtribut("Language", "AR");
		
		GRAMMARS Grammars = new GRAMMARS(document,dictionnaire.getracine());
		
		ENTRIES entries = new ENTRIES(document,dictionnaire.getracine());
		
		String word;
		Line line = new Line();
		while((line.setline(br.readLine())) != 0 ){
			if(!line.getLine().isEmpty()){
			  if(line.getLine().charAt(0) != '#'){
				  if(line.numberOfComma() == 1){// la ligne contient une seule vergule 
					  
					int comma = line.searchForComma() ;
					int plus = line.searchForplus();
					
					if(plus != -1){
						
					  if((plus - comma) == 2 ){
						  if(line.getLine().charAt(comma+1) == 'V'){
							  
							  VERB verb = new VERB(document,entries.getelem());
							  verb.AddAtribut("LEMMA",line.getLine().substring(0,comma+3) );
							  int newplus,equal;
							  while(plus != -1){
								  newplus = line.searchForPlus(plus+1);
								  if(newplus != -1){
									  
									  equal = line.searchForequals(plus+1);

									  verb.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
									  
								  }else if(line.searchForequals(plus+1) != -1){
									  equal = line.searchForequals(plus+1);
									  verb.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

								  }
								  plus = newplus;
								  
							  }
						  }else if(line.getLine().charAt(comma+1) == 'N'){
							  
							  NOUN noun = new NOUN(document,entries.getelem());
							  noun.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
							  
							  int newplus,equal;
							  while(plus != -1){
								  newplus = line.searchForPlus(plus+1);
								  if(newplus != -1){
									  
									  equal = line.searchForequals(plus+1);
									  
										  noun.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
									  
								  }else if(line.searchForequals(plus+1) != -1){
									  equal = line.searchForequals(plus+1);
									  noun.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

								  }
								  plus = newplus;
								  
							  }
						  }
					  }else{
						  		String neline = line.getLine().substring(comma+1,plus);
								  if(neline.matches("DET")){
									  DETERMINER det = new DETERMINER(document,entries.getelem());
									  det.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
								  
									  int newplus,equal;
									  while(plus != -1){
										  newplus = line.searchForPlus(plus+1);
										  if(newplus != -1){
											  
											  equal = line.searchForequals(plus+1);

											  det.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
											  
										  }else if(line.searchForequals(plus+1) != -1){
											  equal = line.searchForequals(plus+1);
											  det.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

										  }
										  plus = newplus;
										  
									  }
							}
						  
					  }
					}else if(plus == -1){
						String lastWord = line.getLine().substring(comma+1,line.getLine().length());
						  if(lastWord.matches("ADV")){
							  ADVERB adv = new ADVERB(document,entries.getelem());
							  adv.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
						  }else if(lastWord.matches("PREP")){
							  PREPOSITION preposition = new PREPOSITION(document,entries.getelem());
							  preposition.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
						  }
					  }
				  
				  
				 }else if(line.numberOfComma() == 2){ // line contient 2 vergule 
					// System.out.println("Find 2 Comma");
					int comma = line.searchForComma() ;
					int plus = line.searchForplus();
					
					if(plus != -1){
						
					  if((plus - line.getLine().lastIndexOf(',')) == 2 ){
						  if(line.getLine().charAt(line.getLine().lastIndexOf(',')+1) == 'V'){
							  
							  VERB verb = new VERB(document,entries.getelem());
							  verb.AddAtribut("LEMMA", line.getLine().substring(comma+1,line.getLine().lastIndexOf(',')));
							  verb.AddAtribut("Root",line.getLine().substring(0,comma));
							  int newplus,equal;
							  while(plus != -1){
								  newplus = line.searchForPlus(plus+1);
								  if(newplus != -1){
									  
									  equal = line.searchForequals(plus+1);

									  verb.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
									  
								  }else if(line.searchForequals(plus+1) != -1){
									  equal = line.searchForequals(plus+1);
									  verb.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

								  }
								  plus = newplus;
								  
							  }
						  }else if(line.getLine().charAt(line.getLine().lastIndexOf(',')+1) == 'N'){
							  
							  NOUN noun = new NOUN(document,entries.getelem());
							  noun.AddAtribut("LEMMA",line.getLine().substring(comma+1,line.getLine().lastIndexOf(',')));
							//  System.out.println(line.getLine().substring(comma+1,line.getLine().lastIndexOf(',')));
							  noun.AddAtribut("Root",line.getLine().substring(0,comma));

							  int newplus,equal;
							  while(plus != -1){
								  newplus = line.searchForPlus(plus+1);
								  
								  if(newplus != -1){
									  
									  equal = line.searchForequals(plus+1);
									  String derivation = line.getLine().substring(plus+1,newplus);
									  if(derivation.matches("MSDA")|| derivation.matches("MSDH") || derivation.matches("MSDM") || derivation.matches("MSDMM") || derivation.matches("MSDS")){
										  noun.AddAtribut("Derivation",derivation);
									  }else{
										  noun.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
									  }
								  }else if(line.searchForequals(plus+1) != -1){
									  equal = line.searchForequals(plus+1);
									  noun.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

								  }
								  plus = newplus;
								  
							  }
						  }
					  }else{
						  		String neline = line.getLine().substring(comma+1,plus);
								  if(neline.matches("DET")){
									  DETERMINER det = new DETERMINER(document,entries.getelem());
									  det.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
								  
									  int newplus,equal;
									  while(plus != -1){
										  newplus = line.searchForPlus(plus+1);
										  if(newplus != -1){
											  
											  equal = line.searchForequals(plus+1);

											  det.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(newplus) ));
											  
										  }else if(line.searchForequals(plus+1) != -1){
											  equal = line.searchForequals(plus+1);
											  det.AddAtribut(line.getLine().substring((plus+1),(equal) ),line.getLine().substring((equal+1),(line.getLine().length()-1) ));

										  }
										  plus = newplus;
										  
									  }
							}
						  
					  }
					}else if(plus == -1){
						String lastWord = line.getLine().substring(comma+1,line.getLine().length());
						  if(lastWord.matches("ADV")){
							  ADVERB adv = new ADVERB(document,entries.getelem());
							  adv.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
						  }else if(lastWord.matches("PREP")){
							  PREPOSITION preposition = new PREPOSITION(document,entries.getelem());
							  preposition.AddAtribut("LEMMA",line.getLine().substring(0,comma) );
						  }
					  }
				  
					 
				 }
			}else{
				if(line.getLine().length()>3){
				   word = line.getLine().substring(1,4) ;
				   if(word.equals("use")){
					   GRAMMAR grammar = new GRAMMAR(document,Grammars.getelem());
					   grammar.addText(document,line.getLine().substring(5));

				   }else{
					   Comment commentaire = document.createComment(line.getLine().substring(1));
					   entries.getelem().appendChild(commentaire);
				   }
				}else{
					Comment commentaire = document.createComment(line.getLine().substring(1));
					   entries.getelem().appendChild(commentaire);
				}
				
			}
				// Fin de traitment des lines
		}
	}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		String FileName;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do{
		System.out.println("Donner le Nom de Fichie XML :");
		FileName = sc.nextLine();
		}while(FileName.isEmpty());
		StreamResult sortie = new StreamResult(new File(FileName+".xml"));
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.transform(source, sortie);

		fr.close();
		br.close();
		System.out.println("Done");
	}

}
