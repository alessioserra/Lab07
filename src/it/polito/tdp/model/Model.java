package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dao.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutages> listaP;
	int bestPeopleAffected=0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	//Ottengo i valori per la choiceBox
	public List<String> getNercList() {
		
		List<String> string = new ArrayList<String>();
		List<Nerc> lista = podao.getNercList();
		
		for (Nerc n : lista) string.add(n.getValue());
		
		return string;
	}
	
	//N.B. Per gli anni, se diversi, considero sempre l'anno di fine guasto
	public List<PowerOutages> risolviProblema(String nomeNerc, int maxOre, int maxAnni){
		
		//Ottengo Id nel nerc dato il nome
		int idNerc = podao.getIdNerc(nomeNerc);
		
		//Ottengo i valori relativi al NercId
		listaP=podao.getPowerOutages(idNerc);
		
		//Creo lista parziale
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();
		
		//Ricorsione
		risolvi(parziale,0);
		
		//ritorno risultato migliore trovato
		return parziale;
	}
	
	public void risolvi(List<PowerOutages> parziale,int livello) {
		
	}

}