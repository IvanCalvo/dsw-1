package dsw.CarDealership.service.spec;

import java.util.List;

import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.domain.Cliente;

public interface IPropostaService {
	Proposta buscarPorId(Long id);
	
	List<Proposta> buscarTodos();
	
	List<Proposta> buscarPorClienteId(Cliente cliente);
	
	List<Proposta> buscarPorCarroId(Carro carro);
	
	void salvar(Proposta proposta);
	
	void excluir(Long id);
}
