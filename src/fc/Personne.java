package fc;

public abstract class Personne {
	int id;
	abstract int addLocation(LocationBR loc);
	abstract int addLocation(LocationQR loc);
	public abstract boolean payer(long l);
	public int getId() {
		return id;
	}
	
}
