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

import dao.LojaDAO;
import domain.Loja;

@WebServlet(urlPatterns = "/loja/*")

public class LojaController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private LojaDAO dao;

    @Override
    public void init() {
        dao = new LojaDAO();
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
                case "/lista":
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Loja> listaLojas = dao.getAll();
        request.setAttribute("listaLojas", listaLojas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lojas/lista.jsp");
        dispatcher.forward(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lojas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = (long) Integer.parseInt(request.getParameter("id"));
        Loja loja = dao.get(id);
        request.setAttribute("loja", loja);
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lojas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Integer cnpj = Integer.parseInt(request.getParameter("cnpj"));
        
        Long id_loja = Long.parseLong(request.getParameter("loja"));
        Loja loja = new LojaDAO().get(id_loja);
        
        loja = new Loja(email, nome, descricao, cnpj);
        dao.insert(loja);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	//Falta a senha, talvez falte um atributo do tipo Usuário na classe Loja
    	Long id = Long.parseLong(request.getParameter("id"));
    	String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String descricao = request.getParameter("descricao");
        Integer cnpj = Integer.parseInt(request.getParameter("cnpj"));
       
        //Loja loja = new LojaDAO().get(id);
        Loja loja = new Loja(id, nome, email, descricao, cnpj);
        dao.update(loja);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id_loja = Long.parseLong(request.getParameter("id"));

        Loja loja = new Loja(id_loja);
        dao.delete(loja);
        response.sendRedirect("lista");
    }
}