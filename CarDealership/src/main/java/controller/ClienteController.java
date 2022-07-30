package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.UsuarioDAO;
import domain.Cliente;
import domain.Usuario;

@WebServlet(urlPatterns = "/cliente/*")

public class ClienteController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private ClienteDAO dao;
    private UsuarioDAO daoUsuario;

    @Override
    public void init() {
        dao = new ClienteDAO();
        daoUsuario = new UsuarioDAO();
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
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        Usuario usuario = daoUsuario.getbyID(id);
        request.setAttribute("Cliente", cliente);
        request.setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String dataDeNascimento = request.getParameter("dataDeNascimento");

        
        Cliente cliente = new Cliente(cpf, telefone, nome, sexo, dataDeNascimento);
        dao.insert(cliente);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long id = Long.parseLong(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataDeNascimento = request.getParameter("dataDeNascimento");
        
        
        Cliente cliente = new Cliente(id, cpf, telefone, nome, sexo, dataDeNascimento);
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
