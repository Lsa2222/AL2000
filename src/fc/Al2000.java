package fc;

public class Al2000 {
	
	static boolean exist = false;
	static Al2000 al;
	
	static Al2000 creer(){
		if(!exist) {
			exist = true;
			al = new Al2000();
			return al;
		} else {
			return al;
		}
	}
	
	int carte(){
		return(20);
	}
	void sortirBr(int id) {
	}
	public void sortirQr(Film f, Personne p) {
		System.out.print("imprimer Qr");
	}
}
