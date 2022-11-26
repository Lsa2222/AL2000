insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,51651651600000000000000);
insert into LesPersonnes values (0,14986549800000000000000);
insert into LesPersonnes values (0,98562185600000000000000);

insert into LesAbonnes values (1,51651651600000000000000, 'Kevin','Duchamp',15,'Kevdu92@gmail.com','5 rue de la wesh');
insert into LesAbonnes values (2,51651651600000000000000, 'Astride', 'Duchamp', 15, 'asasbor@gmail.com', '5 rue de la wesh');
insert into LesAbonnes values (3,51651651600000000000000, 'KevinA', 'Duchamp', 15, 'aled@gmail.com', '5 rue de la wesh');
insert into LesAbonnes values (4,51651651600000000000000, 'Kevin XV', 'Duchamp', 15, 'KevinLeDiheu@gmail.com','5 rue de la wesh');
insert into LesAbonnes values (5,51651651600000000000000, 'EncoreunEnfant','Dupre', 15, 'eue@gmail.com', '5 rue de la wesh');
insert into LesAbonnes values (6,51651651600000000000000, 'EnfantCache', 'Duchamp', 582, 'bwahah@gmail.com', '5 rue de la wesh');
insert into LesAbonnes values (8,98562185600000000000000, 'Lucas', 'Sauvayre', 15, 'lulu@gmail.com','à la fac');

insert into LesFilms values (1, 'Le retour des moineaux', 'Inconnu', 'Space Opera', 'Il était une fois...');
insert into LesFilms values (2, 'Jenaipas', 'Timothee', 'documentaire', 'Ba, il savait pas');
insert into LesFilms values (3, 'jeregardepasbeaucoupdefilm', 'Lucas', 'comedie', 'Comme resume ? un film un peu vide');
insert into LesFilms values (4, 'aucuneidee', 'Joan', 'baaucuneideeaussi', 'aucune idee, ça va être génial pour la réu');

insert into Tags values ('Pegi18');
insert into Tags values ('Pegi12');
insert into Tags values ('Pegi16');

insert into TagsFilm values ('Pegi18', 1);
insert into TagsFilm values ('Pegi12', 3);
insert into TagsFilm values ('Pegi12', 4);

insert into LesRestrictions values (3, 'Pegi18');
insert into LesRestrictions values (4, 'Pegi12');
insert into LesRestrictions values (4, 'Pegi18');

insert into LesLocationsQR values (1,1,sysdate,sysdate);
insert into LesLocationsQR values (2,1,sysdate,sysdate);
insert into LesLocationsQR values (2,3,sysdate,sysdate);

insert into LesBlueRay values (1, 1, 'good');
insert into LesBlueRay values (2, 1, 'raye');
insert into LesBlueRay values (3, 2, 'good');
insert into LesBlueRay values (4, 2, 'good');
insert into LesBlueRay values (5, 2, 'good');
insert into LesBlueRay values (6, 3, 'raye');

insert into LesLocationsBR values (1, 1, sysdate);
insert into LesLocationsBR values (1, 3, sysdate);
insert into LesLocationsBR values (1, 4, sysdate);
insert into LesLocationsBR values (1, 5, sysdate);
insert into LesLocationsBR values (2, 5, sysdate);

delete from LesLocationsQR q where q.id=1 and q.nofilm=1;
delete from LesLocationsBR b where b.id=1 and b.idbr=1;

select * from HistoriqueLocation;
