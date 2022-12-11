package fc;
import externe.Al2000;
public abstract class AdaptAl2000 {
	
	static boolean exist = false;
	static Al2000 al = null;
		
	public abstract int carte();
	public abstract void sortirBr(int id);
	public abstract void sortirQr(Film f, Personne p);

	public abstract void mail(String adrMail, String text);
	public abstract BluRay lireBr();
	public static Al2000 creer() {
		if(!exist) {
			exist = true;
			al = new Al2000();
			return al;
		} else {
			return al;
		}
	}
	protected abstract void setLireBr(BluRay br1);
	protected abstract void setCarte(int i);
}
