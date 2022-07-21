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
import domain.Carro;
import domain.Loja;

@WebServlet(urlPatterns = "/carros/*")

public class controller extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private CarroDAO dao;

    @Override
    public void init() {
        dao = new CarroDAO();
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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carro> listaCarros = dao.getAll();
        request.setAttribute("listaCarros", listaCarros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/carro/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getLojas() {
        Map <Long,String> lojas = new HashMap<>();
        for (Loja loja: new LojaDAO().getAll()) {
            lojas.put(loja.getId_loja(), loja.getNome());
        }
        return lojas;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/carro/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carro carro = dao.get(id);
        request.setAttribute("carro", carro);
        request.setAttribute("lojas", getLojas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/carro/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String chassi = request.getParameter("chassi");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Integer quilometragem = Integer.parseInt(request.getParameter("quilometragem"));
        String descricao = request.getParameter("descricao");
        Float valor = Float.parseFloat(request.getParameter("valor"));
        String fotos = request.getParameter("fotos");
        
        Long id_loja = Long.parseLong(request.getParameter("loja"));
        Loja loja = new LojaDAO().get(id_loja);
        
        Carro carro = new Carro(loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
        dao.insert(carro);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	int id_carro = Integer.parseInt(request.getParameter("id"));
    	String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String chassi = request.getParameter("chassi");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Integer quilometragem = Integer.parseInt(request.getParameter("quilometragem"));
        String descricao = request.getParameter("descricao");
        Float valor = Float.parseFloat(request.getParameter("valor"));
        String fotos = request.getParameter("fotos");
        
        Long id_loja = Long.parseLong(request.getParameter("loja"));
        Loja loja = new LojaDAO().get(id_loja);
        
        Carro carro = new Carro(id_carro,loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
        dao.update(carro);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id_carro = Integer.parseInt(request.getParameter("id"));

        Carro carro = new Carro(id_carro);
        dao.delete(carro);
        response.sendRedirect("lista");
    }
}