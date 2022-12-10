package externe;

import ui.*;
import fc.*;
import BDD.*;

public class Main {
	   public static void main(String[] args) {
		   
		   fenetre f = new fenetre();
		   Technicien t=new Technicien();
		   CatalogueLocal c=new CatalogueLocal();
		   t.update_Catalogue_1(c);
		   
		   FacadeTLI fac = new FacadeTLI();
		   
//		   FacadeBD bd = new FacadeBD();
//		   f.fenetre_origine(false);
		   

	   }
}