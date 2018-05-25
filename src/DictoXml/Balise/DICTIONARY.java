package DictoXml.Balise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DICTIONARY {
	
		private Element racine;
		
		public DICTIONARY (Document document){
			racine = document.createElement("DICTIONARY");
			document.appendChild(racine);
		}

		
		public void AddAtribut(String attname,String AttValue){
			racine.setAttribute(attname, AttValue);
		}
		
		public Element getracine (){
			return racine;
		}
		
}
