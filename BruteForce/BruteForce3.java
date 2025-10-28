
public class BruteForce3 {

	static String array= "abc";
	public static void bruteFroce(String path, int index) {
		// Base case: siamo arrivati alla fien della stringa
		if(index == array.length()) {
			if(!path.isEmpty()) { // evita di stamapre la sottosequenza vuota
				System.out.println(path);
			}
			return;
			
		}
		//Ricorsione 1: includo il carattere corrente
		bruteFroce(path+ array.charAt(index), index +1);
		// Ricorsione 2: salto il carattere corrente
		bruteFroce(path,index +1);
		
	}
	public static void main(String[]args) {
		System.out.println("tutte le combinazione di 'abc': ");
		bruteFroce("",0);
	}
}
