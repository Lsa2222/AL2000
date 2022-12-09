package fc;

import java.util.HashSet;

public class CatalogueLocal {
HashSet<Film> film = new HashSet<>();
HashSet<BluRay> br = new HashSet<>();
static boolean exist = false;
static CatalogueLocal c;

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
public boolean dispo(Film f) {
	for (BluRay b : this.br) {
		if(b.film==f) {
			return true;
		}
	}
	return false;
}

public BluRay getBr(Film f) {
	for (BluRay b : this.br) {
		if(b.film==f) {
			return b;
		}
	}
	return null;
}


public void out(BluRay b) {
	this.br.remove(b);
}

public void setBr(BluRay b) {
	this.br.add(b);
}











}