package fc;

import java.math.BigInteger;
import java.util.HashSet;

public class Guest extends Personne {
	private LocationBR locBr;
	private HashSet<LocationQR> locQr;
	
	public Guest(BigInteger cb) {
		super(0,cb);
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
		if (loc==loc/*acces a la banque*/) {
			System.out.print("pas assez d'argent");
			return 2;
		}
		this.locQr.add(loc);
		return 1;
	}
	
	public boolean payer(long argent) {
		return(true);//voir avec sa banque	
	}

}

