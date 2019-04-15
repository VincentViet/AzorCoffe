create database azorcoffee
use azorcoffee

create table accounts(
	id NOT NULL AUTO_INCREMENT,
	username varchar(30),
    email varchar(50),
    password varchar(20),
    full_name nvarchar(30),
    phone int
)

create table bills(
	id not null auto_increment,
    tong_tien int,
    ngay_xuat_hoa_don