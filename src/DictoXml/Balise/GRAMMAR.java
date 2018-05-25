package DictoXml.Balise;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class GRAMMAR extends GRAMMARS{
	
	
	public GRAMMAR(Document document,Element pere){
		elem = document.createElement("GRAMMAR");
		pere.appendChild(elem);
		}
	public GRAMMAR(){
		super();
	}
	public void addText(Document document,String value){
		elem.appendChild(document.createTextNode(value));
	}
	
}