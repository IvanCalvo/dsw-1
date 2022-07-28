package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarroDAO;
import dao.LojaDAO;
import dao.ClienteDAO;
import domain.Carro;
import domain.Loja;
import domain.Cliente;

@WebServlet(urlPatterns = "/cliente/*")

public class ClienteController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }


    private Map<Long, String> getLojas() {
        Map <Long,String> lojas = new HashMap<>();
        for (Loja loja: new LojaDAO().getAll()) {
            lojas.put(loja.getId(), loja.getNome());
        }
        return lojas;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        request.setAttribute("cliente", cliente);
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Integer cpf = Integer.parseInt(request.getParameter("cpf"));
        String nome = request.getParameter("nome");
        Integer telefone = Integer.parseInt(request.getParameter("telefone"));
        String sexo = request.getParameter("sexo");
        Integer dataDeNascimento = Integer.parseInt(request.getParameter("dataDeNascimento"));

        
        Cliente cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
        dao.insert(cliente);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Integer cpf = Integer.parseInt(request.getParameter("cpf"));
        String nome = request.getParameter("nome");
        Integer telefone = Integer.parseInt(request.getParameter("telefone"));
        String sexo = request.getParameter("sexo");
        Integer dataDeNascimento = Integer.parseInt(request.getParameter("dataDeNascimento"));
        
        
        Cliente cliente = new Cliente(id, email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
        dao.update(cliente);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Cliente cliente = new Cliente(id);
        dao.delete(cliente);
        response.sendRedirect("lista");
    }
}
