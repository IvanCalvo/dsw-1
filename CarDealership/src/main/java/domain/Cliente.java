package domain;

public class Cliente {
	private Long id;
	private String email;
	private String senha;
	private Integer cpf;
	private String nome;
	private Integer telefone;
	private String sexo;
	private Integer dataDeNascimento;
	
	public Cliente(Long id) {
		this.id = id;
	}
	
	public Cliente(String email, String senha,
			Integer cpf, String nome, Integer telefone,
			String sexo, Integer dataDeNascimento) {
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public Cliente(Long id, String email, String senha,
			Integer cpf, String nome, Integer telefone,
			String sexo, Integer dataDeNascimento) {
		this(email, senha, cpf, nome, telefone, sexo,
				dataDeNascimento);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Integer dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
}
