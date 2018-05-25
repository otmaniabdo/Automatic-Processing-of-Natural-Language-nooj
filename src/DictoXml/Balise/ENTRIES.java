package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ENTRIES {
	
	private Element elem;
	
	public ENTRIES(Document document,Element racine){
		elem = document.createElement("ENTRIES");
		racine.appendChild(elem);
	}
	public ENTRIES(){
		
	}
	public void AddAtribut(String attname,String AttValue){
		elem.setAttribute(attname, AttValue);
	}
	public void addargument(Document document,Element racine){
		elem = document.createElement("ENTRIES");
		racine.appendChild(elem);
		
	}
	
	public Element getelem (){
		return elem;
	}
}
