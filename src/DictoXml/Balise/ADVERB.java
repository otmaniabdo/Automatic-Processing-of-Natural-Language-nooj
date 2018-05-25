package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ADVERB {
	private Element elem;
	public ADVERB(Document document,Element pere){
		elem = document.createElement("ADVERB");
		pere.appendChild(elem);
	}

	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	
	public Element getelem (){
		return elem;
	}

}
