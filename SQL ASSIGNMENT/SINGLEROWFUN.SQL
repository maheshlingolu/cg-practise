-----------------------1------------------------------
select staff_name,'$' || LPAD(staff_sal,15,0) as Salary
from staff_master;
---------------------2------------------------------
select student_name,to_char(student_dob,'month dd yyyy') as student_dateofbirth 
from student_master 
where to_char(student_dob,'day') like  'saturday' or to_char(student_dob,'day') like  'sunday' ;
------------------------3--------------------------------------
select staff_name,round(months_between(sysdate,hiredate)) as "Months Worked"
from staff_master
order by hiredate;
---------------------------4---------------------------------------
select *
from staff_master
where to_char(hiredate,'dd') between 1 and 15 and to_char(hiredate,'month') like '%december%';
-----------------------------5-----------------------------------------
select staff_name,staff_sal,
case
when staff_sal >=50000 then 'A'
when staff_sal >=25000 and staff_sal<50000 then 'B'
when staff_sal >=10000 and staff_sal<25000 then 'C'
else 'D'
end Grade
from staff_master;
------------------------------------6----------------------------------------
select staff_name,hiredate,to_char(hiredate,'day') as Day
from staff_master
order by to_char(hiredate,'d');
-------------------------------------------7------------------------------
select instr('mississippi','i',2,3) from dual;
---------------------------------------------8----------------------------
select (to_char(to_date(to_char(next_day(last_day(sysdate)-interval '7' day,'FRIDAY'),'dd'),'j'),'jsp') || ' of ' ||to_char(sysdate,'month')||','||to_char(sysdate,'yyyy')) as "PAY DATE"
from dual;
--------------------------------------------9--------------------------------------
select student_code,student_name,
case 
when dept_code=20 then 'Electricals'
when dept_code=30 then 'Electronics'
else 'others' 
end as "deptname" 
from student_master;


