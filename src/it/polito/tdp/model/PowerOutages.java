package it.polito.tdp.model;

import java.util.Date;

//Classe creata per gestire in modo più facile i blackout, una volta caricati dal database
public class PowerOutages {
	
	int id;
	int personeCoinvolte;
	Date dataInizio;
	Date dataFine;
	int oreBlackOut;
	
	public PowerOutages(int id,int personeCoinvolte, Date dataInizio, Date dataFine) {
		this.id = id;
		this.personeCoinvolte = personeCoinvolte;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}
	public int getPersoneCoinvolte() {
		return personeCoinvolte;
	}
	public void setPersoneCoinvolte(int personeCoinvolte) {
		this.personeCoinvolte = personeCoinvolte;
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
	public int getOreBlackOut() {
		return oreBlackOut;
	}
	public void setOreBlackOut(int oreBlackOut) {
		this.oreBlackOut = oreBlackOut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
