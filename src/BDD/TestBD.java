package BDD;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.BluRay;
import fc.Enfant;
import fc.Film;
import fc.LocationBR;
import fc.LocationQR;
import fc.Personne;
import fc.Tag;

public class TestBD {
	
	static void testAbo(FacadeBD fbd) {//echec locations
		System.out.println("Abonne = ");
		for(int i = 1 ;i<6;i++) {
			Abonne a = fbd.getAbonne(i);
			System.out.println(a.toString());
			Iterator<LocationBR> locBr = a.getLocBr().iterator();
			Iterator<LocationQR> locQr = a.getLocQr().iterator();
			while(locBr.hasNext()) {
				System.out.println("    "  + locBr.next().toString());
			}
			while(locQr.hasNext()) {
				System.out.println("    "  + locQr.next().toString());
			}
		}
	}
	
	static void testEnf(FacadeBD fbd) {
		System.out.println("Enfant = ");
		for(int i = 2 ;i<6;i++) {
			Enfant a = fbd.getEnfant(i);
			System.out.println(a.toString());
			Iterator<LocationBR> locBr = a.getLocBr().iterator();
			Iterator<LocationQR> locQr = a.getLocQr().iterator();
			while(locBr.hasNext()) {
				System.out.println("    "  + locBr.next().toString());
			}
			while(locQr.hasNext()) {
				System.out.println("    "  + locQr.next().toString());
			}
		}
	}
	
	static void testCat(FacadeBD fbd) {
		System.out.println("Catalogue = ");
		Iterator<Film> catalogue = fbd.getCatalogueGlobal().iterator();
		while(catalogue.hasNext()) {
			System.out.println(catalogue.next());
		}
	}
	
	static void testUpdCred(FacadeBD fbd) {//echec
		System.out.println("Update credis = ");
		Abonne a = fbd.getAbonne(3);
		System.out.println(a.toString());
		System.out.println("Mise a jour... "+fbd.updCredit(a, 666));
		a = fbd.getAbonne(3);
		System.out.println(a.toString());
	}
	
	static void testNewAbo(FacadeBD fbd) {
		System.out.println("Add abonne = ");
		Abonne a = new Abonne(0,"Personne","Commun","adr@mail.pays","1 rue de l'avenue",123,BigInteger.valueOf(1234567981));
		
		if(fbd.newPersonne(a)) {
			System.out.println("insertion personne ok");
		}
		if(fbd.newAbonne(a)) {
			System.out.println("insertion abonne ok, id="+a.getId());
		}
	}
	
	static void testNewEnf(FacadeBD fbd) {
		System.out.println("Add enfant = ");
		Enfant e = new Enfant(0,"Gamin","Bruillant","jeune@spam.vc","42 avenu du chemain",123,BigInteger.valueOf(3210),1,new HashSet<Tag>(),1);
		//Enfant(int id, String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb,int idParent, HashSet<Tag> rest,int nbMax) {
		if(fbd.newPersonne(e)) {
			System.out.println("insertion personne ok");
		}
		if(fbd.newEnfant(e)) {
			System.out.println("insertion enfant ok, id="+e.getId());
		}
	}
	
	static void testLocBR(FacadeBD fbd) {
		System.out.println("Add loc br = ");
		Abonne a = fbd.getAbonne(2);
		
		//BluRay br, Personne p
		
		BluRay b = a.getLocBr().iterator().next().getBR();
		LocationBR l = new LocationBR(b,a);
		
		System.out.println(a.toString());
		Iterator<LocationBR> locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
		fbd.newLocation(l);
		
		System.out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
		fbd.delLocation(l);
		
		System.out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
	}
	
	static void test(FacadeBD fbd) {
		
	}
	
	public static void main(String[] args) {
		FacadeBD fbd = new FacadeBD();
		testLocBR(fbd);
		
	}

}
