<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$matricula = $_POST["matricula"];
$nome = $_POST["nome"];
$cpf = $_POST["cpf"];
$responsavel = $_POST["responsavel"];

$sql = "SELECT `idAluno` FROM `aluno` WHERE matricula = '$matricula'";
$executa = mysqli_query($con, $sql) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"matricula" : "achou", "responsavel" : "vazio"}';
        
    }    
    echo $saida;

} else {
    $sql2 = "SELECT `idResponsavel` FROM `responsavel` WHERE nome = '$responsavel'";
    $executa2 = mysqli_query($con, $sql2) or die (mysqli_error());
    $total = mysqli_num_rows($executa2);
    if($total > 0){
        $saida = '';
        for($i=0; $i<$total; $i++){
            $result = mysqli_fetch_assoc($executa2);
            $saida .= '{"matricula" : "vazio", "responsavel" : "achou"}';
            
            
            $sql2 = "INSERT INTO `aluno` (matricula, nome, cpf, idResponsavel)
            VALUES ('$matricula', '$nome', '$cpf', '".$result['idResponsavel']."')";

            $executa2 = mysqli_query($con, $sql2) or die (mysqli_error());
      
        }
        echo $saida;
        

    } else {
        $saida = '';
        $saida .= '{"matricula" : "vazio", "responsavel" : "vazio"}';
        echo $saida;
    
    }    
}


mysqli_close($con);

?>
