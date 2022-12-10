package fc;

public class Reservation {
	public Reservation(Abonne a, Film f) {
		this.a = a;
		this.f = f;
		this.b = null;
	}
	Abonne a;
	Film f;
	AdaptAl2000 al=AdaptAl2000.creer();
	BluRay b;
	
	//Ce bluray peut être reserve
	public void dispo(BluRay b) {
		this.b=b;
		al.mail(a.getAdrMail(),"votre bluray "+f.titre+"est pres a la location");
	}
}
