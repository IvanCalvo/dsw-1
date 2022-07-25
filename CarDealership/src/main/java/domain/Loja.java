package domain;

public class Loja {
	private Long id_loja;
	private String nome;
	private String email;
	private String senha;
	private String descricao;
	private Integer cnpj;
	
	public Loja(Long id) {
		this.id_loja = id;
	}
	
	public Loja(String nome, String email, String senha, String descricao, Integer cnpj) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
		this.cnpj = cnpj;
	}
	
	public Loja(Long id_loja, String nome, String descricao, Integer cnpj) {
		this.nome = nome;
		this.descricao = descricao;
		this.cnpj = cnpj;
	}
	
	public Loja(Long id_loja, String nome, String email, String senha, String descricao, Integer cnpj) {
		this(nome, email, senha, descricao, cnpj);
		this.id_loja = id_loja;
	}

	public Long getId_loja() {
		return id_loja;
	}

	public void setId_loja(Long id_loja) {
		this.id_loja = id_loja;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
