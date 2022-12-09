package fc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

import BDD.FacadeBD;

public class FacadeTLI {
	FacadeBD bd;
	Abonne a;
	int connecte=0;
	Guest g;
	Banque banque= Banque.creer();
	Al2000 al=new Al2000();
	CatalogueLocal c=CatalogueLocal.creer();
	
	//renvoi 2 si pas assez d'argent, 0 si il a déja trois compte, 1 si ca marche 
	public int creerAbonne(String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb) {
		if(!banque.debiter(cb, credit)) {
			return(2); 
		}
		Abonne abo = new Abonne(prenom,nom,adrMail,adrPhys,credit,cb);
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
			this.connecte=1;
			if(a instanceof Enfant) {
				return 2;
			}
			return 1;
		}
	}
	
	public HashSet<String> getnomfilm(){
		HashSet<String> res = new HashSet<String>();
		for (Film f : this.c.film) {
			res.add(f.titre);
		}
		return res;
	}
	
	public ArrayList<ArrayList<String>> getfilm(){
		//TODO film dispo ?
		ArrayList<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		for (Film f : this.c.film) {
			ArrayList<String> film = new ArrayList<String>();
			film.add(f.titre);
			film.add(f.reali);
			film.add(f.descr);
			film.add(f.image);
			String res="";
			for (Tag r : f.restr) {
				res+=r.toString();
				res+=" ";
			}
			film.add(res);
			l.add(film);
		}
		return l;
	}
	
	public int louer_Br(Film f) {
		Personne p;
		if(this.connecte==0) {
			p = g;
		}
		else {
			p = a;
		}
		
		BluRay b = this.c.getBr(f);
		LocationBR loc = new LocationBR(b,p);
		int res = loc.enregistrer();
		if(res == 1) {
			this.al.sortirBr(b.id);
			this.c.out(b);
		}
		return res;
	}
	
	public int louer_Qr(Film f) {
		Personne p;
		if(this.connecte==0) {
			p = g;
		}
		else {
			p = a;
		}
		
		LocationQR loc = new LocationQR(f, p);
		int res = loc.enregistrer();
		if(res == 1) {
			this.al.sortirQr(f,p);
		}
		return res;
	}
	
	public ArrayList<ArrayList<String>> catalogueGlobal() {
		HashSet<Film> c = bd.getCatalogueGlobal();
		ArrayList<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		for (Film f : c) {
			ArrayList<String> film = new ArrayList<String>();
			film.add(f.titre);
			film.add(f.reali);
			film.add(f.descr);
			film.add(f.image);
			String res="";
			for (Tag r : f.restr) {
				res+=r.toString();
				res+=" ";
			}
			film.add(res);
			l.add(film);
		}
		return l;
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
	
	public int creerGuest(BigInteger cb) {
		g = new Guest(cb);
		return 0;
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
}
