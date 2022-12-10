package BDD;

import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.Enfant;
import fc.Film;
import fc.LocationBR;
import fc.LocationQR;
import fc.Personne;
import fc.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

public class FacadeBD2 {
	
	FacadeBD2(){
		
	}
	
	//PersonneDAO & AbonneDAO & EnfantDAO
	
	public boolean newPersonne(Personne a) {
		System.out.println("ajout de la personne ");
		System.out.println(a);
		return true;
	}
	public boolean newAbonne(Abonne a) {
		System.out.println("ajout de l'abonne ");
		System.out.println(a);
		return true;
	}
	public boolean newEnfant(Enfant a) {
		System.out.println("ajout de l'enfant ");
		System.out.println(a);
		return true;
	}
	
	//recupére l'abonne avec le numero de carte num 
	public Abonne getAbonne(int num) {
		Abonne abo = null;
		System.out.println("acces a l'abonne "+num);
		abo = new Abonne(num,"Personne","Commun","adr@mail.pays","1 rue de l'avenue",123,BigInteger.valueOf(1234567981));
		System.out.println(abo);
		return abo;
	}
	public Enfant getEnfant(int num) {
		Enfant enf = null;
		System.out.println("acces a l'enfant "+num);
		enf = new Enfant(num,"Gamin","Bruillant","jeune@spam.vc","42 avenu du chemain",123,BigInteger.valueOf(3210),1,new HashSet<Tag>(),1);
		System.out.println(enf);
		return enf;
	}
	
	//met à c le crédit de l'abonne a;
	public boolean updCredit(Abonne a,int c) {
		a.setCredit(c);
		return true;
	}
	
	//FilmDAO
	
	public HashSet<Film> getCatalogueGlobal(){
		HashSet<Film> ret = new HashSet<Film>();
		HashSet<Tag> tagTitre = new HashSet<Tag>();
		tagTitre.add(Tag.FANTASY);
		ret.add(new Film(123,"titre","realisateur","description",tagTitre,"image"));
		ret.add(new Film(101,"<redacted>","<redacted>","Un <redacted> pour <redacted>",new HashSet<Tag>(),"<redacted>"));
		return ret;
	}
	
	// LocationDAO
	
	public boolean newLocation(LocationBR br) {
		System.out.println("ajout de la location br");
		System.out.println(br);
		return true;
	}
	public boolean newLocation(LocationQR qr) {
		System.out.println("ajout de la location qr");
		System.out.println(qr);
		return true;
	}
	public boolean delLocation(LocationBR br) {
		System.out.println("suppression de la location br");
		System.out.println(br);
		return true;
	}
	public boolean delLocation(LocationQR qr) {
		System.out.println("suppression de la location qr");
		System.out.println(qr);
		return true;
	}
	
}
