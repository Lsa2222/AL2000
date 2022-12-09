package fc;

import java.math.BigInteger;

public abstract class Personne {
	BigInteger cb;
	int id;
	Personne(int id,BigInteger cb2){
		this.id=id;
		this.cb=cb2;
	}
	
	public String toString() {
		return id + " " + cb + " ";
	}
	
	abstract int addLocation(LocationBR loc);
	abstract int addLocation(LocationQR loc);
	public abstract boolean payer(long l);
	
	public BigInteger getCb() {
		return cb;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
