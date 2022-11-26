create or replace trigger ajoutehistoriqueBR
after delete on LesLocationsBR
for each row
begin
	insert into HistoriqueLocation values (:old.id, (select b.nofilm from LesBlueRay b where :old.idbr=b.idbr), :old.idBR, :old.dateDebut, SYSDATE);
end;
/

create or replace trigger ajoutehistoriqueQR
after delete on LesLocationsQR
for each row
begin
    insert into HistoriqueLocation values (:old.id, :old.noFilm, 0, :old.dateAchat, SYSDATE);
end;
/
