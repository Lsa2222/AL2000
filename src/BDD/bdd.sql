drop table HistoriqueLocation
drop table LesAbonnesEnfant
drop table LesAbonnesAdultes
drop table LesLocations
drop table LesRestrictions
drop table LesAbonnes
drop table TagsFilm
drop table LesFilms
drop table LesBlueRay
drop table LesLocationsQR
drop table LesFilmsDisponibles
drop table LesPersonnes

create table LesPersonnes (
    id integer not null,
    isGuest boolean,
    constraint LesPersonnes_1 primary key (id)
);

create table LesAbonnes(
    id integer not null,
    prenom varchar2(20),
    nom varchar2(20),
    credit number(9),
    constraint LesAbonnes_Key primary key (id, preno),
    constraint LesAbonnes_FK foreign key (id) references LesPersonnes (id)
    constraint LesAbonnes_money check (credit >= 0)
);

create table LesAbonnesAdultes(
    id integer not null,
    adrMail varchar2(40),
    AdrPhy varchar2 (90),
    constraint LesAbonnesAdultes_Key primary key (id, adrMail, AdrPhy),
    constraint LesAbonnesAdultes_FK foreign key (id) references LesPersonnes (id)
);

create table LesAbonneEnfant(
    id integer not null,
    id_parent integer not null,
    constraint LesAbonneEnfant_Key primary key (id, id_parent),
    constraint LesAbonneEnfant_FKid foreign key (id) references LesPersonnes (id),
    constraint LesAbonneEnfant_FKidparent foreign key (id_parent) references LesAbonnesAdultes (id)
);

create table LesFilms(
    noFilm integer not null,
    titre varchar2(20),
    realisateur varchar2(20),
    genre varchar2(200)
    resumer varchar2(500),
    constraint LesFilms_key primary key (noFilm)
);

create table TagsFilm(
    tag varchar2(20),
    noFilm integer not null,
    constraint TagsFilm_key primary key (tag, noFilm),
    constraint TagsFilm_FK foreign key (noFilm) references LesFilms (noFilm)
);

create table LesFilmsDisponibles(
    noFilm integer not null,
    constraint LesFilmsDisponibles_Key primary key (noFilm),
    constraint LesFilmsDisponibles_FK foreign key (noFilm) references LesFilms (noFilm)
);

create table LesLocationsQR(
    id integer not null,
    noFilm integer not null,
    dateActivation date,
    constraint LesLocationsQR_Key primary key (id, noFilm, dateActivation),
    constraint LesLocationsQR_FKid foreign key (id) references LesPersonnes (id),
    constraint LesLocationsQR_FKnoFIlm foreign key (noFilm) references LesFilms (noFilm)
);

create table LesBlueRay(
    idBR integer not null,
    noFilm integer not null,
    etat varchat2(4),
    constraint LesBlueRay_Key primary key (idBR),
    constraint LesBlueRay_FKnoFilm foreign key (noFilm) references LesFilms (noFilm)
);

create table LesLocations(
    id integer not null,
    noFilm integer not null,
    idBR integer not null
    dateDebut date,
    constraint LesLocations_Key primary key (id, noFilm, idBR),
    constraint LesLocations_Fkid foreign key (id) references LesPersonnes (id),
    constraint LesLocations_FknoFilm foreign key (noFilm) references LesFilms (noFilm)
);

create table HistoriqueLocation (
    id integer not null,
    noFilm integer not null,
    idBR integer not null,
    dateDebut date,
    dateFin date,
    constraint HistoriqueLocation_Key primary key (id, noFilm, idBR, dateDebut, dateFin),
    constraint HistoriqueLocation_FKid foreign key (id) references LesPersonnes (id),
    constraint HistoriqueLocation_FKnoFilm foreign key (noFilm) references LesFilms (noFilm),
    constraint HistoriqueLocation_FKidBR foreign key (idBR) references LesLocations (idBR),
    constraint HistoriqueLocation_FkdateDebut foreign key (dateDebut) references LesLocations (dateDebut)
);