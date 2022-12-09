package fc;

import java.util.Date;
import java.util.Timer;

public class LocationQR extends Location{
	Date activation;
	String lienUrl;
	
	public String toString() {
		return super.toString() + " " + activation + " " + lienUrl;
	}
	
	public LocationQR(Film film,Personne p) {
		super(film,p);
	}

	int enregistrer(){
		int v = this.p.addLocation(this);
		if(v==1) {
			//generer le qr code
			//imprimer le qrcode
			//envoyer a la bd
			this.lienUrl="";
			this.date=new Date();
			System.out.print(this.date.toString()+"locqr enregistrer\n");

			return 1;
		}
		return v;
	}
	
	void activer() {
		this.activation=new Date();
	    Timer timer = new Timer();
	    long delay = 3000;
	    timer.schedule(new MaTask(this),delay);
	}
	
	public int getPersonneId() {
		return p.getId();
	}

	public int getFilmId() {
		return super.film.getId();
	}

	public long getActivationDate() {
		return super.date.getTime();//TODO : Activation ? Date ?
	}

	public long getDate() {
		return super.date.getTime();//TODO : Activation ? Date ?
	}
	
}