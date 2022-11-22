package fc;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Main {
	   public static void main(String[] args) {
		   
		 HashSet<Tag> t1 = new HashSet<>();
		 HashSet<Tag> t2 = new HashSet<>();
		 HashSet<Tag> t3 = new HashSet<>();

		 t1.add(Tag.PEGI16);
		 t2.add(Tag.PEGI18);
		 t3.add(Tag.PEGI16);
		 t3.add(Tag.PEGI18);
		 
		 long cb=1234567891234567L;
	     Abonne a=new Abonne("pierre", "machin", "ex@gmail.com", "rue", t1, 15, cb);  
	     
	     Film f1=new Film(1, "interstelar", "nolan", "blablabla", t2);
	     Film f2=new Film(2, "porno", "nolan", "blablabla", t3);
	     Film f3=new Film(3, "interstelar", "nolan", "blablabla", t2);
	     
	     BluRay br1=new BluRay(1, f1, true);
	     BluRay br2=new BluRay(2, f2, true);
	     BluRay br3=new BluRay(3, f3, true);
	     
	     LocationBR loc1=new LocationBR(br1,a);   
	     loc1.enregistrer();
	     LocationQR loc2=new LocationQR(f1, a);
	     loc2.enregistrer();
	     System.out.print(a.getCredit()+"\n");
	     LocationQR loc3=new LocationQR(f1, a);
	     loc2.enregistrer();
	     System.out.print(a.getCredit()+"\n");	    
	     LocationBR loc4=new LocationBR(br2,a);   
	     loc4.enregistrer();
	     
	     System.out.print("location br: "+a.getLocBr()+"location qr :"+a.getLocQr()+"\n");
	     
	     loc1.rendre();
	     System.out.print(a.getCredit());
	     

	     
	   }
	   }