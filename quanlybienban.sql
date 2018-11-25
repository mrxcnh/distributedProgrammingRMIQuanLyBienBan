-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 25, 2018 at 12:25 PM
-- Server version: 5.7.24-0ubuntu0.18.04.1
-- PHP Version: 7.2.10-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlybienban`
--

-- --------------------------------------------------------

--
-- Table structure for table `meetings`
--

CREATE TABLE `meetings` (
  `id` int(11) NOT NULL,
  `userCreateId` int(11) NOT NULL,
  `meetingTitle` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `meetingDate` date NOT NULL,
  `timeStart` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `meetings`
--

INSERT INTO `meetings` (`id`, `userCreateId`, `meetingTitle`, `meetingDate`, `timeStart`) VALUES
(1, 2, 'Meeting1', '2018-11-21', '09:00:00'),
(3, 2, 'Hop CDE', '2018-11-13', '08:00:00'),
(18, 2, 'Hop CDE', '2018-11-13', '08:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `peopleeditreport`
--

CREATE TABLE `peopleeditreport` (
  `id` int(11) NOT NULL,
  `reportId` int(11) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reportparts`
--

CREATE TABLE `reportparts` (
  `id` int(11) NOT NULL,
  `meetingId` int(11) NOT NULL,
  `fileName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `reportPartContent` mediumtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reportparts`
--

INSERT INTO `reportparts` (`id`, `meetingId`, `fileName`, `type`, `reportPartContent`) VALUES
(29, 1, 'Transcript.txt', 2, '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n'),
(35, 1, 'PTver1.txt', 0, 'Thanh[00:01:30~00:02:20]\nLong[00:01:30~00:02:20]\n\nCanh[00:01:25~00:01:50]\n\nHoang[00:00:59~00:01:16]\n'),
(39, 1, 'CTver1.txt', 1, '123dascascsacascsacklsmcalkcdscaml[00:01:30~00:02:20]\n\nascamdklsclk124123r233rkml[00:01:30~00:02:20]\n\ncsclakmsdaklmmdksmclaksmdcasdl[00:01:25~00:01:50]\n\ncsakclamckdalmcsd[00:00:59~00:01:16]\n'),
(40, 3, 'PTver1.txt', 0, 'Thanh[00:01:30~00:02:20]\nLong[00:01:30~00:02:20]\n\nCanh[00:01:25~00:01:50]\n\nHoang[00:00:59~00:01:16]\n'),
(52, 3, 'CTver1.txt', 1, '123dascascsacascsacklsmcalkcdscaml[00:01:30~00:02:20]\n\nascamdklsclk124123r233rkml[00:01:30~00:02:20]\n\ncsclakmsdaklmmdksmclaksmdcasdl[00:01:25~00:01:50]\n\ncsakclamckdalmcsd[00:00:59~00:01:16]\n'),
(65, 1, 'PTver1.txt', 0, 'Thanh[00:01:30~00:02:20]\nLong[00:01:30~00:02:20]\n\nCanh[00:01:25~00:01:50]\n\nHoang[00:00:59~00:01:16]\n'),
(70, 3, 'Transcript.txt', 2, '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `meetingId` int(11) NOT NULL,
  `reportName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `reportContent` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `timeCreate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `authors` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `meetingId`, `reportName`, `reportContent`, `timeCreate`, `authors`) VALUES
(89, 1, 'ReportMid1', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-ascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n', '04:01:41', 'thanhdovan'),
(90, 1, 'Mid1Report', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '08:51:09', 'thanhdovan'),
(92, 1, 'Mid1Report', '[00:00:59~00:01:16]Hoang-Chao moi nguoi\n[00:01:25~00:01:50]Canh-Chao moi nguoi\n[00:01:30~00:02:20]Thanh-alkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-Chao moi nguoi', '08:57:23', 'thanhdovan'),
(93, 1, 'Mid1Report', '[00:00:59~00:01:16]Hoang-Chao moi nguoi\n[00:01:25~00:01:50]Canh-Chao moi nguoi\n[00:01:30~00:02:20]Thanh-Chao moi nguoi\n[00:01:30~00:02:20]Long-Chao moi nguoi', '09:07:02', 'thanhdovan'),
(94, 1, 'Mid1Report', '[00:00:59~00:01:16]Hoang-Chao moi nguoi\n[00:01:25~00:01:50]Canh-Chao moi nguoi\n[00:01:30~00:02:20]Thanh-Chao moi nguoi\n[00:01:30~00:02:20]Long-Chao moi nguoi', '09:07:39', 'longlengoc'),
(95, 1, 'Mid1Report', '[00:00:59~00:01:16]Hoang-Chao moi nguoi\n[00:01:25~00:01:50]Canh-Chao moi nguoi\n[00:01:30~00:02:20]Thanh-Chao moi nguoi\n[00:01:30~00:02:20]Long-Chao moi nguoimmm', '09:08:03', 'longlengoc'),
(99, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/23 09:41:43', 'thanhdovan'),
(101, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r23', '2018/11/23 09:49:58', 'thanhdovan'),
(102, 3, 'MID3Report', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/23 09:55:42', 'thanhdovan'),
(103, 3, 'Hop CDETranscript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/23 09:56:31', 'thanhdovan'),
(106, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r23111', '2018/11/23 16:45:24', 'thanhdovan'),
(107, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123', '2018/11/24 09:55:55', 'longlengoc'),
(108, 1, 'ABC', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/24 10:02:34', 'longlengoc'),
(109, 3, 'MID3REPORT', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/24 14:56:26', 'thanhdovan'),
(121, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascaalascdsacmdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r23111', '2018/11/24 21:08:43', 'thanhdovan'),
(124, 3, 'Hop CDETranscript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\r\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\r\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\r\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\r\n\r\n', '2018/11/25 12:18:59', 'thanhdovan'),
(125, 3, 'Hop CDETranscript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/25 12:19:44', 'longlengoc');

-- --------------------------------------------------------

--
-- Table structure for table `userpermission`
--

CREATE TABLE `userpermission` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `meetingId` int(11) NOT NULL,
  `permission` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `userpermission`
--

INSERT INTO `userpermission` (`id`, `userId`, `meetingId`, `permission`) VALUES
(146, 1, 1, 'u'),
(149, 4, 1, 'u'),
(167, 6, 1, 'r'),
(179, 8, 1, 'r'),
(200, 6, 3, 'r'),
(208, 4, 3, 'u'),
(217, 10, 3, 'u'),
(236, 1, 3, 'u'),
(238, 1, 18, 'u');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `position` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `position`) VALUES
(1, 'thanhdovan', '81dc9bdb52d04dc20036dbd8313ed055', 'thanh', 'staff'),
(2, 'canhnguyenduc', '81dc9bdb52d04dc20036dbd8313ed055', 'canh', 'manager'),
(4, 'longlengoc', '81dc9bdb52d04dc20036dbd8313ed055', 'long', 'staff'),
(6, 'hoangnguyentrong', '81dc9bdb52d04dc20036dbd8313ed055', 'hoang', 'manager'),
(7, 'admin1', '81dc9bdb52d04dc20036dbd8313ed055', 'admin1', 'admin'),
(8, 'manager', '81dc9bdb52d04dc20036dbd8313ed055', 'manager', 'manager'),
(9, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'admin', 'admin'),
(10, 'staff1', '81dc9bdb52d04dc20036dbd8313ed055', 'staff66666', 'staff'),
(11, 'staff2', '81dc9bdb52d04dc20036dbd8313ed055', 'staff2', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `meetings`
--
ALTER TABLE `meetings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_create_id` (`userCreateId`);

--
-- Indexes for table `peopleeditreport`
--
ALTER TABLE `peopleeditreport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_report_id` (`reportId`),
  ADD KEY `fk_userId` (`userId`);

--
-- Indexes for table `reportparts`
--
ALTER TABLE `reportparts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_meetingId` (`meetingId`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_meeting_id` (`meetingId`);

--
-- Indexes for table `userpermission`
--
ALTER TABLE `userpermission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkUserId` (`userId`),
  ADD KEY `fkMeetingId` (`meetingId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `meetings`
--
ALTER TABLE `meetings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `peopleeditreport`
--
ALTER TABLE `peopleeditreport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reportparts`
--
ALTER TABLE `reportparts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT for table `userpermission`
--
ALTER TABLE `userpermission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=239;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `meetings`
--
ALTER TABLE `meetings`
  ADD CONSTRAINT `fk_user_create_id` FOREIGN KEY (`userCreateId`) REFERENCES `users` (`id`);

--
-- Constraints for table `peopleeditreport`
--
ALTER TABLE `peopleeditreport`
  ADD CONSTRAINT `fk_report_id` FOREIGN KEY (`reportId`) REFERENCES `reports` (`id`),
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `reportparts`
--
ALTER TABLE `reportparts`
  ADD CONSTRAINT `fk_meetingId` FOREIGN KEY (`meetingId`) REFERENCES `meetings` (`id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `fk_meeting_id` FOREIGN KEY (`meetingId`) REFERENCES `meetings` (`id`);

--
-- Constraints for table `userpermission`
--
ALTER TABLE `userpermission`
  ADD CONSTRAINT `fkMeetingId` FOREIGN KEY (`meetingId`) REFERENCES `meetings` (`id`),
  ADD CONSTRAINT `fkUserId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
