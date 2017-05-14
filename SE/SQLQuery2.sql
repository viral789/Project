create table users(
userId int identity,
fName varchar(20),
lName varchar(20),
emailId varchar(50) primary key,
password varchar(50),
type varchar(1),
phoneNo varchar(12),
);



drop table users;

delete from users where fname = 'Viral';

insert into users (fName, lName, emailId, password, type, phoneNo) values ('admin', 'admin', 'admin@gmail.com', '1234', '3', '1234567890');

update users set emailId = 'vbhojani1@hawk.iit.edu' ; 

create table user_student(
userId int,
emailId varchar(50) foreign key references users(emailId),
noOfcoursesRegister int,
parentName varchar(50),
parentEmail varchar(50),
parentNo varchar(12) ,
primary key(emailId)
)

select * from users;
select * from user_student;

drop table users;
drop table user_student;

update user_student set parentName = 'girish' where emailId = 'vbhojani@hawk.iit.edu';

create table book(
bookId int identity,
bookName varchar(500),
author varchar(100),
ISBN varchar(50) Primary Key,
publisher varchar(50),
price varchar(10),
noOfBooks int
);

select * from book where bookName = 'software engineering';

update book set bookName ='Introduction to Algorithm' where bookId = '1';

create table bookRequest(
studentName varchar(50),
studentEmail varchar(50) foreign key references users(emailId),
bookName varchar(500),
bookIsbn varchar(50) foreign key references book(ISBN)
);

delete from bookRequest
select * from acceptedStudentBook
select * from bookRequest
select * from book

create table acceptedStudentBook(
studentEmail varchar(50) foreign key references users(emailId),
bookIsbn varchar(50) foreign key references book(ISBN),
status bit,
acceptDate Date  
);

create table studentHavingTotalNoofBook(
studentEmail varchar(50) foreign key references users(emailId),
bookIsbn varchar(50) foreign key references book(ISBN),
returnDate Date
);

drop table studentHavingTotalNoofBook

select * from studentHavingTotalNoofBook

select * from book

update book set noOfBooks =0 where bookId = '4';
create table teachercheckoutbook(
userEmail varchar(50) foreign key references users(emailId),
bookIsbn varchar(50) foreign key references book(ISBN),
checkoutDate Date
);

select * from bookRequest
select * from book
select * from teachercheckoutbook

alter table book
drop column price

create table calculatePenalty(
studentEmail varchar(50) foreign key references users(emailId),
bookIsbn varchar(50) foreign key references book(ISBN),
actualReturnDate Date,
penalty int
);

select * from acceptedStudentBook;
select * from book;
select * from bookRequest;
select * from calculatePenalty;
select * from studentHavingTotalNoofBook;
select * from user_student;
select* from users;

select studentEmail, sum(penalty) as penalty from calculatePenalty group by studentEmail;