-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-07-2025 a las 07:41:39
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `fastaccessapp`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historyuser`
--

CREATE TABLE `historyuser` (
  `id_history` int(11) NOT NULL,
  `date_time` varchar(25) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id_usuario` int(11) NOT NULL,
  `name_user` varchar(25) NOT NULL,
  `password_user` varchar(25) NOT NULL,
  `value_access` int(11) NOT NULL,
  `id_person` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id_usuario`, `name_user`, `password_user`, `value_access`, `id_person`) VALUES
(1, 'Sergio', '12345678', 2, 0),
(2, 'Prueba', '12345678', 5, 2),
(3, 'Facundo', '12345678', 0, 2),
(4, 'Matias', '12345678', 1, 2),
(5, 'Administrador', '12345678', 3, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `historyuser`
--
ALTER TABLE `historyuser`
  ADD PRIMARY KEY (`id_history`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historyuser`
--
ALTER TABLE `historyuser`
  MODIFY `id_history` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historyuser`
--
ALTER TABLE `historyuser`
  ADD CONSTRAINT `historyuser_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
