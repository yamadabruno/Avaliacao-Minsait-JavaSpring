package br.com.brunoyamada.AppMinsaitContato.model;

import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contatos")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Tipo de contato obrigatório")
	@Max(value = 1, message = "Tipo contato não pode ser maior que 1")
	@Min(value = 0, message = "Tipo contato não pode ser menor que 0")
	private Integer tipoContato;
	
	@Column(nullable = false)
	@NotNull(message = "Contato obrigatório")
	@NotBlank(message = "Contato não pode ser vazio")
	@Length(max = 30, message = "Contato não pode conter mais de 30 caracteres")
	private String contato;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Pessoa pessoaId;
	
	public Contato() {
	}

	public Contato(Long id, Integer tipoContato, String contato, Pessoa pessoaId) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.pessoaId = pessoaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Pessoa getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Pessoa pessoaId) {
		this.pessoaId = pessoaId;
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
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	
}
