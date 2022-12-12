create or replace trigger ajoutehistoriqueBR
before delete on LesLocationsBR
for each row
begin
	insert into HistoriqueLocation values (:old.id, (select b.nofilm from LesBlueRay b where :old.idbr=b.idbr), :old.idBR, :old.dateDebut, SYSDATE);
end;
/

create or replace trigger ajoutehistoriqueQR
before delete on LesLocationsQR
for each row
begin
    insert into HistoriqueLocation values (:old.id, :old.noFilm, 0, :old.dateAchat, SYSDATE);
end;
/

create or replace trigger guestLocation
after insert on LesLocationsBR
declare
    nb_max integer;
begin
    select max(count(*)) into nb_max from LesPersonnes p, LesLocationsBR l where p.id in (select p.id from LesPersonnes p minus(select a.id from lesabonnes a)) and l.id=p.id group by p.id;
    if (nb_max > 1) then 
        RAISE_APPLICATION_ERROR(-20154, 'il y a deja 1 films en location actuellement');
    end if;
end;
/
