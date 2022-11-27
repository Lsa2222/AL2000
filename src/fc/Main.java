package fc;

import java.util.HashSet;

public class Main {
	   public static void main(String[] args) {
		   
		 HashSet<Tag> t1 = new HashSet<>();
		 HashSet<Tag> t2 = new HashSet<>();
		 HashSet<Tag> t3 = new HashSet<>();

		 t1.add(Tag.PEGI16);
		 t2.add(Tag.PEGI18);
		 t3.add(Tag.PEGI16);
		 t3.add(Tag.PEGI18);
		 
		 int cb=123456789;
	     
		 
	     Abonne a = new Abonne(0, null, null, null, null, 20, cb);
	     Film f1 =new Film(0, null, null, null, t3, null);
	     
//	     LocationBR loc1=new LocationBR(br1,a);   
//	     loc1.enregistrer();
//	     LocationQR loc2=new LocationQR(f1, a);
//	     loc2.enregistrer();
//	     System.out.print(a.getCredit()+"\n");
	     LocationQR loc2=new LocationQR(f1, a);
	     loc2.enregistrer();
	     loc2.activer();
//	     System.out.print(a.getCredit()+"\n");	    
//	     LocationBR loc4=new LocationBR(br2,a);   
//	     loc4.enregistrer();
//	     
//	     System.out.print("location br: "+a.getLocBr()+"location qr :"+a.getLocQr()+"\n");
//	     
//	     loc1.rendre();
//	     System.out.print(a.getCredit());
	     

	     
	   }
	   }