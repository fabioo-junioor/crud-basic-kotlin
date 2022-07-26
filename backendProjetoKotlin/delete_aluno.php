<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$matricula = $_POST["matricula"];

$sql2 = "SELECT `idAluno` FROM `aluno` WHERE matricula = '$matricula'";
$executa = mysqli_query($con, $sql2) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"matricula" : "achou"}';
        
    }    
    $sql = "DELETE FROM `aluno` WHERE idAluno = '".$result['idAluno']."'";
    $executa2 = mysqli_query($con, $sql) or die (mysqli_error());
    
    echo $saida;

} else {
    $saida = '';
    $saida .= '{"matricula" : "vazio"}';

    echo $saida;

}

mysqli_close($con);

?>