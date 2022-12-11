package externe;

import fc.*;
public class Al2000 extends AdaptAl2000  {
	
	static boolean exist = false;
	static Al2000 al;
	static BluRay br;
	
	//carte acuelement dans la machine
	int carte = 50; //chiffre + 0:adulte   1:enfant
	
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
	//renvoi le Br actuelment dans la fente de la machine 
	public BluRay lireBr() {
		return this.br;
	}
	
	public void setCarte(int i) {
		this.carte=i;
	}
	public void setLireBr(BluRay b){
		this.br=b;
	}
}
