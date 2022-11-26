package fc;

import java.util.HashSet;

public class Enfant extends Abonne {
	private HashSet<Tag> rest = new HashSet<>();
	int nbMax;
	
	
	public Enfant(String prenom, String nom, String adrMail, String adrPhys, int credit, long cb, HashSet<Tag> rest,int nbMax) {
		super(prenom, nom, adrMail, adrPhys, credit, cb);
		this.rest=rest;
		this.nbMax=nbMax;
	}

	public int addLocation(LocationBR loc) {
		HashSet<Tag> inter = new HashSet<>(loc.film.restr);
		inter.retainAll(this.rest);
		//on cree l'intersection des deux liste de restriction pour voir si un tag est interdit
		if (inter.isEmpty()==false) {
			System.out.print("tag interdit:"+inter+"\n");
			return 4;
		}
		return super.addLocation(loc);
	}
	
	public int addLocation(LocationQR loc) {
		HashSet<Tag> inter = new HashSet<>(loc.film.restr);
		inter.retainAll(this.rest);
		if (inter.isEmpty()==false) {
			System.out.print("tag interdit:"+inter+"\n");
			return 4;
		}
		return super.addLocation(loc);
	}

}
