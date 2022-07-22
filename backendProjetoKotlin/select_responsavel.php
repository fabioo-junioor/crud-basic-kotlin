<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$nome = $_POST["nome"];
$email = $_POST["email"];
$cpf = $_POST["cpf"];

$sql = "SELECT * FROM `responsavel` WHERE cpf = '$cpf'";
$executa = mysqli_query($con, $sql) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"nome" : "'.$result['nome'].'",
        "email" : "'.$result['email'].'",
        "cpf" : "'.$result['cpf'].'"}';
        
    }
    
    echo $saida;
}else{
    $saida = '';
    $saida .= '
    {"nome" : "vazio",
    "email" : "vazio",
    "cpf" : "vazio"}';

    echo $saida;

}


mysqli_close($con);

?>
