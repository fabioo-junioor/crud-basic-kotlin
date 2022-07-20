<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$matricula = $_POST["matricula"];
$nome = $_POST["nome"];
$cpf = $_POST["cpf"];
$responsavel = $_POST["responsavel"];

/*
$matricula = "2021";
//$nome = $_POST["nome"];
//$cpf = $_POST["cpf"];
$responsavel = "rosane";
*/
$sql2 = "SELECT `idAluno` FROM `aluno` WHERE matricula = '$matricula'";
$executa = mysqli_query($con, $sql2) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"matricula" : "achou", "responsavel" : "vazio"}';
        
    }
    $sql3 = "SELECT `idResponsavel` FROM `responsavel` WHERE nome = '$responsavel'";
    $executa3 = mysqli_query($con, $sql3) or die (mysqli_error());
    $total2 = mysqli_num_rows($executa3);
    if($total2 > 0){
        $saida = '';
        for($i=0; $i<$total; $i++){
            $result = mysqli_fetch_assoc($executa3);
            $saida .= '{"matricula" : "achou", "responsavel" : "achou"}';
            
            $sql = "UPDATE `aluno` SET nome = '$nome', cpf = '$cpf', idResponsavel = '".$result['idResponsavel']."' WHERE matricula = '$matricula'";
            $executa2 = mysqli_query($con, $sql) or die (mysqli_error());
        }
    } else {
        $saida = '';
        $saida .= '{"matricula" : "achou", "responsavel" : "vazio"}';


    }
    
    
    
    echo $saida;
}else{
    $saida = '';
    $saida .= '{"matricula" : "vazio", "responsavel" : "vazio"}';

    echo $saida;

}


mysqli_close($con);

?>