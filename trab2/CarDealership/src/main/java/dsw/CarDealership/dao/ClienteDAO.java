package dsw.CarDealership.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dsw.CarDealership.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ClienteDAO extends CrudRepository<Cliente, Long>{
	Cliente findById(long id);

	List<Cliente> findAll();
	
	Cliente save(Cliente cliente);

	void deleteById(Long id);
}
