package dsw.CarDealership.controller;

import java.io.IOException;
import java.util.List;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import com.fasterxml.jackson.databind.ObjectMapper;
import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.service.spec.IPropostaService;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.service.spec.ICarroService;
import dsw.CarDealership.domain.Cliente;
import dsw.CarDealership.service.spec.IClienteService;

@CrossOrigin
@RestController
public class PropostaRestController {
	
	@Autowired
	private IPropostaService service;
	
	@Autowired
	private ICarroService carroService;
	
	@Autowired
	private IClienteService clienteService;
	
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}

 }

	@GetMapping(path = "/propostas/carros/{id}")
	public ResponseEntity<List<Proposta>> listaPorCarroId(@PathVariable("id") long id) {
		Carro carro = carroService.buscarPorId(id);
		List<Proposta> lista = service.buscarPorCarroId(carro);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

	@GetMapping(path = "/propostas/clientes/{id}")
	public ResponseEntity<List<Proposta>> listaPorClienteId(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		List<Proposta> lista = service.buscarPorClienteId(cliente);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

}
