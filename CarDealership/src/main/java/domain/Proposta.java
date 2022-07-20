package domain;

public class Proposta {
	private Long id;
	private Integer valor;
	private String condPagamento;
	private Integer dataAtual;
	
	public Proposta(Long id) {
		this.id = id;
	}
	
	public Proposta ( Integer valor, String condPagamento,
			Integer dataAtual) {
		this.valor = valor;
		this.condPagamento = condPagamento;
		this.dataAtual = dataAtual;
	}
	
	public Proposta ( Long id, Integer valor, String condPagamento,
			Integer dataAtual) {
		this(valor, condPagamento, dataAtual);
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
	
	
	
}
