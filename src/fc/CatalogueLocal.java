package fc;

import java.util.HashSet;

public class CatalogueLocal {
HashSet<Film> film = new HashSet<>();
static boolean exist = false;
static CatalogueLocal c;

static CatalogueLocal creer(){
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

}