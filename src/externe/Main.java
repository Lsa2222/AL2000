package externe;

import ui.*;
import fc.*;

import java.math.BigInteger;

import BDD.*;

public class Main {
	   public static void main(String[] args) throws Exception {
		   
		   Technicien t=new Technicien();
		   CatalogueLocal c=new CatalogueLocal();
		   t.update_Catalogue_1(c);
		   
		   FacadeBD bd =  FacadeBD.creer();
		   FacadeTLI tli = new FacadeTLI();	   
		   Test test = new Test(bd,tli,c);
		   test.test1();
		   test.test2();
		   
		   
		   
		   
//		   f.fenetre_origine(false);
		   


	   }
}