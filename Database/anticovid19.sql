-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 12, 2021 lúc 03:36 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `anticovid19`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `ad_id` int(11) NOT NULL,
  `ad_email` varchar(30) NOT NULL,
  `ad_password` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`ad_id`, `ad_email`, `ad_password`) VALUES
(1, '1', 'e3124393df49c811ec6ce36e4eaa5246a4e69883299788dcc257228ccc82512ef93f026b3a6b2bb4edb37d22adb3745b45037befa468459b84d5e204c2bb1125');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diachi`
--

CREATE TABLE `diachi` (
  `diachi_id` int(11) NOT NULL,
  `tinh` varchar(30) NOT NULL,
  `quan` varchar(30) NOT NULL,
  `phuong` varchar(30) NOT NULL,
  `soNha` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diadiemcachli`
--

CREATE TABLE `diadiemcachli` (
  `diadiemcachli_id` int(11) NOT NULL,
  `diadiem` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khaibaoyte`
--

CREATE TABLE `khaibaoyte` (
  `nhankhau_id` int(11) NOT NULL,
  `khaibaoyte_id` int(11) NOT NULL,
  `codiquavungdich` int(11) NOT NULL,
  `cotrieuchung` int(11) NOT NULL,
  `tiepxucnguoimaccovid` int(11) NOT NULL,
  `tiepxucnguoituvungdich` int(11) NOT NULL,
  `tiepxucnguoicobieuhien` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhankhau`
--

CREATE TABLE `nhankhau` (
  `nhankhau_id` int(11) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `gioitinh` varchar(10) NOT NULL,
  `CMND` varchar(20) NOT NULL,
  `diachi_id` int(11) DEFAULT NULL,
  `SDT` varchar(15) NOT NULL,
  `datest` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongketiemchung`
--

CREATE TABLE `thongketiemchung` (
  `nhankhau_id` int(11) NOT NULL,
  `thongke_id` int(11) NOT NULL,
  `datiem` int(11) NOT NULL,
  `somuitiem` int(11) NOT NULL,
  `loaivacxinlan1` varchar(20) NOT NULL,
  `thoigiantiemlan1` date NOT NULL,
  `loaivacxinlan2` varchar(20) NOT NULL,
  `thoigiantiemlan2` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtincachli`
--

CREATE TABLE `thongtincachli` (
  `thongtincachli_id` int(11) NOT NULL,
  `nhankhau_id` int(11) NOT NULL,
  `thoigianbatdau` date NOT NULL,
  `thoigiancachli` int(11) NOT NULL,
  `diadiemcachli_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtintest`
--

CREATE TABLE `thongtintest` (
  `thongtintest_id` int(11) NOT NULL,
  `nhankhau_id` int(11) NOT NULL,
  `thoigiantest` date NOT NULL,
  `hinhthuctest` int(11) NOT NULL,
  `ketquatest` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ad_id`);

--
-- Chỉ mục cho bảng `diachi`
--
ALTER TABLE `diachi`
  ADD PRIMARY KEY (`diachi_id`);

--
-- Chỉ mục cho bảng `khaibaoyte`
--
ALTER TABLE `khaibaoyte`
  ADD PRIMARY KEY (`khaibaoyte_id`);

--
-- Chỉ mục cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`nhankhau_id`);

--
-- Chỉ mục cho bảng `thongketiemchung`
--
ALTER TABLE `thongketiemchung`
  ADD PRIMARY KEY (`thongke_id`);

--
-- Chỉ mục cho bảng `thongtincachli`
--
ALTER TABLE `thongtincachli`
  ADD PRIMARY KEY (`thongtincachli_id`);

--
-- Chỉ mục cho bảng `thongtintest`
--
ALTER TABLE `thongtintest`
  ADD PRIMARY KEY (`thongtintest_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `ad_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `diachi`
--
ALTER TABLE `diachi`
  MODIFY `diachi_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khaibaoyte`
--
ALTER TABLE `khaibaoyte`
  MODIFY `khaibaoyte_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `nhankhau_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `thongketiemchung`
--
ALTER TABLE `thongketiemchung`
  MODIFY `thongke_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `thongtincachli`
--
ALTER TABLE `thongtincachli`
  MODIFY `thongtincachli_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `thongtintest`
--
ALTER TABLE `thongtintest`
  MODIFY `thongtintest_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
