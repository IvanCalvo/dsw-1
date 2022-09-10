package dsw.CarDealership.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.domain.Cliente;
import dsw.CarDealership.domain.Carro;

@SuppressWarnings("unchecked")
public interface PropostaDAO extends CrudRepository<Proposta, Long>{
	Proposta findById(long id);

	List<Proposta> findAll();
	
	Proposta save(Proposta proposta);

	void deleteById(Long id);
	
	@Query("SELECT proposta FROM Proposta proposta WHERE proposta.cliente = :cliente")
	public List<Proposta> findByIdCliente(@Param ("cliente") Cliente cliente);
	
	@Query("SELECT proposta FROM Proposta proposta  WHERE proposta.carro = :carro")
	public List<Proposta> findByIdCarro(@Param ("carro") Carro carro);

}
