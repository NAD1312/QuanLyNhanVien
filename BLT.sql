
create DATABASE Quanlydiemdanhchamcong
GO
USE Quanlydiemdanhchamcong
GO
CREATE TABLE salary
(
	salary_id CHAR(4) NOT NULL,
	salary_base INT NOT NULL,
	Primary key(salary_id),
)
GO
CREATE TABLE project
(
	project_id CHAR(4) NOT NULL PRIMARY KEY,
	project_name NVARCHAR(20) NOT NULL,
	project_start DATETIME NOT NULL,
	project_finish DATETIME NOT NULL,
	project_status BIT NOT NULL
)
GO
CREATE TABLE staff
(
	staff_id CHAR(4) NOT NULL PRIMARY KEY,
	salary_id CHAR(4) NOT NULL,
	staff_password CHAR(8) NOT NULL,
	staff_fullname NVARCHAR(15) NOT NULL,
	staff_age INT NOT NULL,
	staff_address NVARCHAR(50) NOT NULL,
	staff_sex nvarchar(5) not null,
	CONSTRAINT fk_salary_id FOREIGN KEY (salary_id) REFERENCES dbo.salary(salary_id)
)
Go
Create table award(
	project_id CHAR(4) NOT NULL,
	staff_id CHAR(4) NOT NULL,
	salary_award INT NOT NULL,
	primary key(project_id, staff_id),
	CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES dbo.project(project_id),
	CONSTRAINT fk_staff_id1 FOREIGN KEY (staff_id) REFERENCES dbo.staff(staff_id)
)
go
CREATE TABLE attendance
(
	staff_id CHAR(4) NOT NULL,
	date DATETIME NOT NULL,
	time_in TIME NOT NULL,
	PRIMARY KEY(staff_id, date),
	CONSTRAINT fk_staff_id FOREIGN KEY (staff_id) REFERENCES dbo.staff(staff_id),
)


go
create table admin(
	admin_id char(4) not null primary key, 
	user_name nvarchar(20) not null, 
	user_pass nvarchar(20) not null,  
)
go
insert into admin values
('AD01', 'admin', 'admin')
go
insert into project values
('PJ01', 'Website', '2010-01-02', '2010-02-02', 1),
('PJ02', 'App', '2011-01-02', '2011-02-02', 0),
('PJ03', 'Image', '2013-01-02', '2013-02-02', 0),
('PJ04', 'Video', '2012-01-02', '2013-02-02', 1),
('PJ05', 'Music', '2011-01-02', '2015-06-02', 0)
go
insert into salary values 
('SA01', 280000),
('SA02', 350000),
('SA03', 420000),
('SA04', 500000)
go
insert into staff values 
('NV01', 'SA01', 'NV01Pass', 'Nam1', 20, 'Ha Noi', 'nam'),
('NV02', 'SA02', 'NV02Pass', 'Nam2', 22, 'Hai Duong', 'nam'),
('NV03', 'SA03', 'NV03Pass', 'Nu3', 23, 'Vinh Phuc', 'nu'),
('NV04', 'SA01', 'NV04Pass', 'Nu4', 24, 'Hai Phong', 'nu'),
('NV05', 'SA04', 'NV05Pass', 'Nam5', 25, 'Binh Duong', 'nam')
go
insert into award values 
('PJ01', 'NV01', 2000000),
('PJ02', 'NV02', 3000000),
('PJ03', 'NV03', 4000000),
('PJ04', 'NV04', 5000000)
go
insert into attendance values 
('NV01', '2010-01-01', '08:00:00'),
('NV02', '2010-01-01', '07:59:00'),
('NV03', '2010-01-01', '09:00:00'),
('NV04', '2010-01-01', '08:30:00'),
('NV05', '2010-01-01', '08:00:00')
go
select * from award
go
select * from project
go
select * from attendance
go
go
---- Xóa 1 dự án và xóa toàn bộ các dữ liệu bảng có liên quan
create proc pro_XoaPro(@project_id char(4))
as
begin
	alter table project nocheck constraint all
	delete from award where project_id = @project_id
	delete from project where project_id = @project_id
	
end

go
---- Sửa 1 dự án 
create proc pro_SuaDuAn(@project_id char(4), @project_name nvarchar(20), @project_start datetime, @project_finish datetime, @project_status bit)
as
begin
	update project 
	set project_name = @project_name,
	project_start =  @project_start,
	project_finish = @project_finish,
	project_status = @project_status
	where project_id = @project_id
end 
go
---- Xóa 1 mức lương
create proc pro_XoaSa(@salary_id char(4))
as
begin
	delete from salary where salary_id = @salary_id
end

go

select * from salary 
go
select * from staff
---- Sửa 1 mức lương
go
create proc pro_SuaSalary(@salary_id char(4), @salary_base int)
as
begin
	update salary
	set salary_base = @salary_base
	where salary_id= @salary_id
end 


go
CREATE PROC delstaff(@staff_id CHAR(4))
AS
BEGIN 
	
	IF EXISTS (SELECT * FROM dbo.award WHERE staff_id = @staff_id)
	BEGIN 
		DELETE FROM dbo.award WHERE staff_id = @staff_id
	END	
	IF EXISTS (SELECT * FROM dbo.attendance WHERE staff_id = @staff_id)
	BEGIN 
		DELETE FROM dbo.attendance WHERE staff_id = @staff_id
	END	
	IF EXISTS (SELECT * FROM dbo.total_salary WHERE staff_id = @staff_id)
	BEGIN 
		DELETE FROM dbo.total_salary WHERE staff_id = @staff_id
	END	
	DELETE FROM dbo.staff WHERE staff_id = @staff_id
END	
EXEC dbo.delstaff @staff_id = 'NV01' -- char(4)

GO	

create FUNCTION fn_totalaward()
RETURNS @bang TABLE (staff_id CHAR(4), totalaward INT, project_status bit) AS
BEGIN 
	INSERT INTO @bang
	        ( staff_id, totalaward, project_status )
	SELECT staff_id, SUM(salary_award) AS totalaward, project_status FROM dbo.award INNER JOIN dbo.project ON project.project_id = award.project_id
GROUP BY staff_id, project_status
return
END	
GO

CREATE FUNCTION fn_totalbase()
RETURNS @bang1 TABLE (staff_id CHAR(4), month INT, year int, totalbase int) AS
BEGIN 
	INSERT INTO @bang1
	SELECT attendance.staff_id,MONTH(date) AS month, YEAR(date) AS year, ((COUNT(DAY(date))) * dbo.salary.salary_base) AS totalbase FROM dbo.staff INNER JOIN dbo.attendance ON attendance.staff_id = staff.staff_id INNER JOIN dbo.salary ON salary.salary_id = staff.salary_id
GROUP BY attendance.staff_id, salary_base, MONTH(date), YEAR(date)
return
END
go

SELECT fn_totalaward.staff_id,month,year,totalaward,totalbase, project_status, (totalbase + totalaward) AS tong FROM dbo.fn_totalaward() INNER JOIN fn_totalbase() ON fn_totalbase.staff_id = fn_totalaward.staff_id

SELECT fn_totalaward.staff_id,month,year,totalaward,totalbase, project_status,
CASE project_status
	WHEN 0 THEN  totalbase + 0
	ELSE totalbase + totalaward
END
AS total
FROM dbo.fn_totalaward() INNER JOIN fn_totalbase() ON fn_totalbase.staff_id = fn_totalaward.staff_id
GO

go
SELECT * FROM dbo.attendance
go
create FUNCTION fn_count_late (@staff_id char(4), @month int)
RETURNs int
AS
BEGIN
	DECLARE @kq INT
    SELECT @kq = (SELECT COUNT(date) FROM dbo.attendance WHERE staff_id = @staff_id AND MONTH(date) = @month GROUP BY date)
	RETURN @kq
END
go
SELECT dbo.fn_count_late('NV01', 1)
GO



