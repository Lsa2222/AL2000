package fc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LocationBR extends Location {
	final static int PRIXBR=5;
	
	public LocationBR(BluRay br, Personne p) {
		super(br.film, p);
		this.br=br;
	}
	BluRay br;
	
	int enregistrer(){
		int v = this.p.addLocation(this);
		if(v==1) {
			//sortir le bluray
			//envoyer a la bd
			System.out.print("loc enregistrer\n");

			return 1;
		}
		return v;
	}
	
	int rendre() {
		if(this.br.estBon==false/*il faudrais demandé si on arrive a le lire*/) {
			System.out.print("c'est gratuit\n");
			return 0;
		}
		Date ajd= new java.util.Date();
	    long diffInMillies = Math.abs(ajd.getTime() - this.date.getTime());
	    long total = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)+1;
		return (int) (total*PRIXBR);
	}
	
	public int getBRId() {
		return br.getId();
	}

	public int getPersonneId() {
		return p.getId();
	}

	public long getDate() {
		return super.date.getTime();
	}

	
}