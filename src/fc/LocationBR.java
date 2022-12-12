package fc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import BDD.FacadeBD;

public class LocationBR extends Location {
	final static int PRIXBR=5;
	
	public LocationBR(BluRay br, Personne p) {
		super(br.film, p);
		this.br=br;
		
	}
	BluRay br;
	
		public String toString() {
		return super.toString() + " " + br;
	}
	int enregistrer(){
		int v = this.p.addLocation(this);
		if(v==1) {
			FacadeBD bd = FacadeBD.creer();
			bd.newLocation(this);
			return 1;
		}
		return v;
	}
	
	int rendre() {
		System.out.println("rendre :" +br.id+br.estBon);
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

	public BluRay getBR() {
		return br;
	}
	
	public int getPersonneId() {
		return p.getId();
	}
		public long getDate() {
		return super.date.getTime();
	}
	
}
