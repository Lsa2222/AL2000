package fc;

import java.util.HashSet;

public class Abonne extends Personne{
	final static int PRIXQR=10;
	
	private String prenom;
	private String nom;
	private String adrMail;
	private int id;
	private String adrPhys;
	private int credit;
	private HashSet<Tag> rest = new HashSet<>();
	private HashSet<LocationBR> locBr = new HashSet<>();
	private HashSet<LocationQR> locQr = new HashSet<>();
	private HashSet<EntreeHistorique> hist = new HashSet<>();
	
	public Abonne(String prenom, String nom, String adrMail, String adrPhys, HashSet<Tag> rest, int credit, long cb) {
		if(credit<15) {
			System.out.print("mettre + de 15e");
		}
		else {
			super.id=cb;
			this.prenom = prenom;
			this.nom = nom;
			this.adrMail = adrMail;
			this.adrPhys = adrPhys;
			this.rest = rest;
			this.credit=credit;
			//enregistrer abo dans BD
		}
	}
		
	//retourne 1 si la location c'est bien effectue, 3 si il y a deja 3 film, 4 si tag interdit
	public int addLocation(LocationBR loc) {
		HashSet<Tag> inter = new HashSet<>(loc.film.restr);
		inter.retainAll(this.rest);
		//on cree l'intersection des deux liste de restriction pour voir si un tag est interdit
		if (inter.isEmpty()==false) {
			System.out.print("tag interdit:"+inter+"\n");
			return 4;
		}
		if (this.locBr.size()>=3) {
			System.out.print("vous avez deja loue 3film\n");
			return 3;
		}
		this.locBr.add(loc);
		return 1;
	}
	
	public int addLocation(LocationQR loc) {
		if (this.credit<PRIXQR) {
			System.out.print("pas assez d'argent\n");
			return 2;
		}
		this.credit-=PRIXQR;
		this.locQr.add(loc);
		return 1;
	}
	
	public boolean addCredit() {
		//demander a la banque
		return false;
	}

	public void del(LocationBR loc) {
		this.locBr.remove(loc);
	}
	
	public void del(LocationQR loc) {
		this.locQr.remove(loc);
	}
	
	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}
	public String getAdrMail() {
		return adrMail;
	}
	public String getAdrPhys() {
		return adrPhys;
	}

	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}

	public HashSet<LocationBR> getLocBr() {
		return locBr;
	}

	public HashSet<LocationQR> getLocQr() {
		return locQr;
	}

	@Override
	public boolean payer(long argent) {
		if(this.credit>=argent) {
			this.credit-=argent;
			return true;
		}
		else {
			System.out.print("pas assez d'argent");
			return(false);
		}
	}

	
	
	
	
	
}
