package fc;

import java.util.HashSet;

public class CatalogueLocal {
HashSet<Film> film = new HashSet<>();
HashSet<BluRay> br = new HashSet<>();
static boolean exist = false;
static CatalogueLocal c;
HashSet<Reservation> reservation = new HashSet<>(); //tout les film reservé

public static CatalogueLocal creer(){
	if(!exist) {
		exist = true;
		c = new CatalogueLocal();
		return c;
	} else {
		return c;
	}
}

public HashSet<Film> getf() {
	return c.film;
}	
public void setf(HashSet<Film> f) {
	this.film=f;
}	

public void add(Film f) {
	c.film.add(f);
}
public void del(Film f) {
	c.film.remove(f);
}
public boolean dispo(String s) {
	for (BluRay b : this.br) {
		if(b.film.titre==s && b.estBon) {
			return true;
		}
	}
	return false;
}

public BluRay getBr(Film f) {
	for (BluRay b : this.br) {
		if(b.film==f  && b.estBon) {
			return b;
		}
	}
	return null;
}

public BluRay getBr(String f) {
	for (BluRay b : this.br) {
		if(b.film.titre==f && b.estBon) {
			return b;
		}
	}
	return null;
}

//renvoi le film associé au nom donné
public Film stof(String s) {
	for (Film f : this.film) {
		if(f.titre==s) {
			return f;
		}
	}
	return null;
}

public void out(BluRay b) {
	this.br.remove(b);
}

public void setBr(BluRay b) {
	for (Reservation r : this.reservation) {
		if(r.f==b.film) {
			r.dispo(b);
			return;
		}
	}
	this.br.add(b);
}

public void reserve(String s, Abonne a) {
	for (Film f : this.film) {
		if(f.titre==s) {
			Reservation r = new Reservation(a,f);
			this.reservation.add(r);
		}
	}	
}

//retourn le bluray d'une reservation FINI de l'abonne a
public BluRay getreserve(Abonne a) {
	Reservation r = null;
	for (Reservation res : this.reservation) {
		if(res.a==a && res.b!=null) {
			r=res;
		}
	}
	if(r==null) {
		return null;
	}
	return r.b;
}









}