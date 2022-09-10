package dsw.CarDealership.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.domain.Loja;
import dsw.CarDealership.service.spec.ICarroService;

@CrossOrigin
@RestController
public class CarroRestController {

	@Autowired
	private ICarroService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

    @SuppressWarnings("unchecked")
	private void parse(Loja loja, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("estado");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			loja.setId(((Integer) id).longValue());
		} else {
			loja.setId((Long) id);
		}
		 		
		loja.setNome((String) json.get("nome"));
        loja.setCnpj((String) json.get("cnpj"));
		loja.setDescricao((String) json.get("descricao"));
	}

	private void parse(Carro carro, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				carro.setId(((Integer) id).longValue());
			} else {
				carro.setId((Long) id);
			}
		}

		carro.setPlaca((String) json.get("placa"));
		carro.setModelo((String) json.get("modelo"));
		carro.setChassi((String) json.get("chassi"));
        carro.setAno((Integer) json.get("ano"));
        carro.setQuilometragem((Integer) json.get("quilometragem"));
        carro.setDescricao((String) json.get("descricao"));
        carro.setValor((BigDecimal) json.get("valor"));

		Loja loja = new Loja();
		parse(loja, json);
		carro.setLoja(loja);
	}

	@GetMapping(path = "/carros")
	public ResponseEntity<List<Carro>> lista() {
		List<Carro> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/carros/{id}")
	public ResponseEntity<Carro> lista(@PathVariable("id") long id) {
		Carro carro = service.buscarPorId(id);
		if (carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}




	@PostMapping(path = "/carros")
	@ResponseBody
	public ResponseEntity<Carro> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Carro carro = new Carro();
				parse(carro, json);
				service.salvar(carro);
				return ResponseEntity.ok(carro);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/carros/{id}")
	public ResponseEntity<Carro> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Carro carro = service.buscarPorId(id);
				if (carro == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(carro, json);
					service.salvar(carro);
					return ResponseEntity.ok(carro);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/carros/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Carro carro = service.buscarPorId(id);
		if (carro == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
