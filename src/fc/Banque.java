package fc;

import java.math.BigInteger;

public class Banque {
	int solde;
	private Banque(){
		this.solde=20;
	}
	
	static boolean exist = false;
	static Banque b;
	
	static Banque creer(){
		if(!exist) {
			exist = true;
			b = new Banque();
			return b;
		} else {
			return b;
		}
	}
	
	boolean debiter(BigInteger bigInteger, int achat) {
		if(solde<achat) {
			System.out.println("client "+bigInteger+" n'as pas assez d'argent");
			return false;
		}
		
		System.out.println("client "+bigInteger+" debiter de "+achat);
		return true;
	}
}