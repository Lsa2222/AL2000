package BDD;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.BluRay;
import fc.Enfant;
import fc.Film;
import fc.LocationBR;
import fc.LocationQR;
import fc.Tag;

public class TestBD {
	
	static void testAbo(PrintStream out) {//echec locations
		System.out.println("Abonne = ");
		for(int i = 1 ;i<6;i++) {
			Abonne a = fbd.getAbonne(i);
			System.out.println(a.toString());
			Iterator<LocationBR> locBr = a.getLocBr().iterator();
			Iterator<LocationQR> locQr = a.getLocQr().iterator();
			Iterator<Enfant> enfs = a.getEnfants().iterator();
			while(enfs.hasNext()) {
				System.out.println("enf : "  + enfs.next().toString());
			}
			while(locBr.hasNext()) {
				System.out.println("BR  : "  + locBr.next().toString());
			}
			while(locQr.hasNext()) {
				System.out.println("QR  : "  + locQr.next().toString());
			}
		}
	}
	static void testEnf(PrintStream out) {
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
	static void testCat(PrintStream out) {
		System.out.println("Catalogue = ");
		Iterator<Film> catalogue = fbd.getCatalogueGlobal().iterator();
		while(catalogue.hasNext()) {
			System.out.println(catalogue.next());
		}
	}
	static void testUpdCred(PrintStream out) {//echec
		System.out.println("Update credis = ");
		Abonne a = fbd.getAbonne(3);
		System.out.println(a.toString());
		System.out.println("Mise a jour... "+fbd.updCredit(a, 666));
		a = fbd.getAbonne(3);
		System.out.println(a.toString());
	}
	static void testNewAbo(PrintStream out) {
		System.out.println("Add abonne = ");
		Abonne a = new Abonne("Personne","Commun","adr@mail.pays","1 rue de l'avenue",123,BigInteger.valueOf(1234567981));
		if(fbd.newAbonne(a)) {
			System.out.println("insertion abonne ok, id="+a.getId());
		}
	}
	static void testNewEnf(PrintStream out) {
		System.out.println("Add enfant = ");
		Enfant e = new Enfant("Gamin","Bruillant","jeune@spam.vc","42 avenu du chemain",123,BigInteger.valueOf(3210),1,new HashSet<Tag>(),1);
		//Enfant(int id, String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb,int idParent, HashSet<Tag> rest,int nbMax) {
		if(fbd.newEnfant(e)) {
			System.out.println("insertion enfant ok, id="+e.getId());
		}
	}
	static void testLocBR(PrintStream out) {
		System.out.println("Add loc br = ");
		Abonne a = fbd.getAbonne(1);
		
		//BluRay br, Personne p
		
		BluRay b = new BluRay(6,null,true);
		LocationBR l = new LocationBR(b,a);
		
		System.out.println(a.toString());
		Iterator<LocationBR> locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
		if(fbd.newLocation(l)) {
			System.out.println("Location reussie !");
		} else {
			System.out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		System.out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
		if(fbd.delLocation(l)) {
			System.out.println("Suppression reussie !");
		} else {
			System.out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		System.out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			System.out.println("    "  + locBr.next().toString());
		}
		
	}
	static void testLocQR(PrintStream out) {
		System.out.println("Add loc br = ");
		Abonne a = fbd.getAbonne(1);
		
		//BluRay br, Personne p
		
		Film f = new Film(4,"","","",null,"");
		LocationQR l = new LocationQR(f,a);
		
		System.out.println(a.toString());
		Iterator<LocationQR> locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			System.out.println("    "  + locQr.next().toString());
		}
		
		if(fbd.newLocation(l)) {
			System.out.println("Location reussie !");
		} else {
			System.out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		System.out.println(a.toString());
		locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			System.out.println("    "  + locQr.next().toString());
		}
		
		fbd.delLocation(l);
		a = fbd.getAbonne(1);
		
		System.out.println(a.toString());
		locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			System.out.println("    "  + locQr.next().toString());
		}
		
	}
	static void test() {
		
	}
	
	static FacadeBD fbd;
	
	public static void main(String[] args) {
		
		
		PrintStream out = System.out;
		fbd = new FacadeBD();
		
		DataBase db = new DataBase(fbd.conn);
		db.resetBD();
		testAbo(out);
		testEnf(out);
		testCat(out);
		testUpdCred(out);
		testNewAbo(out);
		testNewEnf(out);
		for(int i=0;i<10;i++) {
			
		}
		testLocBR(out);
		testLocQR(out);
	}

}
