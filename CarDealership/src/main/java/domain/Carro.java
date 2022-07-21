package domain;

public class Carro {
	private Integer id_carro;
	private Integer cnpj_loja;
	private Loja loja; 
	private String placa;
	private String modelo; 
	private String chassi; 
	private Integer ano;
	private Integer quilometragem;  
	private String descricao;
	private Float valor;  
	private String fotos;
	
	public Carro(Integer id) {
		this.id_carro = id;  
	}
	
	public Carro(Loja loja, Integer cnpj_loja, String placa, String modelo, String chassi, Integer ano,
				Integer quilometragem, String descricao, Float valor, String fotos) {
		this.cnpj_loja = cnpj_loja;
		this.loja = loja;
		this.placa = placa;
		this.modelo = modelo;
		this.chassi = chassi;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.descricao = descricao;
		this.valor = valor;
		this.fotos = fotos;
	}
	
	public Carro(Integer id, Integer cnpj_loja, Loja loja, String placa, String modelo, String chassi, Integer ano,
			Integer quilometragem, String descricao, Float valor, String fotos) {
		this(loja, cnpj_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
		this.id_carro = id;
	}
	
	public Integer getId_carro() {
		return id_carro;
	}

	public void setId_carro(Integer id_carro) {
		this.id_carro = id_carro;
	}
	
	public Integer getCnpj_loja() {
		return cnpj_loja;
	}
	
	public void setCnpj_loja(Integer cnpj_loja) {
		this.cnpj_loja = cnpj_loja;
	}

	public Loja getloja() {
		return loja;
	}

	public void setloja(Loja loja) {
		this.loja = loja;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}
}