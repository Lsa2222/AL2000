package fc;

import java.util.Arrays;
import java.util.HashSet;

public class Technicien {
	HashSet l1;
	HashSet lb1;


public Technicien() {
	String d1="La Seconde Guerre mondiale vient de s'achever. À New York, le « parrain » Don Corleone, l'un des chefs respectés de la mafia, se sent vieillir. Il refuse de s'adapter à son temps et de se lancer, comme ses pairs, dans le trafic de drogue. Une frilosité qui entrave la bonne marche des affaires des autres « familles » et qui lui vaut d'être la cible d'un attentat. Don Corleone survit à ses blessures, mais reste très diminué. Mike, son plus jeune fils, qui jusque-là se tenait à l'écart des affaires de son père, devient le plus dévoué de ses héritiers. Plus efficace que ses frères, Sonny et Fredo, il venge son père et organise l'élimination de ses adversaires…";
	String d2="En 1947, Andy Dufresne, un jeune banquier, est condamné à la prison à vie pour le meurtre de sa femme et de son amant. Ayant beau clamer son innocence, il est emprisonné à Shawshank, le pénitencier le plus sévère de l'État du Maine. Il y fait la rencontre de Red, un noir désabusé, détenu depuis vingt ans. Commence alors une grande histoire d'amitié entre les deux hommes…";
	String d3="Évocation des années de guerre d’Oskar Schindler, fils d’industriel d’origine autrichienne rentré à Cracovie en 1939 avec les troupes allemandes. Il va, tout au long de la guerre, protéger des juifs en les faisant travailler dans sa fabrique et en 1944 sauver 800 hommes et 300 femmes du camp d’extermination d’Auschwitz‐Birkenau.\n";
	String d4="Toute la famille de Ki-taek est au chômage. Elle s’intéresse particulièrement au train de vie de la richissime famille Park. Mais un incident se produit et les deux familles se retrouvent mêlées, sans le savoir, à une bien étrange histoire…\n";
	String d5="L’odyssée sanglante et burlesque de petits malfrats dans la jungle d’Hollywood : Deux petits tueurs, un dangereux gangster marié à une camée, un boxeur roublard, des prêteurs sur gages sadiques, un caïd élégant et dévoué, un dealer bon mari et deux tourtereaux à la gâchette facile.\n";
	String d6="La suite de Batman Begins, The Dark Knight, le réalisateur Christopher Nolan et l'acteur Christian Bale, qui endosse à nouveau le rôle de Batman/Bruce Wayne dans sa guerre permanente contre le crime. Avec l'aide du Lieutenant de Police Jim Gordon et du Procureur Harvey Dent, Batman entreprend de démanteler définitivement les organisations criminelles de Gotham. L'association s'avère efficace, mais le trio se heurte bientôt à un nouveau génie du crime, plus connu sous le nom du Joker, qui va plonger Gotham dans l'anarchie et pousser Batman à la limite entre héros et assassin. Heath Ledger interprète Le Joker : le méchant suprême et Aaron Eckhart joue le rôle de Dent. Maggie Gyllenhaal complète le casting en tant que Rachel Dawes. De retour après Batman Begins, Gary Oldman est à nouveau Gordon, Michael Caine interprète Alfred, et Morgan Freeman est Lucius Fox.\n";
	String d7="Dom Cobb est un voleur expérimenté, le meilleur dans l'art dangereux de l'extraction, voler les secrets les plus intimes enfouis au plus profond du subconscient durant une phase de rêve, lorsque l'esprit est le plus vulnérable. Les capacités de Cobb ont fait des envieux dans le monde tourmenté de l'espionnage industriel alors qu'il devient fugitif en perdant tout ce qu'il a un jour aimé. Une chance de se racheter lui est alors offerte. Une ultime mission grâce à laquelle il pourrait retrouver sa vie passée mais uniquement s'il parvient à accomplir l'impossible inception.\n";
	String d8="Paul Edgecomb, pensionnaire centenaire d’une maison de retraite, est hanté par ses souvenirs. Gardien-chef du pénitencier de Cold Mountain en 1935, il était chargé de veiller au bon déroulement des exécutions des peines capitales, en s’efforçant d’adoucir les derniers moments des condamnés. Parmi eux, se trouvait un colosse du nom de John Coffey, accusé du viol et du meurtre de deux fillettes. Intrigué par cet homme candide et timide, aux dons magiques, Edgecomb va tisser avec lui des liens très forts.\n";
	String d9="Les armées de Sauron ont attaqué Minas Tirith, la capitale de Gondor. Jamais ce royaume autrefois puissant n'a eu autant besoin de son roi. Mais Aragorn trouvera-t-il en lui la volonté d'accomplir sa destinée ? Tandis que Gandalf tente de soutenir les forces brisées de Gondor, Théoden exhorte les guerriers de Rohan à se joindre au combat. Mais malgré leur courage et leur loyauté, les armées des Hommes ne sont pas de taille à lutter contre les innombrables légions ennemies qui s'abattent sur la Terre du Milieu... Chaque victoire se paye d'immenses sacrifices. Pendant ce temps et malgré ses pertes, la Communauté poursuit sa quête, ses membres faisant tout pour détourner l'attention de Sauron afin de donner à Frodon une chance d'accomplir sa mission. Voyageant à travers les terres ennemies, ce dernier doit s'appuyer sur Sam et Gollum, tandis que l'Anneau continue de le tenter ...\n";
	String d10="Forrest Gump est le symbole d’une époque, un candide dans une Amérique qui a perdu son innocence. Merveilleusement interprété par Tom Hanks, Forrest vit une série d’aventures, de l’état d’handicapé physique à celui de star du football, de héros du Vietnam au roi de la crevette, des honneurs de la Maison Blanche au bonheur d’une grande histoire d’amour. Son cœur dépasse les limites de son Q.I.\n";	
	HashSet<Tag> t1 = new HashSet<>();	t1.add(Tag.ACTION);t1.add(Tag.POLICIER);
	HashSet<Tag> t2 = new HashSet<>();	t2.add(Tag.PEGI16);
	HashSet<Tag> t3 = new HashSet<>();	t3.add(Tag.WORLDWAR); t3.add(Tag.PEGI16);
	HashSet<Tag> t4 = new HashSet<>();	t4.add(Tag.PEGI16);
	HashSet<Tag> t5 = new HashSet<>();	t5.add(Tag.PEGI16);
	HashSet<Tag> t6 = new HashSet<>();	t6.add(Tag.ACTION);
	HashSet<Tag> t7 = new HashSet<>();	t7.add(Tag.ACTION);
	HashSet<Tag> t8 = new HashSet<>();
	HashSet<Tag> t9 = new HashSet<>();	t9.add(Tag.FANTASY);
	HashSet<Tag> t10 = new HashSet<>();
	
	Film f1= new Film(1,"Le Parrain","Francis Ford Coppola",d1,t1,"data/leparrain.jpg");
	Film f2= new Film(1,"Les Évadés","Frank Darabont",d2,t2,"data/lesevades.jpg");
	Film f3= new Film(1,"La Liste de Schindler","Steven Spielberg",d3,t3,"data/lalistedeschindler.jpg");
	Film f4= new Film(1,"Parasite","Bong Joon-ho",d4,t4,"data/parasite.jpg");
	Film f5= new Film(1,"Pulp Fiction","Quentin Tarantino",d5,t5,"data/.jpg");
	Film f6= new Film(1,"The Dark Knight : Le Chevalier noir","Christopher Nolan",d6,t6,"data/.jpg");
	Film f7= new Film(1,"Inception","Christopher Nolan",d7,t7,"data/inception.jpg");
	Film f8= new Film(1,"La Ligne verte","Frank Darabont",d8,t8,"data/laligneverte.jpg");
	Film f9= new Film(1,"Le Seigneur des anneaux : Le Retour du roi","Peter Jackson",d9,t9,"data/leseigneurdesanneaux:leretourduroi.jpg");
	Film f10= new Film(1,"Forrest Gump","Robert Zemeckis",d10,t10,"data/forrestgump.jpg");
	
	this.l1 = new HashSet<Film>(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10));
	
	BluRay b1 = new BluRay(1,f1,true);
	BluRay b2 = new BluRay(2,f1,true);
	BluRay b3 = new BluRay(3,f2,true);
	BluRay b4 = new BluRay(4,f2,true);
	BluRay b5 = new BluRay(5,f3,true);
	BluRay b6 = new BluRay(6,f4,true);
	BluRay b7 = new BluRay(7,f5,true);
	BluRay b8 = new BluRay(8,f6,true);
	BluRay b9 = new BluRay(9,f7,true);
	BluRay b10 = new BluRay(10,f1,true);
	
	this.lb1 = new HashSet<BluRay>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10));
}
	
public void update_Catalogue_1(CatalogueLocal c) {
	c.setf(l1);	
	c.br=lb1;
}

}

