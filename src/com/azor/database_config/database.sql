create database azorcoffee;
use azorcoffee;

-- Tạo bảng tài khoản
create table Account(
	account_id int NOT NULL AUTO_INCREMENT primary key,
	username varchar(30) unique,
    password varchar(20),
	email varchar(50),
    full_name nvarchar(30)
);

-- Tạo bảng danh mục sản phẩm
create table Category(
	category_id int not null auto_increment primary key,
    name nvarchar (50)
);

-- Tạo bảng sản phẩm
create table Food(
    name nvarchar(50) primary key,
    category_id int,
    price int,
    foreign key(category_id) references Category(category_id)
);

-- Tạo bảng danh sách bàn ăn
create table TableFood(
	tablefood_id int not null auto_increment primary key,
    name nvarchar(50) default "bàn chưa có tên",
    status bit -- 0 là trống, 1 là đã có khách
);

-- Tạo bảng hóa đơn	
create table Bill(
	bill_id int not null auto_increment primary key,
    sub_total int default 0,
    date_check_in date,
    tablefood_id int,
    status bit, -- 1 là chưa thanh toán, 0 là đã thanh toán. 1 bàn trong 1 thời điểm buộc chỉ có 1 hóa đơn chưa thanh toán
    foreign key (tablefood_id) references TableFood(tablefood_id)
);

-- Tạo bảng chi tiết hóa đơn
create table BillDetail(
	billdetail_id int not null auto_increment primary key,
    bill_id int,
    food_name nvarchar(50),
    count int,
	foreign key	(bill_id) references Bill(bill_id),
    foreign key (food_name) references Food(name)
);

-- Insert dữ liệu mẫu:
-- Bảng Account:
insert into Account(username, password, email, full_name) 
values ("NguyenXuanAn", "123456", "abc@gmail.com", "Nguyễn Xuân An");
insert into Account(username, password, email, full_name) 
values ("abc", "123", "abc@gmail.com", "Nguyễn Xuân An");

-- Bảng Category
insert into Category (name)
values ("Trà");
insert into Category (name)
values ("Đá xay");

-- Bảng Food
insert into Food (name, category_id, price)
values ("Trà sữa", 1, 20000);
insert into Food (name, category_id, price)
values ("Chocolate đá xay", 2, 40000);

-- Bảng TableFood
insert into TableFood (name, status)
values ("Bàn 1", 1);

-- Bảng Bill
insert into Bill (sub_total, date_check_in, tablefood_id, status)
values (100000, "2019-4-16", 1, 1);
insert into Bill (sub_total, date_check_in, tablefood_id, status)
values (200000, "2019-3-16", 1, 0);

-- Bảng BillDetail
insert into BillDetail (bill_id, food_name, count)
values (1, "Trà sữa", 1);
insert into BillDetail (bill_id, food_name, count)
values (1, "Chocolate đá xay", 2);
insert into BillDetail (bill_id, food_name, count)
values (2, "Trà sữa", 5);

-- test
select bill_id from bill where tablefood_id=1 and status = 1
select * from billdetail where bill_id = 1
drop database azorcoffee



