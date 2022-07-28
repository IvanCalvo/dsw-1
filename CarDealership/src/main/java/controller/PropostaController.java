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
import dao.PropostaDAO;
import dao.ClienteDAO;
import dao.LojaDAO;
import domain.Carro;
import domain.Loja;
import domain.Cliente;
import domain.Proposta;

@WebServlet(urlPatterns = "/propostas/*")

public class PropostaController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private PropostaDAO dao;

    @Override
    public void init() {
        dao = new PropostaDAO();
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
        List<Proposta> listaProposta = dao.getAll();
        request.setAttribute("listaProposta", listaProposta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/lista.jsp");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Proposta proposta = dao.get(id);
        request.setAttribute("proposta", proposta);
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long proposta_id = Long.parseLong(request.getParameter("id"));
    	int valor = Integer.parseInt(request.getParameter("valor"));
        String modelo = request.getParameter("condPagamento");
    	int dataAtual = Integer.parseInt(request.getParameter("dataAtual"));
    	String status = request.getParameter("status");
        
        Long id_cliente = Long.parseLong(request.getParameter("cliente"));
        Cliente cliente = new ClienteDAO().get(id_cliente);
        
        int id_carro = Integer.parseInt(request.getParameter("cliente"));
        Carro carro = new CarroDAO().get(id_carro);
        Proposta proposta = new Proposta(proposta_id, valor, modelo, dataAtual, status, cliente, carro);
        dao.insert(proposta);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long proposta_id = Long.parseLong(request.getParameter("id"));
    	int valor = Integer.parseInt(request.getParameter("valor"));
        String modelo = request.getParameter("condPagamento");
    	int dataAtual = Integer.parseInt(request.getParameter("dataAtual"));
    	String status = request.getParameter("status");
        
        Long id_cliente = Long.parseLong(request.getParameter("cliente"));
        Cliente cliente = new ClienteDAO().get(id_cliente);
        
        int id_carro = Integer.parseInt(request.getParameter("cliente"));
        Carro carro = new CarroDAO().get(id_carro);
        Proposta proposta = new Proposta(proposta_id, valor, modelo, dataAtual, status, cliente, carro);
        dao.update(proposta);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long proposta_id = Long.parseLong(request.getParameter("id"));

        Proposta proposta = new Proposta(proposta_id);
        dao.delete(proposta);
        response.sendRedirect("listaProposta");
    }
}