declare
	associaterecord associate%rowtype;
begin
	select * into associaterecord from Associate where associateid=&id;
	DBMS_OUTPUT.PUT_LINE('aSSOCIATEiD' || Assocaiterecord.associateId);
	DBMS_OUTPUT.PUT_LINE('firstname' || Assocaiterecord.firstname);
	DBMS_OUTPUT.PUT_LINE('lastname' || Assocaiterecord.lastname);
	exception
	when
	no_data_found
	then
	DBMS_OUTPUT.PUT_LINE('associatedetails not found');
	end;
	
	
	declare
	type customtype is record(
	fname associate. firstname%type,
	lname associate. lastname%type);
	fullname customtype;
begin
	select firstname,lastname into fullname from Associate where associateid=&id;
	DBMS_OUTPUT.PUT_LINE('firstname' || fullname.firstname);
	DBMS_OUTPUT.PUT_LINE('lastname' || fullname.lastname);
	exception
	when
	no_data_found
	then
	DBMS_OUTPUT.PUT_LINE('associatedetails not found');
	end;
	
	set server output on
	
	
declare
n1 number(10) :=100;
n2 number(10) :=200;
begin
if n1>n2
then
DBMS_OUTPUT.PUT_LINE(n1 || 'is greater than' ||n2);
else
DBMS_OUTPUT.PUT_LINE(n2 || 'is greater than' ||n1);
end if;
end;
/

declare 
n1 number(10) :=1;
begin
while n1<=10
loop
DBMS_OUTPUT.PUT_LINE(n1);
n1 :=n1+1;
end loop;
end;
/
-----------------------------------------CURSOR-------------------------------------------fetch is to -1 position --------cname is cursor name----------------
declare
cursor cName is select * from associate;
associaterecord associate%rowtype;
begin
if cName%isopen
then
null;
else
open cName;
end if;
fetch cname into associaterecord;
loop
fetch cname into associaterecord; 
exit when cname%notfound;
DBMS_OUTPUT.PUT_LINE(ASSOCIATerecord.firstname||' '||associaterecord.lastname);
END LOOP;
END;
/
--------------------------exception----------------------------------------
declare
cursor cName is select * from associate where associateid=&aid;
associaterecord associate%rowtype;
associate_not_found exception;
begin
if cName%isopen
then
null;
else
open cName;
end if;
fetch cname into associaterecord;
if cname%notfound
then
raise associate_not_found;
else
DBMS_OUTPUT.PUT_LINE(associaterecord.firstname||' '||associaterecord.lastname);
end if;
exception  
when 
associate_not_found
then
DBMS_OUTPUT.PUT_LINE('associate details not found');
END;
/

------------------------------




	
	
	
	
