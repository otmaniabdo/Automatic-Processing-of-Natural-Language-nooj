package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NOUN {

	private Element elem;
	
	public NOUN(Document document,Element racine){
		elem = document.createElement("NOUN");
		racine.appendChild(elem);
	}
	
	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	
	public Element getelem (){
		return elem;
	}
	
	
	
}
