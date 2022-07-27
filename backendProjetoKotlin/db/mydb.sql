-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 27-Jul-2022 às 15:30
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `mydb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE IF NOT EXISTS `aluno` (
  `idAluno` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `idResponsavel` int(11) NOT NULL,
  PRIMARY KEY (`idAluno`),
  UNIQUE KEY `idAluno` (`idAluno`),
  KEY `idResponsavel` (`idResponsavel`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`idAluno`, `matricula`, `nome`, `cpf`, `idResponsavel`) VALUES
(4, '2021', 'Fabio Junior', '1010101', 2),
(27, '656565', 'Maria Joaquina', '01144727006', 2),
(25, '22223', 'Paulo Costa', '111121', 5),
(29, '202010', 'Erico Silva', '123456789', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `nutricionista`
--

DROP TABLE IF EXISTS `nutricionista`;
CREATE TABLE IF NOT EXISTS `nutricionista` (
  `idNutricionista` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `registro` varchar(50) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`idNutricionista`),
  UNIQUE KEY `idNutricionista` (`idNutricionista`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `nutricionista`
--

INSERT INTO `nutricionista` (`idNutricionista`, `nome`, `email`, `registro`, `cpf`) VALUES
(1, 'Rochelle Pereira Quadros', 'rochelle@bol.com.br', 'RNN4587854', '55544466655'),
(3, 'Paula Costa', 'p.costa@gmail.com', 'RNN4512854', '12332112');

-- --------------------------------------------------------

--
-- Estrutura da tabela `observacao_aluno`
--

DROP TABLE IF EXISTS `observacao_aluno`;
CREATE TABLE IF NOT EXISTS `observacao_aluno` (
  `idObservacao` int(11) NOT NULL AUTO_INCREMENT,
  `obs` varchar(100) NOT NULL,
  `dataDia` varchar(10) NOT NULL,
  `idAluno` int(11) NOT NULL,
  `idNutricionista` int(11) NOT NULL,
  PRIMARY KEY (`idObservacao`),
  KEY `idAluno` (`idAluno`),
  KEY `idNutricionista` (`idNutricionista`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `observacao_aluno`
--

INSERT INTO `observacao_aluno` (`idObservacao`, `obs`, `dataDia`, `idAluno`, `idNutricionista`) VALUES
(9, 'aluno comeu d++', '30/05/2022', 12, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `registro_dia`
--

DROP TABLE IF EXISTS `registro_dia`;
CREATE TABLE IF NOT EXISTS `registro_dia` (
  `idRegistro` int(11) NOT NULL AUTO_INCREMENT,
  `carboidrato` varchar(50) NOT NULL,
  `proteina` varchar(50) NOT NULL,
  `salada` varchar(50) NOT NULL,
  `sobremesa` varchar(50) NOT NULL,
  `idNutricionista` int(11) NOT NULL,
  PRIMARY KEY (`idRegistro`),
  KEY `idNutricionista` (`idNutricionista`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `registro_dia`
--

INSERT INTO `registro_dia` (`idRegistro`, `carboidrato`, `proteina`, `salada`, `sobremesa`, `idNutricionista`) VALUES
(1, 'massa', 'carne bovina', 'repolho', 'pudim', 1),
(2, 'arroz', 'peixe', 'repolho', 'maça', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
CREATE TABLE IF NOT EXISTS `responsavel` (
  `idResponsavel` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`idResponsavel`),
  UNIQUE KEY `idResponsavel` (`idResponsavel`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `responsavel`
--

INSERT INTO `responsavel` (`idResponsavel`, `nome`, `email`, `cpf`) VALUES
(2, 'Maria Silva', 'maria.silva@bol.com.br', '11122233355'),
(3, 'Tulio Santos', 'tulio@bol.com.br', '4646'),
(5, 'Rosane Costa', 'zane@bol.com.br', '55555'),
(11, 'Augusto Martins', 'a.martins@gmail.com', '12312345');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
