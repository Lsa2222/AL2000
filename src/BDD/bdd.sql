drop table HistoriqueLocation;
drop table LesLocationsBR;
drop table LesRestrictions;
drop table LesAbonnes;
drop table TagsFilm;
drop table LesBlueRay;
drop table LesLocationsQR;
drop table Tags;
drop table LesFilms;
drop table LesPersonnes;

create table LesPersonnes (
    id integer not null AUTO_INCREMENT,
    cb number(23)
    constraint LesPersonnes_Key primary key (id)
);

create table LesAbonnes(
    id integer not null,
    cb number(23),
    prenom varchar2(20),
    nom varchar2(20),
    credit number(9),
    adrMail varchar2(40),
    AdrPhy varchar2 (90),
    constraint LesAbonnes_Key primary key (id),
    constraint LesAbonnes_FK foreign key (id) references LesPersonnes (id),
    constraint LesAbonnes_money check (credit >= 0)
);

create table LesFilms(
    noFilm integer not null,
    titre varchar2(20),
    realisateur varchar2(20),
    genre varchar2(200),
    resumer varchar2(500),
    constraint LesFilms_key primary key (noFilm)
);

create table Tags(
	tag varchar2(50),
	constraint Tag primary key (tag)
);

create table TagsFilm(
    tag varchar2(20),
    noFilm integer not null,
    constraint TagsFilm_key primary key (tag, noFilm),
    constraint TagsFilm_Fktag foreign key (tag) references Tags (tag),
    constraint TagsFilm_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)
);

create table LesRestrictions(
	id integer not null,
	tag varchar2(20),
	constraint LesRestrictions_Key primary key (id, tag),
	constraint LesRestrictions_Fkid foreign key (id) references LesPersonnes (id),
	constraint LesRestrictions_Fktag foreign key (tag) references Tags (tag)
);

create table LesLocationsQR(
    id integer not null,
    noFilm integer not null,
    dateActivation date,
    dateAchat date,
    constraint LesLocationsQR_Key primary key (id, noFilm, dateActivation),
    constraint LesLocationsQR_FKid foreign key (id) references LesPersonnes (id),
    constraint LesLocationsQR_FKnoFIlm foreign key (noFilm) references LesFilms (noFilm)
);

create table LesBlueRay(
    idBR integer not null,
    noFilm integer not null,
    etat varchar2(4),
    constraint LesBlueRay_Key primary key (idBR),
    constraint LesBlueRay_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)
);

create table LesLocationsBR(
    id integer not null,
    idBR integer not null,
    dateDebut date,
    constraint LesLocations_Key primary key (id, noFilm, idBR),
    constraint LesLocations_Fkid foreign key (id) references LesPersonnes (id),
    constraint LesLocations_FknoFilm foreign key (noFilm) references LesFilms (noFilm)
);

create table HistoriqueLocation (
    id integer not null,
    noFilm integer not null,
    idBR integer, /* Si égal à 0, alors QR*/
    dateDebut date,
    dateFin date,
    constraint HistoriqueLocation_Key primary key (id, noFilm, idBR),
    constraint HistoriqueLocation_FKid foreign key (id) references LesPersonnes (id),
    constraint HistoriqueLocation_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)
);
