create database Quality_Dashboard


use Quality_Dashboard


create table project(
p_id int primary key identity,
p_name varchar(50),
TL varchar(50)
);

create table cnergeSprint(
s_id int primary key identity,
s_name varchar(50),
meeting_date Date);


create table graph(
graph_id int primary key identity,
graph_name varchar(50),
graph_checked bit default(0),
graphDescription varchar(500));

create table graph_sprint(
g_id int primary key identity,
s_id int foreign key references cnergeSprint(s_id) on delete cascade,
p_id int foreign key references project(p_id) on delete cascade,
g_name varchar(50),
color varchar(50),
action_plan varchar(50),
inputvalue varchar(50),
validRange varchar(50));

create table graph_project(
gp_id int primary key identity,
p_id int foreign key references project(p_id) on delete cascade,
graph_id int foreign key references graph(graph_id) on delete cascade,
validRange varchar(50),
options varchar(10),
validinput int);

create table Users(
userId int primary key identity,
userName varchar(50),
[password] varchar(20))

create table UserProject(
userId int foreign key references Users(UserId),
role varchar(10),
projectName varchar(15),
primary key(UserId, ProjectName));

select * from cnergeSprint
select * from graph_sprint
select * from project
select * from graph
select * from graph_project
select * from Users
Select * from UserProject

drop table cnergeSprint
drop table project
drop table graph_sprint
drop table graph
drop table graph_project
drop table Users
drop table UserProject

insert into graph(graph_name, graphDescription)
values ('Top 10 Performance Reqs Passing','Top 10 Performance Reqs Passing');
insert into graph(graph_name, graphDescription)
values ('Open Bugs','Open Bugs');
insert into graph(graph_name, graphDescription)
values ('Static Code Analysis Warnings','Static Code Analysis Warnings');
insert into graph(graph_name, graphDescription)
values ('Passing Builds','Passing Builds');
insert into graph(graph_name, graphDescription)
values ('% Automated Unit Test Code Coverage','% Automated Unit Test Code Coverage');
insert into graph(graph_name, graphDescription)
values ('% Regression Test Automated','% Regression Test Automated');
insert into graph(graph_name, graphDescription)
values ('Hardening Sprints','Hardening Sprints');
insert into graph(graph_name, graphDescription)
values ('Automated Unit Tests','Automated Unit Tests');
insert into graph(graph_name, graphDescription)
values ('Hours User Engagement','Hours User Engagement');
insert into graph(graph_name, graphDescription)
values ('Average CI Build Time','Average CI Build Time');
insert into graph(graph_name, graphDescription)
values ('FMEA Failure Modes','FMEA Failure Modes');
insert into graph(graph_name, graphDescription)
values ('Average Story Cycle Time','Average Story Cycle Time');

insert into graph_sprint
values(1,1,'Top 10 Performance Reqs Passing',1,'red','plan','9','>=10');


select g.graph_name, gp.validRange from graph g
inner join graph_project gp
on g.graph_id = gp.graph_id
where gp.p_id = 8

insert into project values('abc', 'pqr');


select g.graph_name, p.p_id from project p
inner join graph_project gp
on p.p_id = gp.p_id
inner join graph g
on g.graph_id = gp.graph_id
where gp.p_id = 1

select p.p_name, p.TL from project p
where p.p_id IN (select gp.p_id from graph_project gp where gp.p_id = 1)


select g.graph_name from graph g
where g.graph_id IN 
(select gp.graph_id from graph_project gp 
where gp.p_id IN 
(select p.p_id from project p));


update project set p_name = 'project3', TL='project3' where p_id=6

update graph_sprint
set inputvalue = '11'
where s_id = 1 and p_id = 1 and g_name = 'Top 10 Performance Reqs Passing'

update graph_sprint set inputvalue='1' where s_id =1 and p_id =3 and g_name='Hardening Sprints'

select g_name, s_id, p_id, inputvalue from graph_sprint
where g_name = 'Open Bugs'
order by s_id desc

delete from project where p_id = 16

select * from graph_sprint where s_id = 9 

update graph_project set options ='>='
where p_id = 20 and graph_id = 1

update project set p_name='Project9', TL='Viral'where p_id=21

insert into Users (userName, password)
values ('viral', 'password')

insert into Users (userName, password)
values ('smita', 'password')

insert into UserProject (userId, role, projectName)
values (1, 'DL', 'Project1')

insert into UserProject (userId, role, projectName)
values (2, 'admin', 'All')

select g.graph_name, p.p_id, gp.validRange, gp.options from project p inner join graph_project gp on p.p_id = 3
			inner join graph g on g.graph_id = gp.graph_id
			
			
select * from graph_sprint where s_id = 9 and p_id = 10 
select * from cnergeSprint


alter table graph_sprint
add NA bit default (0)

update graph_sprint set color = '', action_plan = 'planing', inputvalue = ''
where g_id = 47

insert into graph_sprint (s_id, p_id, g_name) values (6,4, 'Open Bugs');

update graph_sprint set validRange = '<=25' where s_id = 13 and p_id = 3 and g_name='Open Bugs'
update graph_sprint set inputvalue = '', color = '', action_plan = 'vnv_cycle', vnv_cycle = 1
where s_id = 4 and p_id = 6

delete graph_sprint where g_id = 263

alter table project
add QMSName varchar(50)

insert into graph_sprint (s_id, p_id, g_name, validRange) values (6,2, 'Open Bugs', '');

select * from graph_sprint where s_id = 6 and p_id = 4
order by s_id


select * from graph_sprint where p_id = 3