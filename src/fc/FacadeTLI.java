package fc;

import java.util.HashSet;

public class FacadeTLI {
	
	Banque banque= Banque.creer();

	public int creerAbonne(String prenom, String nom, String adrMail, String adrPhys, HashSet<Tag> rest, int credit, int cb) {
		if(!banque.debiter(cb, credit)) {
			return(2); 
		}
		Abonne a = new Abonne(prenom,nom,adrMail,adrPhys,rest,credit,cb);
		//res=envoyer a la bd
		int res = 1;
		return res;
	}
	
	
}
