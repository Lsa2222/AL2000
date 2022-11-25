create or replace trigger limite_souscompte
after insert on LesAbonnes
for each row
declare 
    nb_sscompte integer;
begin
    select count(*)into nb_sscompte from LesAbonnes a where :old.cb = a.cb
    if (nb_sscompte > 5) then 
        raise_application_error(-201,'Trop de sous-compte')
    end if;
end;
/