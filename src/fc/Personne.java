package fc;

public abstract class Personne {
	int cb;
	int id;
	Personne(int id,int cb){
		this.id=id;
		this.cb=cb;
	}
	
	abstract int addLocation(LocationBR loc);
	abstract int addLocation(LocationQR loc);
	public abstract boolean payer(long l);
	
	public int getCb() {
		return cb;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
