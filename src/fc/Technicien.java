package fc;

import java.util.Arrays;
import java.util.HashSet;

public class Technicien {
	HashSet l1;

public Technicien() {
	String d1="La Seconde Guerre mondiale vient de s'achever. À New York, le « parrain » Don Corleone, l'un des chefs respectés de la mafia, se sent vieillir. Il refuse de s'adapter à son temps et de se lancer, comme ses pairs, dans le trafic de drogue. Une frilosité qui entrave la bonne marche des affaires des autres « familles » et qui lui vaut d'être la cible d'un attentat. Don Corleone survit à ses blessures, mais reste très diminué. Mike, son plus jeune fils, qui jusque-là se tenait à l'écart des affaires de son père, devient le plus dévoué de ses héritiers. Plus efficace que ses frères, Sonny et Fredo, il venge son père et organise l'élimination de ses adversaires…";
	String d2="En 1947, Andy Dufresne, un jeune banquier, est condamné à la prison à vie pour le meurtre de sa femme et de son amant. Ayant beau clamer son innocence, il est emprisonné à Shawshank, le pénitencier le plus sévère de l'État du Maine. Il y fait la rencontre de Red, un noir désabusé, détenu depuis vingt ans. Commence alors une grande histoire d'amitié entre les deux hommes…";
	String d3="Évocation des années de guerre d’Oskar Schindler, fils d’industriel d’origine autrichienne rentré à Cracovie en 1939 avec les troupes allemandes. Il va, tout au long de la guerre, protéger des juifs en les faisant travailler dans sa fabrique et en 1944 sauver 800 hommes et 300 femmes du camp d’extermination d’Auschwitz‐Birkenau.\n";
	String d4="";
	String d5="";
	String d6="";
	String d7="";
	String d8="";
	String d9="";
	String d10="";
	
	HashSet<Tag> t1 = new HashSet<>();	t1.add(Tag.ACTION);t1.add(Tag.POLICIER);
	HashSet<Tag> t2 = new HashSet<>();	t2.add(Tag.PEGI16);
	HashSet<Tag> t3 = new HashSet<>();	t3.add(Tag.WORLDWAR); t3.add(Tag.PEGI16);
	HashSet<Tag> t4 = new HashSet<>();
	HashSet<Tag> t5 = new HashSet<>();
	HashSet<Tag> t6 = new HashSet<>();
	HashSet<Tag> t7 = new HashSet<>();
	HashSet<Tag> t8 = new HashSet<>();
	HashSet<Tag> t9 = new HashSet<>();
	HashSet<Tag> t10 = new HashSet<>();
	
	Film f1= new Film(1,"Le Parrain","Francis Ford Coppola",d1,t1,"data/leparrain.jpg");
	Film f2= new Film(1,"Les Évadés","Frank Darabont",d2,t2,"data/lesevades.jpg");
	Film f3= new Film(1,"La Liste de Schindler","Steven Spielberg",d3,t3,"data/.jpg");
	Film f4= new Film(1,"","",d4,t4,"data/.jpg");
	Film f5= new Film(1,"","",d5,t5,"data/.jpg");
	Film f6= new Film(1,"","",d6,t6,"data/.jpg");
	Film f7= new Film(1,"","",d7,t7,"data/.jpg");
	Film f8= new Film(1,"","",d8,t8,"data/.jpg");
	Film f9= new Film(1,"","",d9,t9,"data/.jpg");
	Film f10= new Film(1,"","",d10,t10,"data/.jpg");
	this.l1 = new HashSet<Film>(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10));
}






	
public void update_Catalogue_1(CatalogueLocal c) {
	c.setf(l1);	
}

}

