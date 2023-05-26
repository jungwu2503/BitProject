create database bitedu;

use bitedu;

create table gisaTBL(
	std_no int(10) primary key,
	email varchar(10) not null,
	kor integer not null,
	eng integer not null,
	math integer not null,
	sci integer not null,
	hist integer not null,
	total integer not null,
	mgr_code char(1) not null,
	acc_code char(1) not null,
	loc_code char(1) not null
);

select * from gisaTBL;
delete from gisaTBL where std_no = 990001;

select std_no
from gisaTBL
where loc_code = 'B' order by (kor+eng) desc, std_no asc limit 4, 1;
