package outras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    public static void main(String[] args){

        System.out.println("Começou...");
        conexao();

    }

    public static void conexao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            Statement sta = connection.createStatement();
            ResultSet result = sta.executeQuery("select * from aluno");

            while (result.next()){
                System.out.println(result.getString("nome"));

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nao conectado");

        }
    }
}