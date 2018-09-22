--------------------------1--------------------------------------------
select staff_name,design_code
from staff_master
where hiredate<'01-jan-03' and staff_sal between 12000 and 25000;
-----------------------------2-----------------------------------------
select staff_code,staff_name,dept_code
from staff_master
where round(months_between(sysdate,hiredate)/12) >=18
order by hiredate;
-----------------------------3--------------------------------
select * from staff_master where mgr_code is null;
-----------------------------4---------------------------------
select *
from book_master
where book_pub_year between 2001 and 2004 and book_name like '%&%';
------------------------------5-------------------------------------
select * from staff_master where staff_name like '%\_%' escape '\';
