package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class VERB {

	private Element elem;
	
	public VERB(Document document,Element racine){
		elem = document.createElement("VERB");
		racine.appendChild(elem);
	}
	
	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	
	public Element getelem (){
		return elem;
	}
}
