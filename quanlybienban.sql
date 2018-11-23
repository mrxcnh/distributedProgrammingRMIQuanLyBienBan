-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 23, 2018 at 10:17 AM
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
(1, 2, 'Meeting ve ABC', '2018-11-21', '09:00:00'),
(3, 2, 'Hop CDE', '2018-11-13', '08:00:00'),
(8, 2, 'meeting 8', '2018-11-14', '18:00:00'),
(14, 2, 'meeting 15', '2018-11-14', '19:00:00'),
(16, 2, 'meeting 17', '2018-11-14', '15:00:00'),
(17, 2, 'Meeting 18', '2018-11-14', '13:00:00'),
(18, 2, 'Meeting ve ABC', '2018-11-21', '09:00:00'),
(19, 2, 'Hop ve Mid term', '2018-11-22', '10:00:00'),
(20, 8, 'Hop ve aaaa', '2018-11-14', '08:00:00');

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
(41, 3, 'CTver1.txt', 1, '123dascascsacascsacklsmcalkcdscaml[00:01:30~00:02:20]\n\nascamdklsclk124123r233rkml[00:01:30~00:02:20]\n\ncsclakmsdaklmmdksmclaksmdcasdl[00:01:25~00:01:50]\n\ncsakclamckdalmcsd[00:00:59~00:01:16]\n'),
(42, 3, 'Transcript.txt', 2, '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n'),
(44, 8, 'Transcript.txt', 2, '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n');

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
(96, 3, 'Hop CDETranscript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '09:32:35', 'thanhdovan'),
(97, 8, 'meeting 8Transcript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/23 09:40:34', 'thanhdovan'),
(98, 8, 'meeting 8Transcript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsaaa', '2018/11/23 09:40:54', 'thanhdovan'),
(99, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/23 09:41:43', 'thanhdovan'),
(100, 8, 'meeting 8Transcript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsdaylatsua', '2018/11/23 09:48:48', 'thanhdovan'),
(101, 1, '', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r23', '2018/11/23 09:49:58', 'thanhdovan'),
(102, 3, 'MID3Report', '[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n[00:01:30~00:02:20]Long-123dascascsacascsacklsmcalkcdscamlascamdklsclk124123r233rkml\n', '2018/11/23 09:55:42', 'thanhdovan'),
(103, 3, 'Hop CDETranscript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/23 09:56:31', 'thanhdovan'),
(104, 8, 'meeting 8Transcript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/23 09:59:00', 'thanhdovan'),
(105, 8, 'meeting 8Transcript', '[00:01:30~00:02:20]Thanh-123dascascsacascsacklsmcalkcdscaml\n[00:01:30~00:02:20]Long-ascamdklsclk124123r233rkml\n[00:01:25~00:01:50]Canh-csclakmsdaklmmdksmclaksmdcasdl\n[00:00:59~00:01:16]Hoang-csakclamckdalmcsd\n\n', '2018/11/23 10:00:33', 'thanhdovan');

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
(154, 10, 17, 'u'),
(156, 11, 17, 'u'),
(158, 1, 3, 'u'),
(163, 6, 8, 'r'),
(164, 6, 14, 'r'),
(165, 6, 16, 'r'),
(167, 6, 1, 'r'),
(170, 8, 14, 'r'),
(173, 8, 8, 'r'),
(174, 1, 8, 'u'),
(175, 10, 8, 'u'),
(176, 4, 3, 'u'),
(177, 4, 8, 'u'),
(178, 1, 19, 'u');

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
(10, 'staff1', '81dc9bdb52d04dc20036dbd8313ed055', 'staff1', 'staff'),
(11, 'staff2', '81dc9bdb52d04dc20036dbd8313ed055', 'staff2', 'staff'),
(12, 'admin2', '81dc9bdb52d04dc20036dbd8313ed055', 'admin', 'admin');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `peopleeditreport`
--
ALTER TABLE `peopleeditreport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reportparts`
--
ALTER TABLE `reportparts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT for table `userpermission`
--
ALTER TABLE `userpermission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179;

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
