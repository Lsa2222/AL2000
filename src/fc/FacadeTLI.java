package fc;

import java.util.HashSet;

public class FacadeTLI {
	
	Abonne a;
	Banque banque= Banque.creer();
	Al2000 al=new Al2000();
	CatalogueLocal c=CatalogueLocal.creer();
	
	//renvoi 2 si pas assez d'argent, 0 si il a déja trois compte, 1 si ca marche 
	public int creerAbonne(String prenom, String nom, String adrMail, String adrPhys, int credit, long cb) {
		if(!banque.debiter(cb, credit)) {
			return(2); 
		}
		this.a = new Abonne(prenom,nom,adrMail,adrPhys,credit,cb);
		//res=envoyer a la bd
		int res = 1;
		return res;
	}
	
	//dire si c'est enfant ou adulte 1 adulte 2 enfant
	public boolean connection() {
		if(al.carte()==0) {
			return false;
		}
		else {
			//abo:demandé a la BD
			//this.a=abo
			return true;
		}
	}
	
	public HashSet<Film> catalogueLocal() {
		return c.getf();
	}
	
	public HashSet<Film> catalogueGlobal() {
		//get cat BD
		return null;
	}
	
	public boolean recharger(int c) {
		return this.a.addCredit(c) ;
	}
	//return des message d'erreur
	//precondition:on est abonne
	public int creerEnfant(String prenom, String nom, int credit, HashSet<Tag> rest, int nbMax) {
		if(!banque.debiter(this.a.cb, credit)) {
			return(2); 
		}
		this.a.creerEnfant(prenom, nom, credit, rest, nbMax);
		return 1;
	}
	
	public void deconecte() {
		this.a=null;
	}
	
}
