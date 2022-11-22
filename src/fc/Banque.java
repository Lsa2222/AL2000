package fc;

public class Banque {
	private Banque(){
		
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
	
	boolean debiter(int numCmt, int solde) {
		if(solde%10==9) {
			System.out.println("client "+numCmt+" n'as pas assez d'argent");
			return false;
		}
		System.out.println("client "+numCmt+" debiter de "+solde);
		return true;
	}
}