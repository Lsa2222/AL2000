import fc.*;
public interface AdaptAl2000 {
	
	static boolean exist = false;
		
	int carte();
	void sortirBr(int id);
	public void sortirQr(Film f, Personne p);

	public void mail(String adrMail, String text);
}
