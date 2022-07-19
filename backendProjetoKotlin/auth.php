<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$sql = "SELECT * FROM `aluno`";

$executa = mysqli_query($con, $sql) or die (mysqli_error());

$total = mysqli_num_rows($executa);
    if($total > 0){
        $saida = '';
        for($i=0; $i < $total; $i++){
            $result = mysqli_fetch_assoc($executa);
            
            echo $result['idAluno'], "\n";
            echo $result['nome'], "\n";
            echo $result['cpf'], "\n";
            echo $result['idResponsavel'], "\n \n";

        }       

    }else{
        echo "ERRO!!!";

    }
?>