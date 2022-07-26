<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$nome = $_POST["nome"];
$email = $_POST["email"];
$cpf = $_POST["cpf"];


$sql = "SELECT `idResponsavel` FROM `responsavel` WHERE cpf = '$cpf'";
$executa = mysqli_query($con, $sql) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"cpf" : "achou"}';
        
    }           
    $sql2 = "UPDATE `responsavel` SET nome = '$nome', email = '$email' WHERE cpf = '$cpf'";
    $executa2 = mysqli_query($con, $sql2) or die (mysqli_error());
    
    echo $saida;

} else {
    $saida = '';
    $saida .= '{"cpf" : "vazio"}';

    echo $saida;

}

mysqli_close($con);

?>