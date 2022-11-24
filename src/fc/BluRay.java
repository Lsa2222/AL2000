package fc;

public class BluRay {
	public BluRay(int id, Film film, boolean estBon) {
		this.id = id;
		this.film = film;
		this.estBon = estBon;
	}
	int id;
	Film film;
	boolean estBon;
	
	public int getId() {
		return id;
	}

	public int getFilmId() {
		return film.getId();
	}

	public boolean getEtat() {
		return estBon;
	}
}
