package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.UsuarioDAO;
import domain.Usuario;
import util.Erro;

@WebServlet(urlPatterns = "/admin/*")

public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private UsuarioDAO dao;

    @Override
    public void init() {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
    	
    	if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("ADMIN")) {
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
                    case "/lista":
                        lista(request, response);
                        break;
                    default:
                    	RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    	
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}    	
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaUsuario = dao.getAll();
        request.setAttribute("listaUsuario", listaUsuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario Usuario = dao.getbyID(id);
        request.setAttribute("Usuario", Usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
    	String email = request.getParameter("email");
    	String senha = request.getParameter("senha");
    	String papel = request.getParameter("papel");
        
        Usuario Usuario = new Usuario(nome, email, senha, papel);
        dao.insert(Usuario);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long Usuario_id = Long.parseLong(request.getParameter("id"));
    	String nome = request.getParameter("nome");
    	String email = request.getParameter("email");
    	String senha = request.getParameter("senha");
    	String papel = request.getParameter("papel");
    	
        Usuario Usuario = new Usuario(Usuario_id, nome, email, senha, papel);
        dao.update(Usuario);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long Usuario_id = Long.parseLong(request.getParameter("id"));

        Usuario Usuario = new Usuario(Usuario_id);
        dao.delete(Usuario);
        response.sendRedirect("listaUsuario");
    }
}