package fc;

import java.math.BigInteger;
import java.util.HashSet;

import BDD.FacadeBD;

public class FacadeTLI {
	FacadeBD bd;
	Abonne a;
	Banque banque= Banque.creer();
	Al2000 al=new Al2000();
	CatalogueLocal c=CatalogueLocal.creer();
	
	//renvoi 2 si pas assez d'argent, 0 si il a déja trois compte, 1 si ca marche 
	public int creerAbonne(String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb) {
		if(!banque.debiter(cb, credit)) {
			return(2); 
		}
		Abonne abo = new Abonne(0,prenom,nom,adrMail,adrPhys,credit,cb);
		if(bd.newAbonne(abo)) {
		this.a=abo;
		return 1;
		}
		return 0;
	}
	
	//0 echec 1 adulte 2 enfant
	public int connection(int num) {
		if(al.carte()==0) {
			return 0;
		}
		else {
			this.a=bd.getAbonne(num);
			if(a instanceof Enfant) {
				return 2;
			}
			return 1;
		}
	}
	
	public HashSet<Film> catalogueLocal() {
		return c.getf();
	}
	
	public HashSet<Film> catalogueGlobal() {
		bd.getCatalogueGlobal();
		return null;
	}
	
	public boolean recharger(int c) {
		return this.a.addCredit(c) ;
	}

	//precondition:on est abonne
	public int creerEnfant(String prenom, String nom, int credit, HashSet<Tag> rest, int nbMax) {
		if(!banque.debiter(this.a.getCb(), credit)) {
			return(2); 
		}
		Enfant e=this.a.creerEnfant(prenom, nom, credit, rest, nbMax);
		if(bd.newEnfant(e)) {
			return 0;
		}
		return 1;
	}
	
	public void deconecte() {
		//bd update abonne
		this.a=null;
	}
	
	boolean rendre(LocationBR loc){
		int res=loc.rendre();
		if(this.a.payer(res)) {
			return true;
		}
		return false;
	}
	//todo liste tout les tag
}
