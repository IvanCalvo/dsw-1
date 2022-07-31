package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.Proposta;
import domain.Cliente;
import domain.Carro;

public class PropostaDAO extends GenericDAO {

    public void insert(Proposta proposta) {

        String sql = "INSERT INTO Proposta ( valor, condPagamento, dataAtual, statusCompra, cliente_id, carro_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setFloat(1, proposta.getValor());
            statement.setString(2, proposta.getCondPagamento());
            statement.setObject(3, proposta.getDataAtual());
            statement.setString(4, proposta.getStatus());
            statement.setLong(5, proposta.getCliente().getId_usuario());
            statement.setLong(6, proposta.getCarro().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proposta> getAll(Long identificador) {

        List<Proposta> listaProposta = new ArrayList<>();

        String sql = "SELECT * from Proposta p, Cliente c where p.CLIENTE_ID = c.ID order by p.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Float valor = resultSet.getFloat("valor");
                String condPagamento = resultSet.getString("condPagamento");
                LocalDate dataAtual = LocalDate.parse(resultSet.getString("dataAtual"));
                String status = resultSet.getString("statusCompra");
                Long cliente_id = resultSet.getLong(6);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome =  resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                LocalDate dataDeNascimento = LocalDate.parse(resultSet.getString("dataDeNascimento"));
                Long carro_id = resultSet.getLong("carro_id");
                
                Carro carro =  new CarroDAO().get(carro_id);
                Cliente cliente = new Cliente(cliente_id, cpf, nome, telefone, sexo, dataDeNascimento);
                Proposta proposta = new Proposta(id, valor, condPagamento, dataAtual, status, cliente, carro);
            	if( identificador == carro_id) {
            		listaProposta.add(proposta);
            	}
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProposta;
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
                        Float valor = resultSet.getFloat("valor");
                        String condPagamento = resultSet.getString("condPagamento");
                        LocalDate dataAtual = LocalDate.parse(resultSet.getString("dataAtual"));
                        String status = resultSet.getString("statusCompra");
                        Long cliente_id = resultSet.getLong(6);
                        String email = resultSet.getString("email");
                        String senha = resultSet.getString("senha");
                        String cpf = resultSet.getString("cpf");
                        String nome =  resultSet.getString("nome");
                        String telefone = resultSet.getString("telefone");
                        String sexo = resultSet.getString("sexo");
                        LocalDate dataDeNascimento = LocalDate.parse(resultSet.getString("dataDeNascimento"));
                        Long carro_id = resultSet.getLong("carro_id");
                        
                        Carro carro =  new CarroDAO().get(carro_id);
                        Cliente cliente = new Cliente(cliente_id, cpf, nome, telefone, sexo, dataDeNascimento);
                        Proposta proposta = new Proposta(id, valor, condPagamento, dataAtual, status, cliente, carro);        
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
        sql += ", cliente_id = ?, carro_id = ?, WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setFloat(1, proposta.getValor());
            statement.setString(2, proposta.getCondPagamento());
            statement.setObject(3, proposta.getDataAtual());
            statement.setString(4, proposta.getStatus());
            statement.setLong(5, proposta.getCliente().getId_usuario());
            statement.setLong(6, proposta.getCarro().getId());
            statement.setLong(7, proposta.getId());
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
            	Float valor = resultSet.getFloat("valor");
                String condPagamento = resultSet.getString("condPagamento");
                LocalDate dataAtual = LocalDate.parse(resultSet.getString("dataAtual"));
                String status = resultSet.getString("statusCompra");

                Long clienteID = resultSet.getLong("cliente_id");
                Long carroID = resultSet.getLong("carro_id");
                Cliente cliente = new ClienteDAO().get(clienteID);
                Carro carro = new CarroDAO().get(carroID);

                proposta = new Proposta(id, valor, condPagamento, dataAtual, status, cliente, carro);
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
