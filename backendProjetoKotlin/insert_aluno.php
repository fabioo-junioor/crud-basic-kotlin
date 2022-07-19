<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$nome = $_POST["nome"];
$cpf = $_POST["cpf"];
$matricula = $_POST["matricula"];
$responsavel = $_POST["responsavel"];

$sql = "INSERT INTO `aluno` (matricula, nome, cpf, idResponsavel)
    VALUES ('$matricula', '$nome', '$cpf', '50')";

$executa = mysqli_query($con, $sql) or die (mysqli_error());

mysqli_close($con);

?>
