package dsw.CarDealership.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dsw.CarDealership.dao.PropostaDAO;
import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.domain.Cliente;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.service.spec.IPropostaService;

@Service
@Transactional(readOnly = false)
public class PropostaService implements IPropostaService {
	@Autowired
	PropostaDAO dao;
	
	public void salvar(Proposta proposta) {
		dao.save(proposta);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Proposta buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Proposta> buscarTodos() {
		return dao.findAll();
	}
	

	public List<Proposta> buscarPorClienteId(Long id) {
		return dao.findByIdCliente(id.longValue());
	}
	

	public List<Proposta> buscarPorCarroId(Long id) {
		return dao.findByIdCarro(id.longValue());
	}
}
