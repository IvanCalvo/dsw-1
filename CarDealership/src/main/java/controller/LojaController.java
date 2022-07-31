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
import domain.Carro;
import dao.LojaDAO;
import dao.UsuarioDAO;
import domain.Loja;
import domain.Usuario;
import util.Erro;

@WebServlet(urlPatterns = "/loja/*")

public class LojaController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private LojaDAO dao;
    private CarroDAO daoCarro;
    private UsuarioDAO daoUsuario;

    @Override
    public void init() {
        dao = new LojaDAO();
        daoUsuario = new UsuarioDAO();
        daoCarro = new CarroDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
    	
    	if (usuario == null) {
    		//response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("LOJA")) {
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
	                	RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/loja/index.jsp");
	                    dispatcher.forward(request, response);
	                    break;
	            }
	        } catch (RuntimeException | IOException | ServletException e) {
	            throw new ServletException(e);
	        }
		} else {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [LOJA] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			//rd.forward(request, response);
		}
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        List<Carro> listaCarros = daoCarro.getAll(usuario.getId());
        request.setAttribute("listaCarros", listaCarros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/loja/listaCarroLoja.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getLojas() {
        Map <Long,String> lojas = new HashMap<>();
        for (Loja loja: new LojaDAO().getAll()) {
            lojas.put(loja.getId_usuario(), loja.getNome());
        }
        return lojas;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lojas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = (long) Integer.parseInt(request.getParameter("id"));
        Loja loja = dao.get(id);
        Usuario usuario = daoUsuario.getbyID(id);
        request.setAttribute("Loja", loja);
        request.setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lojas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cnpj = request.getParameter("cnpj");
        
        Loja loja = new Loja(id, nome, descricao, cnpj);
        dao.insert(loja);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	//Falta a senha, talvez falte um atributo do tipo Usuário na classe Loja
    	Long id = Long.parseLong(request.getParameter("id"));
    	String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cnpj = request.getParameter("cnpj");
       
        //Loja loja = new LojaDAO().get(id);
        Loja loja = new Loja(id, nome, descricao, cnpj);
        dao.update(loja);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id_usuario = Long.parseLong(request.getParameter("id"));

        Loja loja = new Loja(id_usuario);
        dao.delete(loja);
        response.sendRedirect("lista");
    }
}