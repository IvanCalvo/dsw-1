package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (email, senha, cpf, nome, telefone, sexo, dataDeNascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setInt(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setInt(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setInt(7, cliente.getDataDeNascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, dataDeNascimento = ?";
        sql += "WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setInt(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setInt(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setInt(7, cliente.getDataDeNascimento());
            statement.setLong(8, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                int cpf = resultSet.getInt("cpf");
                String nome = resultSet.getString("nome");
                int telefone = resultSet.getInt("telefone");
                String sexo = resultSet.getString("sexo");
                int dataDeNascimento = resultSet.getInt("dataDeNascimento");

                cliente = new Cliente(id, email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
