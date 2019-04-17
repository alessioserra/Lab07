package it.polito.tdp.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

//Classe creata per gestire in modo più facile i blackout, una volta caricati dal database
public class PowerOutages {
	
	//Dati blackout
	private int id;
	private int personeCoinvolte;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	
	//Dati da ricavare per la ricorsione
	private long oreBlackOut;
	private int anno;
	
	public PowerOutages(int id,int personeCoinvolte, LocalDateTime dataInizio, LocalDateTime dataFine) {
		this.id = id;
		this.personeCoinvolte = personeCoinvolte;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		
		//calcolo i dati per la ricorsione
		LocalDateTime tempo = LocalDateTime.from(dataInizio);
		this.oreBlackOut = tempo.until(dataFine, ChronoUnit.HOURS);
		
		this.anno=dataInizio.getYear();
		
	}
	public int getPersoneCoinvolte() {
		return personeCoinvolte;
	}
	public void setPersoneCoinvolte(int personeCoinvolte) {
		this.personeCoinvolte = personeCoinvolte;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}
	public long getOreBlackOut() {
		return oreBlackOut;
	}
	public void setOreBlackOut(int oreBlackOut) {
		this.oreBlackOut = oreBlackOut;
	}
	
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public void setOreBlackOut(long oreBlackOut) {
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
