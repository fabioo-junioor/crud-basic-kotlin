<?php
header('Content-Type: application/json; Charset=UTF-8');
include("connection.php");

$matricula = $_POST["matricula"];
$nome = $_POST["nome"];
$cpf = $_POST["cpf"];
$responsavel = $_POST["responsavel"];

$sql = "SELECT aluno.`nome`, aluno.`matricula`, aluno.`cpf`, responsavel.`nome` nomeResp 
        FROM aluno, responsavel
        WHERE aluno.`idResponsavel` = responsavel.`idResponsavel`
        AND aluno.`matricula` = '$matricula'";
$executa = mysqli_query($con, $sql) or die (mysqli_error());
$total = mysqli_num_rows($executa);

if ($total > 0) {
    $saida = '';
    for($i=0; $i<$total; $i++){
        $result = mysqli_fetch_assoc($executa);
        $saida .= '{"nome" : "'.$result['nome'].'",
        "matricula" : "'.$result['matricula'].'",
        "cpf" : "'.$result['cpf'].'",
        "responsavel" : "'.$result['nomeResp'].'"}';
        
    }
    
    echo $saida;

} else {
    $saida = '';
    $saida .= '
    {"nome" : "vazio",
    "matricula" : "vazio",
    "cpf" : "vazio",
    "responsavel" : "vazio"}';

    echo $saida;

}

mysqli_close($con);

?>
