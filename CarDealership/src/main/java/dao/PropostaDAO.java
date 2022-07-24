package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Proposta;
import domain.Cliente;

public class PropostaDAO extends GenericDAO {

    public void insert(Proposta proposta) {

        String sql = "INSERT INTO Proposta ( valor, condPagamento, dataAtual, statusCompra, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, proposta.getValor());
            statement.setString(2, proposta.getCondPagamento());
            statement.setInt(3, proposta.getDataAtual());
            statement.setString(4, proposta.getStatus());
            statement.setLong(5, proposta.getCliente().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proposta> getAll() {

        List<Proposta> listaProposta = new ArrayList<>();

        String sql = "SELECT * from Proposta p, Cliente c where p.CLIENTE_ID = c.ID order by p.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int valor = resultSet.getInt("valor");
                String condPagamento = resultSet.getString("condPagamento");
                int dataAtual = resultSet.getInt("dataAtual");
                String status = resultSet.getString("statusCompra");
                Long cliente_id = resultSet.getLong(6);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                int cpf = resultSet.getInt("cpf");
                String nome =  resultSet.getString("nome");
                int telefone = resultSet.getInt("telefone");
                String sexo = resultSet.getString("sexo");
                int dataDeNascimento = resultSet.getInt("dataDeNascimento");
                Cliente cliente = new Cliente(cliente_id, email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
                Proposta proposta = new Proposta(id, valor, condPagamento, dataAtual, status, cliente);
                listaProposta.add(proposta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProposta;
    }

    public void delete(Proposta proposta) {
        String sql = "DELETE FROM Proposta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, proposta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Proposta proposta) {
        String sql = "UPDATE Proposta SET valor = ?, condPagamento = ?, dataAtual = ?, statusCompra = ?";
        sql += ", cliente_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, proposta.getValor());
            statement.setString(2, proposta.getCondPagamento());
            statement.setInt(3, proposta.getDataAtual());
            statement.setString(4, proposta.getStatus());
            statement.setLong(5, proposta.getCliente().getId());
            statement.setLong(6, proposta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Proposta get(Long id) {
        Proposta proposta = null;

        String sql = "SELECT * from Proposta p, Cliente c where p.id = ? and p.CLIENTE_ID = c.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	int valor = resultSet.getInt("valor");
                String condPagamento = resultSet.getString("condPagamento");
                int dataAtual = resultSet.getInt("dataAtual");
                String status = resultSet.getString("statusCompra");

                Long clienteID = resultSet.getLong("cliente_id");
                Cliente cliente = new ClienteDAO().get(clienteID);

                proposta = new Proposta(id, valor, condPagamento, dataAtual, status, cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proposta;
    }
}
