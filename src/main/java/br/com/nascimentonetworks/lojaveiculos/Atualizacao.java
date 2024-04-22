package br.com.nascimentonetworks.lojaveiculos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Atualizacao {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "123";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o código do veículo que deseja atualizar: ");
            int codigo = sc.nextInt();
            sc.nextLine(); // Limpar o buffer de entrada
            
            // Verificar se o veículo existe
            if (existeVeiculo(conn, codigo)) {
                // Solicitar novos dados
                System.out.println("Digite a nova marca: ");
                String novaMarca = sc.nextLine();
                System.out.println("Digite o novo modelo: ");
                String novoModelo = sc.nextLine();
                System.out.println("Digite o novo ano: ");
                int novoAno = sc.nextInt();
                System.out.println("Digite o novo preço: ");
                int novoPreco = sc.nextInt();
                sc.nextLine(); // Limpar o buffer de entrada
                System.out.println("Digite a nova placa: ");
                String novaPlaca = sc.nextLine();
                
                // Atualizar os dados do veículo
                atualizarVeiculo(conn, codigo, novaMarca, novoModelo, novoAno, novoPreco, novaPlaca);
                System.out.println("Veículo atualizado com sucesso.");
            } else {
                System.out.println("Nenhum veículo encontrado com o código informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static boolean existeVeiculo(Connection conn, int codigo) throws SQLException {
        String sql = "SELECT 1 FROM carros WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void atualizarVeiculo(Connection conn, int codigo, String novaMarca, String novoModelo,
            int novoAno, int novoPreco, String novaPlaca) throws SQLException {
        String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ?, preco = ?, placa = ? WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, novaMarca);
            preparedStatement.setString(2, novoModelo);
            preparedStatement.setInt(3, novoAno);
            preparedStatement.setInt(4, novoPreco);
            preparedStatement.setString(5, novaPlaca);
            preparedStatement.setInt(6, codigo);

            preparedStatement.executeUpdate();
        }
    }
}

