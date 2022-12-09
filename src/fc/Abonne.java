package fc;

import java.math.BigInteger;
import java.util.HashSet;

public class Abonne extends Personne{
	final static int PRIXQR=10;
	Banque banque= Banque.creer();

	protected int nbMax;
	
	private String prenom;
	private String nom;
	private String adrMail;
	//private BigInteger cb;
	private String adrPhys;
	private int credit;
	private HashSet<LocationBR> locBr = new HashSet<>();
	private HashSet<LocationQR> locQr = new HashSet<>();
	private HashSet<Enfant> enfant = new HashSet<>();
	int numCarte;
	
	public Abonne(int idPersonne, String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb) {
			super(idPersonne,cb);
			this.prenom = prenom;
			this.nom = nom;
			this.adrMail = adrMail;
			this.adrPhys = adrPhys;
			this.credit=credit;
			this.nbMax=168;
	}
	
	
	public String toString() {
		return super.toString() + " " + prenom + " "+ nom + " "+ adrMail + " "+ adrPhys + " "+ credit;
	}
		
	//retourne 1 si la location c'est bien effectue, 3 si il y a deja 3 film, 4 si tag interdit
	public int addLocation(LocationBR loc) {
		
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
	
	public int addLocationAdmin(LocationBR loc) {
		this.locBr.add(loc);
		return 1;
	}
	
	public int addLocationAdmin(LocationQR loc) {
		this.locQr.add(loc);
		return 1;
	}
	
	public boolean addCredit(int c) {
		boolean res=banque.debiter(this.cb, c);
		if(res) {
			//mettre a jour BD avec this.credit +c
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

	@Override
	public boolean payer(long argent) {
		if(this.credit>=argent) {
			this.credit-=argent;
			return true;
		}
		else {
			System.out.print("pas assez de credit");
			return(false);
		}
	}
	
	public Enfant creerEnfant(String prenom, String nom, int credit, HashSet<Tag> rest, int nbMax) {
		Enfant e = new Enfant (0, prenom, nom, adrMail, adrPhys, credit, cb, this.id, rest, nbMax);
		this.enfant.add(e);
		return e;
	}
}
