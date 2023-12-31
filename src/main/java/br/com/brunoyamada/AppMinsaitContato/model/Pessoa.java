package br.com.brunoyamada.AppMinsaitContato.model;

import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pessoas")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Nome obrigatório")
	@NotBlank(message = "Nome não pode ser vazio")
	@Length(max = 100, message = "Nome não pode conter mais de 100 caracteres")
	private String nome;
	
	@Length(max = 120, message = "Endereço não pode conter mais de 120 caracteres")
	private String endereco;
	
	@Length(max = 20, message = "Cep não pode conter mais de 20 caracteres")
	private String cep;
	
	@Length(max = 100, message = "Cidade não pode conter mais de 100 caracteres")
	private String cidade;
	
	@Length(max = 2, message = "Uf não pode conter mais de 2 caracteres")
	private String uf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoaId", cascade = CascadeType.ALL)
	private List<Contato> contatos;
	
	public Pessoa() {
	}

	public Pessoa(Long id, String nome, String endereco, String cep, String cidade, String uf) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	public String infMalaDireta() {
		return endereco + 
			   " - CEP: " + cep +
			   " - " + cidade +
			   "/" + uf;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

}
