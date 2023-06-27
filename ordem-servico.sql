-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.24-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para ordem-servico
CREATE DATABASE IF NOT EXISTS `ordem-servico` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ordem-servico`;

-- Copiando estrutura para tabela ordem-servico.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `idClientes` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(200) DEFAULT NULL,
  `telefone` varchar(200) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idClientes`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.funcionario
CREATE TABLE IF NOT EXISTS `funcionario` (
  `idFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFunc` varchar(255) DEFAULT NULL,
  `dataAdmissao` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `salario` double(10,0) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.itensservico
CREATE TABLE IF NOT EXISTS `itensservico` (
  `iditensservico` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionario` int(11) NOT NULL DEFAULT 0,
  `idservico` int(11) NOT NULL,
  `idvenda` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL DEFAULT 0,
  `valor` int(11) NOT NULL,
  PRIMARY KEY (`iditensservico`),
  KEY `FK_itensservico_funcionario` (`idfuncionario`),
  KEY `FK_itensservico_servico` (`idservico`),
  KEY `FK_itensservico_venda` (`idvenda`),
  CONSTRAINT `FK_itensservico_funcionario` FOREIGN KEY (`idfuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_itensservico_servico` FOREIGN KEY (`idservico`) REFERENCES `servico` (`idservico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_itensservico_venda` FOREIGN KEY (`idvenda`) REFERENCES `venda` (`idVenda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.itensvenda
CREATE TABLE IF NOT EXISTS `itensvenda` (
  `iditensvenda` int(11) NOT NULL AUTO_INCREMENT,
  `venda_idVenda` int(11) NOT NULL,
  `produto_idProduto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`iditensvenda`),
  KEY `FK_itensvenda_venda` (`venda_idVenda`),
  KEY `FK_itensvenda_produto` (`produto_idProduto`),
  CONSTRAINT `FK_itensvenda_produto` FOREIGN KEY (`produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(45) NOT NULL,
  `valorProduto` decimal(3,0) NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.servico
CREATE TABLE IF NOT EXISTS `servico` (
  `idservico` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`idservico`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela ordem-servico.venda
CREATE TABLE IF NOT EXISTS `venda` (
  `idVenda` int(11) NOT NULL AUTO_INCREMENT,
  `idClientes` int(11) NOT NULL,
  `idFuncionario` int(11) NOT NULL,
  `dataVenda` date NOT NULL,
  PRIMARY KEY (`idVenda`),
  KEY `FK_venda_cliente` (`idClientes`),
  KEY `FK_venda_funcionario` (`idFuncionario`),
  CONSTRAINT `FK_venda_cliente` FOREIGN KEY (`idClientes`) REFERENCES `cliente` (`idClientes`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_venda_funcionario` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- Exportação de dados foi desmarcado.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
