package fc;

import java.math.BigInteger;
import java.util.HashSet;

import BDD.FacadeBD;

public class Test {
	
	   public Test(FacadeBD bd, FacadeTLI tli, CatalogueLocal c) {
		   this.bd=bd;
		   this.t=tli;
		   this.c=c;
		   this.al = AdaptAl2000.creer();
		   System.out.print("------debut test------\n");
		   BigInteger cb =new BigInteger("55555");
		   this.a = new Abonne("jo","bes", "gmail", "gre", 10, cb);
	}
	   FacadeBD bd;
	   FacadeTLI t;
	   CatalogueLocal c;
	   Abonne a;
	   AdaptAl2000 al;
	   
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
		   asert(t.a.getPrenom().equals("Mario"),"1 4");
		   
		   System.out.print("------test 1 ok------\n");

		 
	   }
	   
	   //test location
	   public void test2() throws Exception {
		   
		   BluRay br1 =(BluRay) c.br.toArray()[0];
		   BluRay br2 =(BluRay) c.br.toArray()[1];
		   BluRay br3 =(BluRay) c.br.toArray()[2];
		   BluRay br4 =(BluRay) c.br.toArray()[3];

		   Film film1 =br1.film;
		   Film film2 =br2.film;
		   Film film3 =br3.film;
		   Film film4 =br4.film;
		   

		   
		   t.louer_Br(film1.titre);
		   LocationBR loc = (LocationBR)t.a.getLocBr().toArray()[0];
		   
		   //on s'assure que le bluray a bien été ajouté a abonné
		   asert(loc.br.film.titre == film1.titre, "2 1");
		   
		   //on rellou 2 autre filme
		   t.louer_Br(film2.titre);
		   t.louer_Br(film3.titre);

		   //si on relou une 4eme fois, on n'a pas le droit
		   asert(t.louer_Br(film4.titre) == 3,"2 2");
		   t.deconecte();

		   //un autre abboné tente de louer un film qui n'est plus disponible en br.
		   al.setCarte(40);
		   t.connection();
		   t.reserve(film1.titre);
		   t.deconecte();
		   
		   //le 1er abo se connecte et rend le 1er br
		   al.setCarte(50);
		   al.setLireBr(br1);
		   t.connection();
		   System.out.print(t.rendre());
		   
		   

		   
		   
		   
		   
		   
		   System.out.print("------test 2 ok------");

	   }
	   
	   //test 
	   
	   
	   
	   

	   
}
