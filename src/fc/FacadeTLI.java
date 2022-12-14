package fc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import BDD.FacadeBD;

public class FacadeTLI {
	FacadeBD bd = FacadeBD.creer();
	Abonne a;
	int connecte=0;
	Guest g;
	AdaptBanque banque= AdaptBanque.creer();
	AdaptAl2000 al=AdaptAl2000.creer();
	CatalogueLocal c=CatalogueLocal.creer();
	

	
	
	//0 echec 1 adulte 2 enfant
	public int connection() {
		if(al.carte()==0) {
			return 0; //carte pas inserer
		}
		else {
			//on regarde la carte inséré dans la machine
			//on récupére le dernier chiffre pour savoir si enfant ou abone
			String s = Integer.toString(al.carte());
			char type = s.charAt(s.length()-1);
			int i=Integer.parseInt(s.substring(0,s.length()-1));
			this.connecte=1;
			if(Character.getNumericValue(type)==0) {
				this.a=bd.getAbonne(i);
				return 1;
			}
			else {
				this.a=bd.getEnfant(i);
				return 2;
			}
		}
	}
	
	
	public void deconecte() {
		this.a=null;
		this.connecte=0;
	}
		
	//fournis a l'ui une liste contenant toute les info de tout les film du catalogue local
	public ArrayList<ArrayList<String>> getfilm(){
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

	//test si le film correspondant au titre f a au moins un BluRay dans le catalogue local
	public boolean	film_dispo(String f) {
		return c.dispo(f);
	}
	
	//preconditon: le film est dispo
	//loue le BluRay associé au film de titre s pour l'abonné ou le guest actuelement connecte
	//return: 1 bon   3 louer trop de film   4 tag interdit   
	public int louer_Br(String s) {
		Personne p;
		if(this.connecte==0) {
			p = g;
		}
		else {
			p = a;
		}
		
		BluRay b = this.c.getBr(s);
		LocationBR loc = new LocationBR(b,p);
		int res = loc.enregistrer();
		if(res == 1) {
			this.al.sortirBr(b.id);
			this.c.out(b);
		}
		return res;
	}
	
	// 1: ok   2: pas assez d'argent   4 tag interdit
	public int louer_Qr(String s) {
		Personne p;
		if(this.connecte==0) {
			p = g;
		}
		else {
			p = a;
		}
		
		Film f = c.stof(s);
		LocationQR loc = new LocationQR(f, p);
		int res = loc.enregistrer();
		if(res == 1) {
			this.al.sortirQr(f,p);
		}
		return res;
	}
	
	//reserve le film de titre s pour l'abonne a
	public void reserve(String s) {
		c.reserve(s, a);
	}

	//return 5 si a n'a pas de reservation, 1 si c'est bon, erreur de location sinon
	public int retirerReseravtion() {
		
		BluRay b = c.getreserve(a);
		if(b==null) {
			return 5;
		}
		LocationBR loc = new LocationBR(b,a);
		int res = loc.enregistrer();
		if(res == 1) {
			this.al.sortirBr(b.id);
		}
		return res;
	}

	//fournis a l'ui une liste contenant toute les info de tout les film du catalogue local
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
	
	//ajoute c credit a l'abonne a
	public boolean recharger(int c) {
		return this.a.addCredit(c) ;
	}

	//renvoi 2 si pas assez d'argent 1 si ca marche et 5 si l'adresse mail est déja associé a un compte
	public int creerAbonne(String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb) {
		
		if(!banque.debiter(cb, credit)) {
			return(2); 
		}
		Abonne abo = new Abonne(prenom,nom,adrMail,adrPhys,credit,cb); //id ?
		bd.newAbonne(abo);
		this.a=abo;
		return 1;
	}
	
	//precondition:on est abonne
	//2 pas assez d'argent 1 ok  5 adresse mail déja existante
	public int creerEnfant(String prenom, String nom, int credit, ArrayList<String> rest, int nbMax) {
		
		if(!banque.debiter(this.a.getCb(), credit)) {
			return(2); 
		}
		HashSet<Tag> tag = new HashSet<Tag>();
		for (Tag t: Tag.values()) {
			if (rest.contains(t.toString())) {
				tag.add(t);
			}
		}
		Enfant e=this.a.addEnfant(prenom, nom, credit, tag, nbMax);
		bd.newEnfant(e);
		return 1;
	}
	
	//creer un gest et enregistre sa carte banquaire
	public int creerGuest(BigInteger cb) {
		g = new Guest(cb);
		return 0;
	}

	//fournit une liste de TOUT les tag disponibles
	public ArrayList<String> listeTag() {
		ArrayList<String> l = new ArrayList<String>();
		for(Tag t: Tag.values()) {
			l.add(t.toString());
		}
		return l;
	}
	
	//rend le disque insere dans la machine
	//2 pas assez de crédits et 3 insérer cd et 1 c'est ok
	public int rendre(){
		BluRay br = al.lireBr();
		if(br == null) {
			return 3;
		}
		for(LocationBR l : a.getLocBr()) {
			if(l.br.id == br.id) {
				int res=l.rendre();
				if(this.a.payer(res)) {
					c.setBr(br);
					bd.delLocation(l);
					return 1;
				}
				else {
					return 2;
				}
			}
		}
		return 0;
	}

//	demande d'ajout au catalogue local pour le mois prochain
	public void demande(String s) {
		Film f = c.stof(s);
		c.film_demande.add(f);
	}
	
	//precondition: un abonne est connecte
	//donne toute les info sur l'abonne actuelement connecte
	//prenom nom mail adr credit cb 
	public ArrayList<String> info() {
		ArrayList<String> info = new ArrayList<String>();
		info.add(a.getPrenom());
		info.add(a.getNom());
		info.add(a.getAdrMail());
		info.add(a.getAdrPhys());
		info.add(Integer.toString(a.getCredit()));
		info.add(a.getCb().toString());
		return info;
	}
	
	//return une liste de location de l'abonne a sous la forme film + date
	public ArrayList<String> LocationEnCour() {
		ArrayList<String> info = new ArrayList<String>();
		for(LocationBR loc : a.getLocBr()) {
			info.add(loc.film.titre + loc.date.toString());
		}
		return info;
	}
	
	//donne toute les réstriction de l'abonné actuel
	public ArrayList<String> TagAbonne() {
		ArrayList<String> tag = new ArrayList<String>();
		Iterator<Tag> it = a.getRestrIterator();
		while(it.hasNext()) {
			Tag t = it.next();
			tag.add(t.toString());
		}
		return tag;
	}
}
