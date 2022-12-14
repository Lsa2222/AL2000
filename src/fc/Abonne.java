package fc;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

import BDD.FacadeBD;

public class Abonne extends Personne{
	AdaptBanque banque= AdaptBanque.creer();

	
	private String prenom;
	private String nom;
	private String adrMail;
	private BigInteger cb;
	private String adrPhys;
	private int credit;
	private HashSet<LocationBR> locBr = new HashSet<>();
	private HashSet<LocationQR> locQr = new HashSet<>();
	private HashSet<Enfant> enfant = new HashSet<>();
	int numCarte;
	
	
	
	public Abonne(String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb) {
			super(cb);
			this.prenom = prenom;
			this.nom = nom;
			this.adrMail = adrMail;
			this.adrPhys = adrPhys;
			this.credit=credit;
	}
		public String toString() {
		return super.toString() + " " + prenom + " "+ nom + " "+ adrMail + " "+ adrPhys + " "+ credit;
	}	
	//retourne 1 si la location c'est bien effectue, 3 si il y a deja 3 film, 4 si tag interdit
	public int addLocation(LocationBR loc) {
		
		if (this.locBr.size()>=3) {
			return 3;
		}
		this.locBr.add(loc);
		return 1;
	}
	
	public int addLocation(LocationQR loc) {
		if (this.credit<PRIXQR) {
			return 2;
		}
		this.credit-=PRIXQR;
		this.locQr.add(loc);
		return 1;
	}
	
	public boolean addCredit(int c) {
		boolean res=banque.debiter(this.cb, c);
		if(res) {
			FacadeBD bd = FacadeBD.creer();
			this.credit+=c;
			bd.updCredit(this,this.credit);
			return true;
		}
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
	
	//credite du compte d'abonne le nombre de credit 
	@Override
	public boolean payer(int c) {
		if(this.credit>=c) {
			this.credit-=c;
			return true;
		}
		else {
			System.out.print("pas assez de credit");
			return(false);
		}
	}
	
	public Enfant addEnfant(String prenom, String nom, int credit, HashSet<Tag> rest, int nbMax) {
		Enfant e = new Enfant (prenom, nom, adrMail, adrPhys, credit, cb, this.id, rest, nbMax);
		this.enfant.add(e);
		return e;
	}

	public int getId() {
		return id;
	}
	
	public int addLocationAdmin(LocationBR loc) {
		this.locBr.add(loc);
		return 1;
	}

	public int addLocationAdmin(LocationQR loc) {
		this.locQr.add(loc);
		return 1;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Enfant addEnfant(Enfant e) {
		this.enfant.add(e);
		return e;
	}
	
	public HashSet<Enfant> getEnfants() {
		return this.enfant;
	}
	public Iterator<Tag> getRestrIterator() {
		// TODO Auto-generated method stub
		HashSet<Tag> tab = new HashSet<Tag>();
		return tab.iterator();
	}
	

	
	
	
	
	
}
