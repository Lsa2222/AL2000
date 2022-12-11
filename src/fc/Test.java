package fc;

import java.math.BigInteger;
import java.util.HashSet;

import BDD.FacadeBD;

public class Test {
	
	   public Test(FacadeBD bd, FacadeTLI tli, CatalogueLocal c) {
		   this.bd=bd;
		   this.t=tli;
		   this.c=c;
		   
		   BigInteger cb =new BigInteger("55555");
		   this.a = new Abonne("jo","bes", "gmail", "gre", 10, cb);
	}
	   FacadeBD bd;
	   FacadeTLI t;
	   CatalogueLocal c;
	   Abonne a;
	   
	   public void asert(boolean b,String s) throws Exception {
		   if(!b) {
			   throw new Exception("erreur lors du test "+s);
		   }
	   }
	   
	   
	   //test abonne
	   public void test1() throws Exception {
		   BigInteger cb = new BigInteger("123456789"); 
		   //pas assez de credit
//		   asert(t.creerAbonne("jean", "deschamp", "gmail","gre", 10, cb) == 2,"1 1");

		   //ca marche
		   asert(t.creerAbonne("jean2", "deschamp", "gmail","gre", 20, cb)==1,"1 2");
		   
		   //on se connect avec la carte creer
		   asert(t.connection() == 1,"1 3");
		   
//		   on verifie si on a recuperer le bon abonne
		   asert(t.a.getNom().equals("jean2"),"1 4");

		   t.deconecte();
		 
	   }
	   
	   //test location
	   public void test2() {
		   
		   t.louer_Br(null)
	   }
	   
	   //test 
	   
	   
	   
	   

	   
}
