package BDD;

import java.io.IOException;
import java.io.OutputStream;
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

class VoidOutputStream extends OutputStream{

	@Override
	public void write(int b) throws IOException {}
	
}

public class TestBD {
	
	static void testAbo(PrintStream out) {//echec locations
		out.println("Abonne = ");
		for(int i = 1 ;i<6;i++) {
			Abonne a = fbd.getAbonne(i);
			out.println(a.toString());
			Iterator<LocationBR> locBr = a.getLocBr().iterator();
			Iterator<LocationQR> locQr = a.getLocQr().iterator();
			Iterator<Enfant> enfs = a.getEnfants().iterator();
			while(enfs.hasNext()) {
				out.println("enf : "  + enfs.next().toString());
			}
			while(locBr.hasNext()) {
				out.println("BR  : "  + locBr.next().toString());
			}
			while(locQr.hasNext()) {
				out.println("QR  : "  + locQr.next().toString());
			}
		}
	}
	static void testEnf(PrintStream out) {
		out.println("Enfant = ");
		for(int i = 2 ;i<6;i++) {
			Enfant a = fbd.getEnfant(i);
			out.println(a.toString());
			Iterator<LocationBR> locBr = a.getLocBr().iterator();
			Iterator<LocationQR> locQr = a.getLocQr().iterator();
			while(locBr.hasNext()) {
				out.println("    "  + locBr.next().toString());
			}
			while(locQr.hasNext()) {
				out.println("    "  + locQr.next().toString());
			}
		}
	}
	static void testCat(PrintStream out) {
		out.println("Catalogue = ");
		Iterator<Film> catalogue = fbd.getCatalogueGlobal().iterator();
		while(catalogue.hasNext()) {
			out.println(catalogue.next());
		}
	}
	static void testUpdCred(PrintStream out) {//echec
		out.println("Update credis = ");
		Abonne a = fbd.getAbonne(3);
		out.println(a.toString());
		out.println("Mise a jour... "+fbd.updCredit(a, 666));
		a = fbd.getAbonne(3);
		out.println(a.toString());
	}
	static void testNewAbo(PrintStream out) {
		out.println("Add abonne = ");
		Abonne a = new Abonne("Personne","Commun","adr@mail.pays","1 rue de l'avenue",123,BigInteger.valueOf(1234567981));
		if(fbd.newAbonne(a)) {
			out.println("insertion abonne ok, id="+a.getId());
		}
	}
	static void testNewEnf(PrintStream out) {
		out.println("Add enfant = ");
		Enfant e = new Enfant("Gamin","Bruillant","jeune@spam.vc","42 avenu du chemain",123,BigInteger.valueOf(3210),1,new HashSet<Tag>(),1);
		//Enfant(int id, String prenom, String nom, String adrMail, String adrPhys, int credit, BigInteger cb,int idParent, HashSet<Tag> rest,int nbMax) {
		if(fbd.newEnfant(e)) {
			out.println("insertion enfant ok, id="+e.getId());
		}
	}
	static void testLocBR(PrintStream out) {
		out.println("Add loc br = ");
		Abonne a = fbd.getAbonne(1);
		
		//BluRay br, Personne p
		
		BluRay b = new BluRay(6,null,true);
		LocationBR l = new LocationBR(b,a);
		
		out.println(a.toString());
		Iterator<LocationBR> locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			out.println("    "  + locBr.next().toString());
		}
		
		if(fbd.newLocation(l)) {
			out.println("Location reussie !");
		} else {
			out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			out.println("    "  + locBr.next().toString());
		}
		
		if(fbd.delLocation(l)) {
			out.println("Suppression reussie !");
		} else {
			out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		out.println(a.toString());
		locBr = a.getLocBr().iterator();
		while(locBr.hasNext()) {
			out.println("    "  + locBr.next().toString());
		}
		
	}
	static void testLocQR(PrintStream out) {
		out.println("Add loc br = ");
		Abonne a = fbd.getAbonne(1);
		
		//BluRay br, Personne p
		
		Film f = new Film(4,"","","",null,"");
		LocationQR l = new LocationQR(f,a);
		
		out.println(a.toString());
		Iterator<LocationQR> locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			out.println("    "  + locQr.next().toString());
		}
		
		if(fbd.newLocation(l)) {
			out.println("Location reussie !");
		} else {
			out.println("echec");
			return;
		}
		a = fbd.getAbonne(1);
		
		out.println(a.toString());
		locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			out.println("    "  + locQr.next().toString());
		}
		
		fbd.delLocation(l);
		a = fbd.getAbonne(1);
		
		out.println(a.toString());
		locQr = a.getLocQr().iterator();
		while(locQr.hasNext()) {
			out.println("    "  + locQr.next().toString());
		}
		
	}
	static void test() {
		
	}
	
	static FacadeBD fbd;
	
	public static void main(String[] args) {
		
		
		PrintStream out = System.out;
		PrintStream voi = new PrintStream(new VoidOutputStream());
		
		fbd = FacadeBD.creer();
		
		DataBase db = new DataBase(fbd.conn);
		db.resetBD();
		testNewAbo(out);
		testNewEnf(out);
		testAbo(out);
		testEnf(out);
		testCat(out);
		testUpdCred(out);
		testLocBR(out);
		testLocQR(out);
		for(int i=0;i<10;i++) {
			testLocBR(voi);
			out.println("location num "+(i*2+1)+"/20 (br)");
			testLocQR(voi);
			out.println("location num "+(i*2+2)+"/20 (qr)");
		}
		
	}

}
