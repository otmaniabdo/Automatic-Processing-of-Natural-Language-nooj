package DictoXml.Balise;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GRAMMARS {
			
		protected Element elem ;
		
		public GRAMMARS(Document document,Element pere){
			elem = document.createElement("GRAMMARS");
			pere.appendChild(elem);
		}
		public GRAMMARS(){
			
		}
		public void AddAtribut(String attname,String AttValue){
			elem.setAttribute(attname, AttValue);
		}
		
		public Element getelem (){
			return elem;
		}
		
		
		
}
