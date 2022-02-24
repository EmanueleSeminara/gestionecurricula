package it.gestionecurricula.model;

import java.util.Date;
import java.util.Objects;

public class Esperienza {
	private Long id;
	private String descrizione;
	private Date dataInizio;
	private Date dataFine;
	private String conoscenzeAquisite;
	private Curriculum curriculum;

	public Esperienza() {
		super();
	}

	public Esperienza(Long id, String descrizione, Date dataInizio, Date dataFine, String conoscenzeAquisite,
			Curriculum curriculum) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.conoscenzeAquisite = conoscenzeAquisite;
		this.curriculum = curriculum;
	}

	public Esperienza(String descrizione, Date dataInizio, Date dataFine, String conoscenzeAquisite,
			Curriculum curriculum) {
		super();
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.conoscenzeAquisite = conoscenzeAquisite;
		this.curriculum = curriculum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getConoscenzeAquisite() {
		return conoscenzeAquisite;
	}

	public void setConoscenzeAquisite(String conoscenzeAquisite) {
		this.conoscenzeAquisite = conoscenzeAquisite;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conoscenzeAquisite, curriculum, dataFine, dataInizio, descrizione, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esperienza other = (Esperienza) obj;
		return Objects.equals(conoscenzeAquisite, other.conoscenzeAquisite)
				&& Objects.equals(curriculum, other.curriculum) && Objects.equals(dataFine, other.dataFine)
				&& Objects.equals(dataInizio, other.dataInizio) && Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Esperienza [id=" + id + ", descrizione=" + descrizione + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + ", conoscenzeAquisite=" + conoscenzeAquisite + ", curriculum=" + curriculum + "]";
	}

}
