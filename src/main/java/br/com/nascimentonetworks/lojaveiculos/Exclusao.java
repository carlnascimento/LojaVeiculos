package br.com.nascimentonetworks.lojaveiculos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Exclusao {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "123";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o código do veículo que deseja excluir: ");
            int codigo = sc.nextInt();

            if (excluirVeiculo(conn, codigo)) {
                System.out.println("Veículo excluído com sucesso.");
            } else {
                System.out.println("Nenhum veículo encontrado com o código informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static boolean excluirVeiculo(Connection conn, int codigo) throws SQLException {
        String sql = "DELETE FROM carros WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}

