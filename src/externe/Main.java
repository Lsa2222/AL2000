package externe;

import ui.*;
import fc.*;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Iterator;

import BDD.*;

public class Main {
	   public static void main(String[] args) throws Exception {
		   
		   Technicien t=new Technicien();
		   CatalogueLocal c=new CatalogueLocal();
		   t.update_Catalogue_1(c);
		   
		   FacadeBD bd =  FacadeBD.creer();
		   bd.reset();
		   FacadeTLI tli = new FacadeTLI();	   
//		   Test test = new Test(bd,tli,c);
//		   test.test1();
//		   test.test2();
		   
//		   testLocBR(System.out);
		   fenetre f = new fenetre();
		   f.fenetre_origine(false);
		   

	   }	  
//	   
//	   static void testLocBR(PrintStream out) {
//		   FacadeBD fbd = FacadeBD.creer();
//	        out.println("Add loc br = ");
//	        Abonne a = fbd.getAbonne(1);
//	        
//	        //BluRay br, Personne p
//	        
//	        BluRay b = new BluRay(6,null,true);
//	        LocationBR l = new LocationBR(b,a);
//	        
//	        out.println(a.toString());
//	        Iterator<LocationBR> locBr = a.getLocBr().iterator();
//	        while(locBr.hasNext()) {
//	            out.println("    "  + locBr.next().toString());
//	        }
//	        
//	        if(fbd.newLocation(l)) {
//	            out.println("Location reussie !");
//	        } else {
//	            out.println("echec");
//	            return;
//	        }
//	        a = fbd.getAbonne(1);
//	        
//	        out.println(a.toString());
//	        locBr = a.getLocBr().iterator();
//	        while(locBr.hasNext()) {
//	            out.println("    "  + locBr.next().toString());
//	        }
//	        
//	        if(fbd.delLocation(l)) {
//	            out.println("Suppression reussie !");
//	        } else {
//	            out.println("echec");
//	            return;
//	        }
//	        a = fbd.getAbonne(1);
//	        
//	        out.println(a.toString());
//	        locBr = a.getLocBr().iterator();
//	        while(locBr.hasNext()) {
//	            out.println("    "  + locBr.next().toString());
//	        }
}