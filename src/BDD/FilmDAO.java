package fc;

import java.util.HashSet;

public class Film {
	public Film(int id, String titre, String reali, String descr, HashSet<Tag> restr) {
		super();
		this.id = id;
		this.titre = titre;
		this.reali = reali;
		this.descr = descr;
		this.restr = restr;
	}
	private int id;
	private String titre;
	private String reali;
	private String descr;
	public HashSet<Tag> restr = new HashSet<>();
	
	
}