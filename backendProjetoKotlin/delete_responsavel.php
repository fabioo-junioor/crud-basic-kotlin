<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$cpf = $_POST["cpf"];
$nome = $_POST["nome"];

$sql = "SELECT `idResponsavel` FROM `responsavel` WHERE cpf = '$cpf'";
$executa = mysqli_query($con, $sql) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"cpf" : "achou", "nome" : "vazio"}';
        
    }

    $sql2 = "SELECT responsavel.`idResponsavel`, aluno.`nome` nomeAluno
            FROM `responsavel`, `aluno`
            WHERE responsavel.`idResponsavel` = aluno.`idResponsavel`
            AND responsavel.`cpf` = '$cpf'";
    $executa2 = mysqli_query($con, $sql2) or die (mysqli_error());
    $total2 = mysqli_num_rows($executa2);

    if ($total2 > 0) {
        $saida = '';
        for($i=0; $i<$total2; $i++){
            $result2 = mysqli_fetch_assoc($executa2);
            //$saida .= '{"cpf" : "achouID", "nome" : "'.$result2['nomeAluno'].'"}';
            
        }
        $saida .= '{"cpf" : "achouID", "nome" : "'.$result2['nomeAluno'].'"}';
        echo $saida;
        

    } else {
        
        $sql3 = "DELETE FROM `responsavel` WHERE idResponsavel = '".$result['idResponsavel']."'";
        $executa3 = mysqli_query($con, $sql3) or die (mysqli_error());
        
        $saida = '';
        $saida .= '{"cpf" : "achou", "nome" : "vazio"}';
        
        echo $saida;   
    }
    
    
}else{
    $saida = '';
    $saida .= '{"cpf" : "vazio", "nome" : "vazio"}';

    echo $saida;

}

/*
select responsavel.idResponsavel
from responsavel, aluno
where responsavel.idResponsavel = aluno.idResponsavel
and responsavel.cpf = '999';
*/

mysqli_close($con);

?>
