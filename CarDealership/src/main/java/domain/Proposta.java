package domain;

public class Proposta {
	private Long id;
	private Integer valor;
	private String condPagamento;
	private Integer dataAtual;
	private String status;
	private Cliente cliente;
	
	
	public Proposta(Long id) {
		this.id = id;
	}
	
	public Proposta ( Integer valor, String condPagamento,
			Integer dataAtual, String status, Cliente cliente) {
		this.valor = valor;
		this.condPagamento = condPagamento;
		this.dataAtual = dataAtual;
		this.status = status;
		this.cliente = cliente;
	}
	
	public Proposta ( Long id, Integer valor, String condPagamento,
			Integer dataAtual, String status, Cliente cliente) {
		this(valor, condPagamento, dataAtual, status, cliente);
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getCondPagamento() {
		return condPagamento;
	}
	public void setCondPagamento(String condPagamento) {
		this.condPagamento = condPagamento;
	}
	public Integer getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(Integer dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
