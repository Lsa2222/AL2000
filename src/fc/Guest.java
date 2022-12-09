package fc;
import java.math.BigInteger;
import java.util.HashSet;

public class Guest extends Personne {
	private LocationBR locBr;
	private HashSet<LocationQR> locQr;
	Banque banque= Banque.creer();
	
	public Guest(BigInteger cb) {
		super(cb);
		//TODO est déja présent dans BD ? 
		this.locQr = new HashSet<>();
	}
	
	public int addLocation(LocationBR loc) {
		if (this.locBr != null) {
			System.out.print("vous avez deja loue un film");
			return 3;
		}
		this.locBr=loc;
		return 1;
	}
	
	public int addLocation(LocationQR loc) {
		if (banque.debiter(this.cb, this.PRIXQR)) {

			this.locQr.add(loc);
			return 1;
		}
		System.out.print("pas assez d'argent");
		return 2;

	}
	
	public boolean payer(int argent) {
		return(banque.debiter(this.cb, argent));	
	}

}

