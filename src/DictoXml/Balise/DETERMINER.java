package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DETERMINER {
	
	private Element elem;
	public DETERMINER(Document document,Element pere){
		elem = document.createElement("DETERMINER");
		pere.appendChild(elem);
	}

	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	
	public Element getelem (){
		return elem;
	}
}
