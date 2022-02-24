package it.gestionecurricula.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curriculum {
	private Long id;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String telefono;
	private String email;
	private List<Esperienza> esperienza = new ArrayList<>();

	public Curriculum() {
		super();
	}

	public Curriculum(Long id, String nome, String cognome, Date dataDiNascita, String telefono, String email,
			List<Esperienza> esperienza) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.telefono = telefono;
		this.email = email;
		this.esperienza = esperienza;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Esperienza> getEsperienza() {
		return esperienza;
	}

	public void setEsperienza(List<Esperienza> esperienza) {
		this.esperienza = esperienza;
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
		Curriculum other = (Curriculum) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", telefono=" + telefono + ", email=" + email + ", esperienza=" + esperienza + "]";
	}

}
