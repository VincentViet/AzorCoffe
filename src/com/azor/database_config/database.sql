create database azorcoffee
use azorcoffee

-- Tạo bảng tài khoản
create table Account(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(30) unique,
    password varchar(20),
	email varchar(50),
    full_name nvarchar(30),
    phone int,
    primary key(id)
);

-- Tạo bảng danh mục sản phẩm
create table Category(
	id int not null auto_increment,
    name nvarchar (50),
    primary key(id)
);

-- Tạo bảng sản phẩm
create table Product(
	id int not null auto_increment,
    name nvarchar(50),
    id_category int,
    price int,
    primary key(id),
    foreign key(id_category) references Category(id)
);

-- Tạo bảng danh sách bàn ăn
create table TableFood(
	id int not null auto_increment,
    name nvarchar(50) default "bàn chưa có tên",
    status nvarchar(20) default "trống",
    primary key(id)
);

-- Tạo bảng hóa đơn
create table Bill(
	id int not null auto_increment,
    tong_tien int,
    ngay_xuat_hoa_don date,
    table_id int,
    primary key(id),
    foreign key (table_id) references TableFood(id)
);

-- Tạo bảng chi tiết hóa đơn
create table BillDetail(
	id int not null auto_increment,
    bill_id int,
    food_id int,
    count int,
    primary key(id),
	foreign key	(bill_id) references Bill(id),
    foreign key (food_id) references Product(id)
);

-- Insert dữ liệu mẫu:
-- Bảng Account:
insert into Account(username, password, email, full_name, phone) 
values ("NguyenXuanAn", "123456", "abc@gmail.com", "Nguyễn Xuân An", 774657158);

-- Bảng Category
insert into Category (name)
values ("Trà");
insert into Category (name)
 values ("Đá xay");

-- Bảng Product
insert into Product (name, id_category, price)
values ("Trà sữa", 1, 20000);
insert into Product (name, id_category, price)
values ("Chocolate đá xay", 2, 40000);

-- Bảng TableFood
insert into TableFood (name, status)
values ("Bàn 1", "Trống");

-- Bảng Bill
insert into Bill (tong_tien, ngay_xuat_hoa_don, table_id)
values (100000, 2019/4/16, 1);
insert into Bill (tong_tien, ngay_xuat_hoa_don, table_id)
values (200000, 2019/3/16, 1);

-- Bảng BillDetail
insert into BillDetail (bill_id, food_id, count)
values (1, 1, 1); -- Bill thứ 1, Product thứ 1, count 1
insert into BillDetail (bill_id, food_id, count)
values (1, 2, 2);
