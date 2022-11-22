package fc;

import java.util.Date;

public abstract class  Location {
	Film film;
	Date date;
	Personne p;
	
	public Location(Film film, Personne p) {
		this.film = film;
		this.p = p;
		this.date= new java.util.Date();
	}
	
	//test si la location est valide, si oui l'enregistre
	//sinon 2:pas assez d'argent	3:trop de film sur ce compte	4:tag interdis
	abstract int enregistrer();

}