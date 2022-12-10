package externe;

import fc.*;
public class Al2000 extends AdaptAl2000  {
	
	static boolean exist = false;
	static Al2000 al;
	int carte = 1234567890; //9 chiffre + 0:adulte   1:enfant
	
	static public Al2000 creer(){
		if(!exist) {
			exist = true;
			al = new Al2000();
			return al;
		} else {
			return al;
		}
	}
	
	public int carte(){
		   int i = carte;
		   System.out.print("la carte est le"+i);
		return(i);
	}
	public void sortirBr(int id) {
	}
	public void sortirQr(Film f, Personne p) {
		System.out.print("imprimer Qr");
	}

	public void mail(String adrMail, String text) {
		System.out.print(text+" a ete envoye a "+adrMail);
	}
}
