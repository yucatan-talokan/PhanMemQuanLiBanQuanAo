﻿USE MASTER 
GO

CREATE DATABASE QUANLYBANQUANAOTEST
GO

USE QUANLYBANQUANAOTEST
GO

CREATE TABLE SANPHAM(
	ID INT IDENTITY(1,1), 
		MASP NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)

GO

CREATE TABLE SIZE(
	ID INT IDENTITY(1,1),
		MASIZE NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO
CREATE TABLE NSX(
	ID INT IDENTITY(1,1),
		MANSX NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO

CREATE TABLE DONGSP(
	ID INT IDENTITY(1,1),
		MADONGSP NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO

CREATE TABLE MAUSAC(
	ID INT IDENTITY(1,1),
		MAMAUSAC NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO

CREATE TABLE CHATLIEU(
	ID INT IDENTITY(1,1),
		MACHATLIEU NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO

CREATE TABLE CHITIETSP(
	ID INT IDENTITY(1,1),
	MACTSP NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	IDSP INT,
	IDDONGSP INT,
	IDMAUSAC INT,
	IDCHATLIEU INT,
	IDSIZE INT,
	IDNSX INT,
	ANH NVARCHAR(255),
	SOLUONGTON INT,
	GIA INT,
	MAVACH INT,
	MOTA NVARCHAR(255),
	PRIMARY KEY(ID),
	FOREIGN KEY(IDSP) REFERENCES SANPHAM(ID),
	FOREIGN KEY(IDDONGSP) REFERENCES DONGSP(ID),
	FOREIGN KEY(IDMAUSAC) REFERENCES MAUSAC(ID),
	FOREIGN KEY(IDCHATLIEU) REFERENCES CHATLIEU(ID),
	FOREIGN KEY(IDSIZE) REFERENCES SIZE(ID),
	FOREIGN KEY(IDNSX) REFERENCES NSX(ID)
)
GO

CREATE TABLE CHUCVU(
	ID INT IDENTITY(1,1),
		MACHUCVU NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO

CREATE TABLE NHANVIEN(
	ID INT IDENTITY(1,1),
	MANV NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	CCCD nvarchar(20) unique,
	TEN NVARCHAR(255),
	IDCHUCVU INT,
	GIOITINH BIT,
	NGAYSINH DATE,
	SDT NVARCHAR(10) UNIQUE NOT NULL,
	DIACHI NVARCHAR(255),
	EMAIL NVARCHAR(255) UNIQUE NOT NULL,
	MATKHAU NVARCHAR(255),
	TRANGTHAI BIT,
	PRIMARY KEY(ID),
	FOREIGN KEY(IDCHUCVU) REFERENCES CHUCVU(ID)
)
GO

CREATE TABLE KHUYENMAI(
	ID INT IDENTITY(1,1),
	MAKHUYENMAI NVARCHAR(20) UNIQUE,
	NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	MUCGIAMGIA INT,
	NGAYBATDAU DATETIME,
	NGAYKETTHUC DATETIME,
	TRANGTHAI INT,
	PRIMARY KEY(ID)
)
GO


CREATE TABLE KHACHHANG(
	ID INT IDENTITY(1,1),
	MAKHACHHANG NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	CCCD NVARCHAR(20) ,
	TEN NVARCHAR(255),
	SDT NVARCHAR(10),
	CAPBAC INT,
	DIACHI NVARCHAR(255),
	PRIMARY KEY(ID)
)
GO 
CREATE TABLE HOADON(
	ID INT IDENTITY(1,1),
	MAHOADON NVARCHAR(20) UNIQUE,
		NGAYTAO DATE,
	NGAYSUA DATE,
	IDNHANVIEN INT,
	IDKHACHHANG INT,
	SDT NVARCHAR(10) DEFAULT N'Không có',
	DIACHI NVARCHAR(255),
	HINHTHUCTHANHTOAN INT DEFAULT 0,
	LYDO NVARCHAR(255) DEFAULT N'Không có',
	NGAYTHANHTOAN DATE,
	TIENKHACHTRA INT DEFAULT 0,
	TIENKHACHCHUYENKHOAN INT DEFAULT 0,
	TRANGTHAI INT DEFAULT 0,
	TONGTIEN INT DEFAULT 0,
	IDKHUYENMAI INT DEFAULT 1,
	TIENSAUGIAMGIA INT DEFAULT 0,
	PRIMARY KEY(ID),
	FOREIGN KEY(IDNHANVIEN) REFERENCES NHANVIEN(ID),
	FOREIGN KEY(IDKHUYENMAI) REFERENCES KHUYENMAI(ID),
	FOREIGN KEY(IDKHACHHANG) REFERENCES KHACHHANG(ID)
)
GO

CREATE TABLE HOADONCHITIET(
	ID INT IDENTITY(1,1),
	IDHOADON INT,
	IDCTSP INT,
		NGAYTAO DATE,
	NGAYSUA DATE,
	TENSP NVARCHAR(255),
	TENNSX NVARCHAR(255),
	DONGSP NVARCHAR(255),
	MAUSAC NVARCHAR(255),
	ANH NVARCHAR(255),
	SIZE NVARCHAR(255),
	SOLUONG INT,
	DONGIA INT,
	TRANGTHAI INT DEFAULT 0,
	PRIMARY KEY(ID),
	FOREIGN KEY(IDHOADON) REFERENCES HOADON(ID),
	FOREIGN KEY(IDCTSP) REFERENCES CHITIETSP(ID)
)
GO
INSERT INTO CHUCVU(MACHUCVU,TEN) VALUES('CV1',N'Quản lý')
INSERT INTO CHUCVU(MACHUCVU,TEN) VALUES('CV2',N'Nhân viên')
INSERT INTO NHANVIEN VALUES(N'NVI1','2023-10-10','2023-10-10','034203009023',N'Mai Thanh Giang',1,0,'2003-01-11','0123456789',N'Nam Định','maigiang534@gmail.com',123,0)
--select * from SANPHAM
--INSERT INTO SANPHAM VALUES(N'Quần jeans nam')
--INSERT INTO SANPHAM VALUES(N'Quần shorts nam')
--INSERT INTO SANPHAM VALUES(N'Áo sơ mi nam tay dài')
--INSERT INTO SANPHAM VALUES(N'Quần kaki nam')
--INSERT INTO SANPHAM VALUES(N'Áo khoác nam')
--INSERT INTO SANPHAM VALUES(N'Áo sweater nam')
--INSERT INTO SANPHAM VALUES(N'Áo thun tay dài nam')
--INSERT INTO SANPHAM VALUES(N'Áo khoác jeans nam')
--INSERT INTO SANPHAM VALUES(N'Áo len nam')
--INSERT INTO SANPHAM VALUES(N'Váy kaki')
--INSERT INTO SANPHAM VALUES(N'Áo sơ mi nữ tay dài')
--INSERT INTO SANPHAM VALUES(N'Quần shorts nữ')
--INSERT INTO SANPHAM VALUES(N'Quần jeans nữ')
--INSERT INTO SANPHAM VALUES(N'Áo len nữ')
--INSERT INTO SANPHAM VALUES(N'Áo thun nữ tay ngắn')


--INSERT INTO NSX VALUES('NSX1')
--INSERT INTO NSX VALUES('NSX2')
--INSERT INTO NSX VALUES('NSX3')
--INSERT INTO NSX VALUES('NSX4')
--INSERT INTO NSX VALUES('NSX5')
--INSERT INTO NSX VALUES('NSX6')

--INSERT INTO SIZE VALUES('S')
--INSERT INTO SIZE VALUES('L')
--INSERT INTO SIZE VALUES('XL')
--INSERT INTO SIZE VALUES('XXL')

--INSERT INTO MAUSAC VALUES(N'Xanh')
--INSERT INTO MAUSAC VALUES(N'Đỏ')
--INSERT INTO MAUSAC VALUES(N'Vàng')
--INSERT INTO MAUSAC VALUES(N'Tím')
--INSERT INTO MAUSAC VALUES(N'Hồng')
--INSERT INTO MAUSAC VALUES(N'Đen')
--INSERT INTO MAUSAC VALUES(N'Đỏ')
--INSERT INTO MAUSAC VALUES(N'Trắng')
--INSERT INTO MAUSAC VALUES(N'Cam')
--INSERT INTO MAUSAC VALUES(N'Bạc')
--INSERT INTO MAUSAC VALUES(N'Xám')



--INSERT INTO DONGSP VALUES(N'Áo đông')
--INSERT INTO DONGSP VALUES(N'Áo hè')

--INSERT INTO CHATLIEU VALUES(N'Vải Cotton')
--INSERT INTO CHATLIEU VALUES(N'Vải Kaki')
--INSERT INTO CHATLIEU VALUES(N'Vải Kate')
--INSERT INTO CHATLIEU VALUES(N'Vải Jean')
--INSERT INTO CHATLIEU VALUES(N'Vải Nỉ')
--INSERT INTO CHATLIEU VALUES(N'Vải Len')
--INSERT INTO CHATLIEU VALUES(N'Vải Thô (Canvas)')




--INSERT INTO NHANVIEN VALUES(N'NVI2','000000000000',N'Đoàn Đức Thiện',2,1,'2003-09-02','0987654321',N'Nam Định','thienddph27448@fpt.edu.vn',123,0)


--INSERT INTO KHUYENMAI VALUES(N'KM0','2022-12-10','2022-12-11',N'Giảm giá ngày 12-12',0,'2022-12-12','2022-12-13',1)
--INSERT INTO KHUYENMAI VALUES(N'KM1','2022-12-13','2022-12-14',N'Giảm giá ngày 20-12',8,'2022-12-20','2022-12-21',1)
--INSERT INTO KHUYENMAI VALUES(N'KM2','2022-12-15','2022-12-16',N'Giảm giá thi',12,'2022-12-12','2022-12-16',1)

--INSERT INTO KHACHHANG(MAKHACHHANG,TEN) VALUES('KH0',N'Khách lẻ')
----INSERT INTO KHACHHANG VALUES('KH1','0123456789123',N'Lỗ Chí Thâm','0123456789',0,N'Lương Sơn Bạc')
----INSERT INTO KHACHHANG VALUES('KH2','1123456789123',N'Võ Tòng','0987654321',0,N'Lương Sơn Bạc')
----INSERT INTO KHACHHANG VALUES('KH3','2123456789123',N'Tống Giang','0387654321',0,N'Lương Sơn Bạc')
--select * from KHACHHANG
--select * from NHANVIEN
--select * from HOADONCHITIET
--INSERT INTO CHITIETSP VALUES('SP1',1,1,1,1,1,1,'img1.png',553,50000,'165875',N'Không có') 
--INSERT INTO CHITIETSP VALUES('SP2',2,2,2,2,2,2,'img1.png',643,75000,'165875',N'Không có')
--INSERT INTO CHITIETSP VALUES('SP3',3,2,3,3,3,3,'img1.png',245,90000,'165875',N'Không có')
--select * from CHITIETSP
--INSERT INTO HOADON VALUES('HD154853',1,1,'0684569874',N'89 Lê Đức Thọ-Mỹ Đình',0,0,null,null,'2022-05-12',500000,0,'2022-05-12',1,500000,1,450000) 
--INSERT INTO HOADON VALUES('HD544677',2,2,'0947658544',N'14 Trần Bình-Cầu Giấy',0,0,null,null,'2022-05-12',300000,0,'2022-05-12',2,300000,1,290000)
--INSERT INTO HOADON VALUES('HD734563',2,1,'0947658544',N'17 Trần Bình-Cầu Giấy',1,2,null,'2022-05-12','2022-05-12',0,300000,'2022-05-12',2,300000,1,290000)

--INSERT INTO HOADONCHITIET VALUES(2,1,'TESTTENSP0','TESTTENNSX0','TESTDONGSP0','TESTMAUSAC0','TESTANH0','TESTSIZE0',6,99999,1) 
--INSERT INTO HOADONCHITIET VALUES(3,2,'TESTTENSP1','TESTTENNSX1','TESTDONGSP1','TESTMAUSAC1','TESTANH1','TESTSIZE1',8,88888,1) 
--INSERT INTO HOADONCHITIET VALUES(1,1,'TESTTENSP2','TESTTENNSX2','TESTDONGSP2','TESTMAUSAC2','TESTANH2','TESTSIZE2',2,55555,1) 
















--CREATE TABLE CALAM(
	
--)



--USE MASTER
--GO
--DROP DATABASE QUANLYBANQUANAO
