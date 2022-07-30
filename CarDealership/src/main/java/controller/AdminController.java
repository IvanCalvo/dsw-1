package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LojaDAO;
import dao.ClienteDAO;
import dao.UsuarioDAO;
import domain.Usuario;
import domain.Cliente;
import domain.Loja;
import util.Erro;

@WebServlet(urlPatterns = "/admin/*")

public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private UsuarioDAO dao;
    private LojaDAO daoLoja;
    private ClienteDAO daoCliente;

    @Override
    public void init() {
        dao = new UsuarioDAO();
        daoLoja = new LojaDAO();
        daoCliente = new ClienteDAO();
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
                    case "/cadastroCliente":
                        apresentaFormCadastroCliente(request, response);
                        break;
                    case "/cadastroLoja":
                        apresentaFormCadastroLoja(request, response);
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
    
    private void apresentaFormCadastroCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastroLoja(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/formularioLoja.jsp");
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
        
        Usuario Usuario = new Usuario(email, senha, papel);
        dao.insert(Usuario);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long Usuario_id = Long.parseLong(request.getParameter("id"));
    	String email = request.getParameter("email");
    	String senha = request.getParameter("senha");
    	String papel = request.getParameter("papel");
    	String nome = request.getParameter("nome");
    	
        Usuario Usuario = new Usuario(Usuario_id,  email, senha, papel);
        dao.update(Usuario);
        if(papel == "LOJA") {
        	String descricao = request.getParameter("descricao");
        	Integer cnpj = Integer.parseInt(request.getParameter("cnpj"));
        	
        	Loja loja = new Loja(Usuario_id, nome, descricao, cnpj);
        	daoLoja.update(loja);
        }
        else {
        	if(papel == "CLIENTE"){
        		Integer cpf = Integer.parseInt(request.getParameter("cpf"));
        		Integer telefone = Integer.parseInt(request.getParameter("telefone"));
        		String sexo = request.getParameter("sexo");
        		Integer dataNascimento = Integer.parseInt(request.getParameter("dataNascimento"));
        		
        		Cliente cliente = new Cliente(Usuario_id, cpf, telefone, nome, sexo, dataNascimento);
        		daoCliente.update(cliente);
        	}
        }
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