package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Loja;
import java.sql.Connection;

public class LojaDAO extends GenericDAO{
	
	public void insert(Loja loja) {

        String sql = "INSERT INTO lojas (nome, email, senha, descricao, cnpj) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, loja.getNome());
            statement.setString(2, loja.getEmail());
            statement.setString(3, loja.getSenha());
            statement.setString(4, loja.getDescricao());
            statement.setInt(5, loja.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Loja> getAll() {

        List<Loja> listaLojas = new ArrayList<>();

        String sql = "SELECT * from lojas";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String descricao = resultSet.getString("descricao");
                int cnpj = resultSet.getInt("cnpj");
                
                Loja loja = new Loja(id, nome, email, senha, descricao, cnpj);
                listaLojas.add(loja);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLojas;
    }
	
	public void delete(Loja loja) {
		//Só vai funcionar se loja não tiver mais nenhum carro.
        String sql = "DELETE FROM lojas where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, loja.getId_loja());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void update(Loja loja) {
        String sql = "UPDATE lojas SET nome = ?, email = ?, senha = ?, descricao = ?";
        sql += ", cnpj = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, loja.getNome());
            statement.setString(2, loja.getEmail());
            statement.setString(3, loja.getSenha());
            statement.setString(4, loja.getDescricao());
            statement.setInt(5, loja.getCnpj());

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Loja get(int id) {
        Loja loja = null;
        
        String sql = "SELECT * from lojas where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String descricao = resultSet.getString("descricao");
                int cnpj = resultSet.getInt("cnpj");
                loja = new Loja(id, nome, email, senha, descricao, cnpj);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loja;
    }
}
