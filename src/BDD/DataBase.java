package BDD;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {
	
	Connection conn;
	
	DataBase(Connection conn){
		this.conn = conn;
	}
	
	void resetBD() {
		drop();
		create();
		data();
	}
	
	private void execStatm(String s) {
		try {
			System.out.println(s);
			conn.prepareStatement(s).execute();
		} catch (SQLException e) {
			//System.err.print(e);
			e.printStackTrace();
		}
	}
	
	void drop() {
		try {
			conn.setAutoCommit(true);
			System.out.println("drop");
			execStatm("ALTER TABLE LesAbonnes "
					+ "DROP PRIMARY KEY CASCADE");
			execStatm("ALTER TABLE LesPersonnes "
					+ "DROP PRIMARY KEY CASCADE");
			
			execStatm("drop table LesEnfants");
			execStatm("drop table HistoriqueLocation");
			execStatm("drop table LesLocationsBR");
			execStatm("drop table LesRestrictions");
			execStatm("drop table LesAbonnes");
			execStatm("drop table TagsFilm");
			execStatm("drop table LesBlueRay");
			execStatm("drop table LesLocationsQR");
			execStatm("drop table Tags");
			execStatm("drop table LesFilms");
			execStatm("drop sequence id_gens");
			
			execStatm("drop table LesPersonnes");
			
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.print("drop table error");
			e.printStackTrace();
		}
	}
	
	void create() {
		try {
			conn.setAutoCommit(true);
			System.out.println("create");
			execStatm("create table LesPersonnes("
					+ "id INT PRIMARY KEY NOT NULL,"
					+ "cb number(23)"
					+ ")");
			execStatm("create table LesAbonnes("
					+ "id integer not null,"
					+ "prenom varchar2(20),"
					+ "nom varchar2(20),"
					+ "credit number(9),"
					+ "adrMail varchar2(40),"
					+ "AdrPhy varchar2 (90),"
					+ "constraint LesAbonnes_Key primary key (id),"
					+ "constraint LesAbonnes_FK foreign key (id) references LesPersonnes (id),"
					+ "constraint LesAbonnes_money check (credit >= 0)"
					+ ")");
			execStatm("create table LesEnfants ("
					+ "idEnfant integer not null,"
					+ "idParent integer not null,"
					+ "constraint LesEnfants_Key primary key (idEnfant),"
					+ "constraint LesEnfants_FKparent foreign key (idParent) references LesAbonnes(id),"
					+ "constraint LesEnfants_FKenfant foreign key (idEnfant) references LesAbonnes(id)"
					+ ")");
			execStatm("create table LesFilms("
					+ "    noFilm integer not null,"
					+ "    titre varchar2(100),"
					+ "    realisateur varchar2(20),"
					+ "    image varchar2(200),"
					+ "    resumer varchar2(500),"
					+ "    constraint LesFilms_key primary key (noFilm)"
					+ ")");
			execStatm("create table Tags("
					+ "	tag varchar2(50),"
					+ "	constraint Tag primary key (tag)"
					+ ")"
					+ "");
			execStatm("create table TagsFilm("
					+ "    tag varchar2(20),"
					+ "    noFilm integer not null,"
					+ "    constraint TagsFilm_key primary key (tag, noFilm),"
					+ "    constraint TagsFilm_Fktag foreign key (tag) references Tags (tag),"
					+ "    constraint TagsFilm_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)"
					+ ")");
			execStatm("create table LesRestrictions("
					+ "	id integer not null,"
					+ "	tag varchar2(20),"
					+ "	constraint LesRestrictions_Key primary key (id, tag),"
					+ "	constraint LesRestrictions_Fkid foreign key (id) references LesPersonnes (id),"
					+ "	constraint LesRestrictions_Fktag foreign key (tag) references Tags (tag)"
					+ ")"
					+ "");
			execStatm("create table LesLocationsQR("
					+ "    id integer not null,"
					+ "    noFilm integer not null,"
					+ "    dateActivation date,"
					+ "    dateAchat date,"
					+ "    constraint LesLocationsQR_Key primary key (id, noFilm, dateActivation),"
					+ "    constraint LesLocationsQR_FKid foreign key (id) references LesPersonnes (id),"
					+ "    constraint LesLocationsQR_FKnoFIlm foreign key (noFilm) references LesFilms (noFilm)"
					+ ")");
			execStatm("create table LesBlueRay("
					+ "    idBR integer not null,"
					+ "    noFilm integer not null,"
					+ "    etat varchar2(4),"
					+ "    constraint LesBlueRay_Key primary key (idBR),"
					+ "    constraint LesBlueRay_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)"
					+ ")");
			execStatm("create table LesLocationsBR("
					+ "    id integer not null,"
					+ "    idBR integer not null,"
					+ "    dateDebut date,"
					+ "    constraint LesLocations_Key primary key (id, idBR),"
					+ "    constraint LesLocations_Fkid foreign key (id) references LesPersonnes (id)"
					+ ")");
			execStatm("create table HistoriqueLocation ("
					+ "    id integer not null,"
					+ "    noFilm integer not null,"
					+ "    idBR integer,"
					+ "    dateDebut date,"
					+ "    dateFin date,"
					+ "    constraint HistoriqueLocation_Key primary key (id, noFilm, idBR),"
					+ "    constraint HistoriqueLocation_FKid foreign key (id) references LesPersonnes (id),"
					+ "    constraint HistoriqueLocation_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)"
					+ ")");
			execStatm("create sequence id_gens");
			
			//execStatm("drop trigger aut_enum");
			/*
			execStatm("create or replace trigger aut_enum "
					+ "before insert on LesPersonnes "
					+ "for each row "
					+ "begin "
					+ "    select id_gens.nextval "
					+ "    into new.id "
					+ "    from dual; "
					+ "end;");
			*/
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.print("create table error");
			e.printStackTrace();
		}
	}
	
	void data() {
		try {
			conn.setAutoCommit(true);
			System.out.println("data");
			execStatm("insert into LesPersonnes values (1,51651651600000000000000)");
			execStatm("insert into LesPersonnes values (2,51651651600000000000000)");
			execStatm("insert into LesPersonnes values (3,51651651600000000000000)");
			execStatm("insert into LesPersonnes values (4,51651651600000000000000)");
			execStatm("insert into LesPersonnes values (5,51651651600000000000000)");
			execStatm("insert into LesPersonnes values (6,14986549800000000000000)");
			execStatm("insert into LesPersonnes values (7,98562185600000000000000)");
			
			execStatm("insert into LesAbonnes values (1, 'Kevin','Duchamp',15,'Kevdu92@gmail.com','5 rue de la wesh')");
			execStatm("insert into LesAbonnes values (2, 'Astride', 'Duchamp', 15, 'asasbor@gmail.com', '5 rue de la wesh')");
			execStatm("insert into LesAbonnes values (3, 'KevinA', 'Duchamp', 15, 'aled@gmail.com', '5 rue de la wesh')");
			execStatm("insert into LesAbonnes values (4, 'Kevin XV', 'Duchamp', 15, 'KevinLeDiheu@gmail.com','5 rue de la wesh')");
			execStatm("insert into LesAbonnes values (5, 'Mario','Dupre', 15, 'eue@gmail.com', '5 rue de la wesh')");
			execStatm("insert into LesAbonnes values (7, 'Lucas', 'Sauvayre', 15, 'lulu@gmail.com','à la fac')");
			
			execStatm("insert into LesEnfants values (2,1)");
			execStatm("insert into LesEnfants values (3,1)");
			execStatm("insert into LesEnfants values (4,1)");
			execStatm("insert into LesEnfants values (5,1)");
			
			execStatm("insert into LesFilms values (1, 'Le retour des moineaux', 'Francis Ford Coppola', 'Space Opera', 'Il était une fois...')");
			execStatm("insert into LesFilms values (2, 'Jenaipas', 'Timothee', 'documentaire', 'Ba, il savait pas')");
			execStatm("insert into LesFilms values (3, 'jeregardepasbeaucoupdefilm', 'Lucas', 'comedie', 'Comme resume ? un film un peu vide')");
			execStatm("insert into LesFilms values (4, 'Le Parrain', 'Francis Ford Coppola', 'data/leparrain.jpg', 'y a un parin et des mafias')");
			execStatm("insert into LesFilms values (5, 'Les Évadés', 'Frank Darabont', 'data/lesevades.jpg', 'En 1947, Andy Dufresne, un jeune banquier, est condamné à la prison à vie pour le meurtre de sa femme et de son amant.')");
			execStatm("insert into LesFilms values (6, 'La Liste de Schindler', 'Steven Spielberg', 'data/lalistedeschindler.jpg', 'Évocation des années de guerre d’Oskar Schindler, fils d’industriel d’origine autrichienne rentré à Cracovie en 1939 avec les troupes allemandes.')");
			execStatm("insert into LesFilms values (7, 'Parasite', 'Bong Joon-ho', 'data/parasite.jpg', 'Toute la famille de Ki-taek est au chômage. Elle s’intéresse particulièrement au train de vie de la richissime famille Park.')");
			execStatm("insert into LesFilms values (8, 'Pulp Fiction', 'Quentin Tarantino', 'data/pulpfiction.jpg', 'L’odyssée sanglante et burlesque de petits malfrats dans la jungle d’Hollywood.')");
			execStatm("insert into LesFilms values (9, 'The Dark Knight', 'Christopher Nolan', 'data/thedarkknight.jpg', 'Avec l aide du Lieutenant de Police Jim Gordon et du Procureur Harvey Dent, Batman entreprend de démanteler définitivement les organisations criminelles de Gotham.')");
			execStatm("insert into LesFilms values (10, 'Inception', 'Christopher Nolan', 'data/inception.jpg', 'Dom Cobb est un voleur expérimenté, le meilleur dans l art dangereux de l extraction.')");
			execStatm("insert into LesFilms values (11, 'La Ligne verte', 'Frank Darabont', 'data/laligneverte.jpg', 'Paul Edgecomb, pensionnaire centenaire d une maison de retraite, est hanté par ses souvenirs.')");
			execStatm("insert into LesFilms values (12, 'Le Seigneur des anneaux : Le Retour du roi', 'Peter Jackson', 'data/leseigneurdesanneauxleretourduroi.jpg', 'Les armées de Sauron ont attaqué Minas Tirith, la capitale de Gondor.')");
			execStatm("insert into LesFilms values (13, 'Forrest Gump', 'Robert Zemeckis', 'data/forrestgump.jpg', 'Forrest Gump est le symbole d une époque, un candide dans une Amérique qui a perdu son innocence.')");
			
			execStatm("insert into Tags values ('PEGI18')");
			execStatm("insert into Tags values ('PEGI12')");
			execStatm("insert into Tags values ('PEGI16')");
			execStatm("insert into Tags values ('ACTION')");
			execStatm("insert into Tags values ('POLICIER')");
			execStatm("insert into Tags values ('FANTASY')");
			execStatm("insert into Tags values ('WORLDWAR')");
			
			execStatm("insert into TagsFilm values ('PEGI18', 1)");
			execStatm("insert into TagsFilm values ('PEGI12', 3)");
			execStatm("insert into TagsFilm values ('PEGI12', 4)");
			execStatm("insert into TagsFilm values ('POLICIER', 4)");
			execStatm("insert into TagsFilm values ('POLICIER', 5)");
			execStatm("insert into TagsFilm values ('PEGI16', 6)");
			execStatm("insert into TagsFilm values ('WORLDWAR', 6)");
			execStatm("insert into TagsFilm values ('PEGI16', 7)");
			execStatm("insert into TagsFilm values ('PEGI18', 8)");
			execStatm("insert into TagsFilm values ('ACTION', 8)");
			execStatm("insert into TagsFilm values ('ACTION', 9)");
			execStatm("insert into TagsFilm values ('ACTION', 10)");
			execStatm("insert into TagsFilm values ('POLICIER',11)");
			execStatm("insert into TagsFilm values ('FANTASY', 12)");
			
			execStatm("insert into LesRestrictions values (3, 'PEGI18')");
			execStatm("insert into LesRestrictions values (4, 'PEGI12')");
			execStatm("insert into LesRestrictions values (4, 'PEGI18')");
			
			execStatm("insert into LesBlueRay values (6, 3, 'good')");
			
			execStatm("insert into LesBlueRay values (1, 4, 'raye')");
			execStatm("insert into LesBlueRay values (2, 4, 'good')");
			execStatm("insert into LesBlueRay values (3, 5, 'good')");
			execStatm("insert into LesBlueRay values (4, 5, 'good')");
			execStatm("insert into LesBlueRay values (5, 6, 'good')");
			execStatm("insert into LesBlueRay values (7, 7, 'good')");
			execStatm("insert into LesBlueRay values (8, 8, 'good')");
			execStatm("insert into LesBlueRay values (9, 9, 'good')");
			execStatm("insert into LesBlueRay values (10, 10, 'good')");
			
			execStatm("insert into LesLocationsQR values (1,1,sysdate,sysdate)");
			execStatm("insert into LesLocationsQR values (2,1,sysdate,sysdate)");
			execStatm("insert into LesLocationsQR values (2,3,sysdate,sysdate)");
			
			execStatm("insert into LesLocationsBR values (1, 1, sysdate)");
			execStatm("insert into LesLocationsBR values (1, 3, sysdate)");
			execStatm("insert into LesLocationsBR values (1, 4, sysdate)");
			execStatm("insert into LesLocationsBR values (1, 5, sysdate)");
			execStatm("insert into LesLocationsBR values (2, 5, sysdate)");
			
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
