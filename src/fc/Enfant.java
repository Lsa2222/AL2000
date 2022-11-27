package fc;

import java.util.HashSet;
import java.util.Iterator;

public class Enfant extends Abonne {
	private HashSet<Tag> rest = new HashSet<>();
	
	private int idParent;
	
	
	public Enfant(int id, String prenom, String nom, String adrMail, String adrPhys, int credit, int cb,int idParent, HashSet<Tag> rest,int nbMax) {
		super(id, prenom, nom, adrMail, adrPhys, credit, cb);
		this.rest=rest;
		super.nbMax=nbMax;
		this.idParent=idParent;
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

	public Iterator<Tag> getRestrIterator() {
		return rest.iterator();
	}

	public static Enfant aboToEnfant(Abonne a) {
		Enfant ret = (Enfant) a;
		ret.rest=new HashSet<>();
		ret.nbMax=168;
		ret.idParent=-1;
		return null;
	}

	public int getParentId() {
		return idParent;
	}

	public void setIdParent(int i) {
		idParent = i;
	}

}
