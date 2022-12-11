package externe;
import java.math.BigInteger;

import fc.AdaptBanque;
public class Banque extends AdaptBanque {
	int solde;
	public Banque(){
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
	public void setSolde(int i) {
		this.solde=i;
	}
	protected boolean debiter(BigInteger cb, int achat) {
		if(solde<achat) {
			return false;
		}
		return true;
	}
}
