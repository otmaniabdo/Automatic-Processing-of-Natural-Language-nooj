package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PREPOSITION {
		
	private Element elem;
	public PREPOSITION(Document document,Element pere){
		elem = document.createElement("PART");
		pere.appendChild(elem);
	}

	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	
	public Element getelem (){
		return elem;
	}
}
