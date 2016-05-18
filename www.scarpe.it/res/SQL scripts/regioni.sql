CREATE TABLE IF NOT EXISTS `regioni` (
  `idRegione` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `abb` varchar(4) NOT NULL,
  `nomeRegione` varchar(200) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`idRegione`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

INSERT INTO `regioni` (`idRegione`, `abb`, `nomeRegione`) VALUES
(1, 'ABR', 'Abruzzo'),
(2, 'BAS', 'Basilicata'),
(3, 'CAL', 'Calabria'),
(4, 'CAM', 'Campania'),
(5, 'EMR', 'Emilia-Romagna'),
(6, 'FVG', 'Friuli-Venezia Giulia'),
(7, 'LAZ', 'Lazio'),
(8, 'LIG', 'Liguria'),
(9, 'LOM', 'Lombardia'),
(10, 'MAR', 'Marche'),
(11, 'MOL', 'Molise'),
(12, 'PIE', 'Piemonte'),
(13, 'PUG', 'Puglia'),
(14, 'SAR', 'Sardegna'),
(15, 'SIC', 'Sicilia'),
(16, 'TOS', 'Toscana'),
(17, 'TAA', 'Trentino-Alto Adige'),
(18, 'UMB', 'Umbria'),
(19, 'VDA', 'Valle d''Aosta'),
(20, 'VEN', 'Veneto');
