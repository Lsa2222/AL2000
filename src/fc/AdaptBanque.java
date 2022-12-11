package fc;

import java.math.BigInteger;

import externe.Banque;

public abstract class AdaptBanque {
	int solde;
	public AdaptBanque(){
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
	public abstract void setSolde(int i);
	protected abstract boolean debiter(BigInteger cb, int achat);
}
