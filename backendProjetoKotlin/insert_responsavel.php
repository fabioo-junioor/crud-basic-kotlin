<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$nome = $_POST["nome"];
$email = $_POST["email"];
$cpf = $_POST["cpf"];

$sql2 = "SELECT `idResponsavel` FROM `responsavel` WHERE cpf = '$cpf'";
$executa = mysqli_query($con, $sql2) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"cpf" : "achou"}';
        
    }
    
    echo $saida;


}else{
    
    $sql = "INSERT INTO `responsavel` (nome, email, cpf)
    VALUES ('$nome', '$email', '$cpf')";

    $executa2 = mysqli_query($con, $sql) or die (mysqli_error());

    $saida = '';
    $saida .= '{"cpf" : "vazio"}';


    echo $saida;

}


mysqli_close($con);

?>