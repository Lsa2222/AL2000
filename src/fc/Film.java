package fc;

import java.util.HashSet;

public class Film {
	public Film(int id, String titre, String reali, String descr, HashSet<Tag> restr, String image) {
		this.id = id;
		this.titre = titre;
		this.reali = reali;
		this.descr = descr;
		this.restr = restr;
		this.image = image;
	}
	private int id;
	public String titre;
	public String reali;
	public String descr;
	public HashSet<Tag> restr = new HashSet<>();
	public String image;
	
	public int getId() {
		return id;
	}
	public String getTitre() {
		return titre;
	}
	public String getRealisateur() {
		return reali;
	}
	public String getGenre() {
		return null;//TODO
	}
	public String getResumer() {
		return descr;
	}
}