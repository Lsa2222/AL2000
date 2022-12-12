package fc;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

public class Enfant extends Abonne {
	private HashSet<Tag> rest = new HashSet<>();
	int nbMax;
	private int idParent;
	
		public String toString() {
		return super.toString();
	}
	
	public Enfant(String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb,int idParent,HashSet<Tag> rest,int nbMax) {
		super(prenom, nom, adrMail, adrPhys, credit, cb);
		this.rest=rest;
		this.nbMax=nbMax;
		this.idParent=idParent;
	}
	
	public static Enfant aboToEnfant(Abonne a) {
		Enfant ret = new Enfant(a.getPrenom(),a.getNom(),a.getAdrMail(),a.getAdrPhys(),a.getCredit(),a.getCb(),0,null,168);
		ret.rest=new HashSet<>();
		return ret;
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
	
	@Override
	public Iterator<Tag> getRestrIterator() {
		return rest.iterator();
	}

	public int getIdParent() {
		return id;
	}

	public void setIdParent(int i) {
		idParent = i;
	}

	public int getParentId() {
		return this.idParent;
	}

}
