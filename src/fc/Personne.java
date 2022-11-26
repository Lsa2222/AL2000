package fc;

public abstract class Personne {
	int cb;
	abstract int addLocation(LocationBR loc);
	abstract int addLocation(LocationQR loc);
	public abstract boolean payer(long l);
	
	public int getCb() {
		return cb;
	}
}
