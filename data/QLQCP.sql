USE [master]
GO
/****** Object:  Database [QuanLyQuanCaPhe]    Script Date: 12/08/2021 6:31:39 PM ******/
CREATE DATABASE [QuanLyQuanCaPhe]
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyQuanCaPhe].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET QUERY_STORE = OFF
GO
USE [QuanLyQuanCaPhe]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/08/2021 6:31:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [int] IDENTITY(1,1) NOT NULL,
	[MaNV] [nvarchar](10) NULL,
	[MaSP] [nvarchar](10) NULL,
	[SoLuong] [int] NULL,
	[ThanhTien] [int] NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Loai]    Script Date: 12/08/2021 6:31:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai](
	[MaLoai] [nvarchar](50) NOT NULL,
	[TenLoai] [nvarchar](50) NULL,
 CONSTRAINT [PK_Loai] PRIMARY KEY CLUSTERED 
(
	[MaLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NguoiDung]    Script Date: 12/08/2021 6:31:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NguoiDung](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NULL,
	[ChucVu] [nvarchar](50) NULL,
 CONSTRAINT [PK_NguoiDung] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/08/2021 6:31:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](10) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[NamSinh] [nvarchar](10) NULL,
	[Email] [nvarchar](50) NULL,
	[SDT] [nvarchar](10) NULL,
	[Luong] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](10) NULL,
	[DiaChi] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/08/2021 6:31:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](10) NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[SoLuong] [nvarchar](50) NULL,
	[DonGia] [int] NULL,
	[LoaiSP] [nvarchar](50) NULL,
	[MoTa] [nvarchar](50) NULL,
	[Hinh] [nvarchar](50) NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [MaSP], [SoLuong], [ThanhTien]) VALUES (1, N'NV01', N'SP01', 2, 36000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [MaSP], [SoLuong], [ThanhTien]) VALUES (3, N'NV03', N'SP04', 2, 36000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [MaSP], [SoLuong], [ThanhTien]) VALUES (6, N'NV01', N'SP06', 1, 20000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [MaSP], [SoLuong], [ThanhTien]) VALUES (7, N'NV01', N'SP05', 1, 12000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [MaSP], [SoLuong], [ThanhTien]) VALUES (8, N'NV01', N'SP12', 2, 32000)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (N'001', N'Trà')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (N'002', N'Cà Phê')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (N'003', N'Nước ngọt')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (N'004', N'Nước lọc')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (N'005', N'Nước trái cây ')
GO
INSERT [dbo].[NguoiDung] ([Username], [Password], [ChucVu]) VALUES (N'admin', N'123', N'Quản lý')
INSERT [dbo].[NguoiDung] ([Username], [Password], [ChucVu]) VALUES (N'nv01', N'123', N'Nhân viên')
GO
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV01', N'Võ Lê Nhật Linh', N'2002', N'linhvln@gmail.com', N'0944694449', N'7000000', N'Nam', N'Kiên Giang')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV02', N'Lê Chí Bảo', N'2001', N'baolc@gmail.com', N'0946944490', N'5500000', N'Nam', N'Kiên Giang')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV03', N'Trần Khôn', N'2000', N'trankhon2k@gmail.com', N'0946944491', N'6000000', N'Nữ', N'Cần Thơ')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV04', N'Lê Thanh Tú', N'1997', N'tutt@gmail.com', N'0947003996', N'6500000', N'Nữ', N'Bạc Liêu')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV05', N'Cao Thái Hà', N'2002', N'thaiha24@gmail.com', N'0944444444', N'5000000', N'Nữ', N'Cần Thơ')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV07', N'Nguyễn Hữu Nghĩa', N'2002', N'thaiha@gmail.com', N'0944244445', N'6500000', N'Nam', N'Kiên Giang')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV08', N'Nguyễn Hữu B', N'2002', N'thai12@gmail.com', N'0944244425', N'5500000', N'Nam', N'Kiên Giang')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [NamSinh], [Email], [SDT], [Luong], [GioiTinh], [DiaChi]) VALUES (N'NV09', N'Trần Văn Thới', N'2002', N'thoit@gmail.com', N'0944244424', N'5000000', N'Nam', N'Kiên Giang')
GO
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP01', N'7up', N'12', 18000, N'003', N'Vị chanh', N'src\ImagesSanPham\sp1.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP02', N'Pepsi Cola', N'23', 22000, N'003', N'Có ga ,Thể tích 1.5 lít', N'src\ImagesSanPham\sp2.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP03', N'Fanta hương xá xị 390ml', N'30', 23000, N'003', N'Hương xá xị', N'src\ImagesSanPham\sp3.jfif')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP04', N'Cà phê đen ', N'10', 18000, N'002', N'Có vị đắng nhẹ', N'src\ImagesSanPham\sp4.png')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP05', N'Cà phê sữa', N'12', 12000, N'002', N'Cà phê pha với sữa đặc ', N'src\ImagesSanPham\sp5.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP06', N'Cà phê chồn', N'20', 20000, N'002', N'Có vị đắng chát', N'src\ImagesSanPham\sp6.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP07', N'Trà xanh', N'40', 15000, N'001', N'Hương thơm ngát', N'src\ImagesSanPham\sp7.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP08', N'Trà trái cây', N'18', 16000, N'001', N'Có các loại trái cây đi kèm', N'src\ImagesSanPham\sp8.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP09', N'Nuớc khoáng LaVie', N'20', 10000, N'004', N'Nước suối ', N'src\ImagesSanPham\sp9.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP10', N'Aquafina', N'15', 16000, N'003', N'Nước suối tinh khiết', N'src\ImagesSanPham\sp10.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP11', N'Aquafina', N'15', 16000, N'003', N'Nước suối tinh khiết', N'src\ImagesSanPham\sp11.png')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [SoLuong], [DonGia], [LoaiSP], [MoTa], [Hinh]) VALUES (N'SP12', N'Aquafina 2', N'20', 16000, N'004', N'Nước suối tinh khiết', N'src\ImagesSanPham\sp11.png')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Khoaduynhat_Email]    Script Date: 12/08/2021 6:31:39 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [Khoaduynhat_Email] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [khoaDuyNhat_SDT]    Script Date: 12/08/2021 6:31:39 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [khoaDuyNhat_SDT] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_SanPham]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_Loai_SP] FOREIGN KEY([LoaiSP])
REFERENCES [dbo].[Loai] ([MaLoai])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_Loai_SP]
GO
USE [master]
GO
ALTER DATABASE [QuanLyQuanCaPhe] SET  READ_WRITE 
GO
