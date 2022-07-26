package domain;

public class Loja {
	private Long id;
	private String nome;
	private String email;
	private String descricao;
	private Integer cnpj;
	
	public Loja(Long id) {
		this.id = id;
	}
	
	public Loja(String nome, String email, String descricao, Integer cnpj) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.cnpj = cnpj;
	}
	
	public Loja(Long id, String nome, String descricao, Integer cnpj) {
		this.nome = nome;
		this.descricao = descricao;
		this.cnpj = cnpj;
	}
	
	public Loja(Long id, String nome, String email, String descricao, Integer cnpj) {
		this(nome, email, descricao, cnpj);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
