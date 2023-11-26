create database Bank
go

use Bank

--�إ� �Ӽh�y��(SeatingChart)��ƪ�
create table seating_chart(
	floor_seat_seq int identity(1,1) primary key,
	floor_no int not null,
	seat_no int not null
);

--�إ� ���u(Employee)��ƪ�
create table employee(
	emp_id int identity(10000,1) primary key,
	name varchar(20) not null,
	email varchar(50) not null,
	floor_seat_seq int null,
	foreign key (floor_seat_seq) references seating_chart(floor_seat_seq)
);

--����Ҧ��y���T���w�s�{��
create procedure getAllSeatInfo
as
begin
select sc.floor_seat_seq,sc.floor_no,sc.seat_no,e.emp_id
from seating_chart sc
left join employee e on sc.floor_seat_seq = e.floor_seat_seq;
end;

--����Ҧ����u��ID�w�s�{��
create procedure getEmpId
as
begin
select e.emp_id from employee as e order by e.emp_id asc;
end;

--��s�y���T���w�s�{��
create procedure updateSeat
    @empId int,
    @seatId int
as
begin
    if @seatId <= 0
    begin
        set @seatId = null;
    end

    update employee
    set floor_seat_seq = @seatId
    where emp_id = @empId;
end;

SET IDENTITY_INSERT seating_chart ON;

insert into seating_chart (floor_seat_seq, floor_no, seat_no)
values  (1, 1, 1),
        (2, 1, 2),
        (3, 1, 3),
        (4, 1, 4),
        (5, 1, 5),
        (6, 2, 1),
        (7, 2, 2),
        (8, 2, 3),
        (9, 2, 4),
        (10, 2, 5),
        (11, 3, 1),
        (12, 3, 2),
        (13, 3, 3),
        (14, 3, 4),
        (15, 3, 5);

SET IDENTITY_INSERT seating_chart OFF;

insert into employee (name, email, floor_seat_seq)
values  ('�p��', '123@gmail.com', 1),
        ('�p�P', '321@gmail.com', null),
        ('�p�L', '123@gmail.com', 12),
        ('�p�i', '231@gmail.com', 7),
        ('�p��', '1122@gmail.com', null),
        ('�p��', '1133@gmail.com', null),
        ('�p�d', '22@gmail.com', 8),
        ('�p�]', '44@gmail.com', null),
        ('�p��', '55@gmail.com', null),
        ('�p��', '66@gmail.com', null);