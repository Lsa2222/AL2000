package fc;

public abstract class Personne {
	long id;
	abstract int addLocation(LocationBR loc);
	abstract int addLocation(LocationQR loc);
	public abstract boolean payer(long l);
	
}
