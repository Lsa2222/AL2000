package fc;

import java.util.HashSet;
import java.util.Iterator;

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
	
		public String toString() {
		String infos = id + " " + titre + " " + reali + " " + descr + " " + image;
		Iterator<Tag> it = restr.iterator();
		while(it.hasNext()) {
			infos+=" "+it.next().toString();
		}
		return infos;
	}
	
	public int getId() {
		return id;
	}
	public String getTitre() {
		return titre;
	}
	public String getRealisateur() {
		return reali;
	}
	public HashSet<Tag> getGenre() {
		return restr;
	}
	public String getResumer() {
		return descr;
	}
	public String getImage() {
		return image;
	}
}
