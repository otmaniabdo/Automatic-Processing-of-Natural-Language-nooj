package DictoXml;

public class Line {
	
	private String line;
	private char[] arraycharline;
	
	public Line(String Line){
		line = Line;
		arraycharline = line.toCharArray();
	}
	public Line(){
		
	}

	public String getLine(){
		return line;
	}
	public char[] getArraychar(){
		return arraycharline;
	}
	
	public int setline(String newline){
		if((line = newline) != null){
			return -1;
		}else{
			return 0;
		}
	}
	public int searchForComma(){
		for(int i=0;i<line.length();i++){ 
			if(line.charAt(i) == ','){
				return i;
			}
		}
		return -1;
	}
	public int searchForComma(int index){
		for(int i=index;i<line.length();i++){ 
			if(line.charAt(i) == ','){
				return i;
			}
		}
		return -1;
	}
	
	public int searchForplus(){
		for(int i=0;i<line.length();i++){ 
			if(line.charAt(i) == '+'){
				return i;
			}
		}
		return -1;
	}
	public int searchForPlus(int index){
		for(int i=index;i<line.length();i++){ 
			if(line.charAt(i) == '+'){
				return i;
			}
		}
		return -1;
	}
	public int searchForequals(int index){
		for(int i=index;i<line.length();i++){ 
			if(line.charAt(i) == '='){
				return i;
			}
		}
		return -1;
	}
	public int searchForequals(){
		for(int i=0;i<line.length();i++){ 
			if(line.charAt(i) == '='){
				return i;
			}
		}
		return -1;
	}
	
	public int numberOfComma(){
		int cont=0;
		
		for(int i=0;i<line.length();i++){
			if(line.charAt(i) == ',')
				cont=cont+1 ;
		}
		return cont;
	}
}
