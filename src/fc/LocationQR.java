package fc;

import java.util.Date;

public class LocationQR extends Location{
	Date activation;
	String lienUrl;
	
	public LocationQR(Film film,Personne p) {
		super(film,p);
	}

	int enregistrer(){
		int v = this.p.addLocation(this);
		if(v==1) {
			//generer le qr code
			//imprimer le qrcode
			//envoyer a la bd
			System.out.print("locqr enregistrer\n");

			return 1;
		}
		return v;
	}

	public int getPersonneId() {
		return p.getId();
	}
	
	
}