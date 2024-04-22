package br.com.nascimentonetworks.lojaveiculos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Consulta {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "123";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o código do veículo que deseja consultar: ");
            int codigo = sc.nextInt();

            String sql = "SELECT * FROM carros WHERE codigo = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, codigo);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String marca = resultSet.getString("marca");
                        String modelo = resultSet.getString("modelo");
                        int ano = resultSet.getInt("ano");
                        int preco = resultSet.getInt("preco");
                        String placa = resultSet.getString("placa");
                        
                        System.out.println("Marca: " + marca);
                        System.out.println("Modelo: " + modelo);
                        System.out.println("Ano: " + ano);
                        System.out.println("Preço: " + preco);
                        System.out.println("Placa: " + placa);
                    } else {
                        System.out.println("Nenhum veículo encontrado com o código informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar banco de dados: " + e.getMessage());
        }
    }
}

