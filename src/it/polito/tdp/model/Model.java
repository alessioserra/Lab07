package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dao.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutages> listaP;
	int bestPeopleAffected=0;
	List<PowerOutages> soluzione;
	
	private int oreMax;
	private int annoMax;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public int getBestPeopleAffected() {
		return bestPeopleAffected;
	}
	
	//Ottengo i valori per la choiceBox
	public List<String> getNercList() {
		
		List<String> string = new ArrayList<String>();
		List<Nerc> lista = podao.getNercList();
		
		for (Nerc n : lista) string.add(n.getValue());
		
		return string;
	}
	
	//N.B. Per gli anni, se diversi, considero sempre l'anno di inizio guasto
	public List<PowerOutages> risolviProblema(String nomeNerc, int maxOre, int maxAnni){
		
		//Assegno valori globali
		oreMax=maxOre;
		annoMax=maxAnni;
		
		//Ottengo Id nel nerc dato il nome
		int idNerc = podao.getIdNerc(nomeNerc);
		
		//Ottengo i valori relativi al NercId
		listaP=podao.getPowerOutages(idNerc);
		
		//Creo lista parziale
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();
		
		//Ricorsione
		risolvi(parziale,0);
		
		//ritorno risultato migliore trovato
		return soluzione;
	}
	
	public void risolvi(List<PowerOutages> parziale,int livello) {
		
		//CASO TERMINALE (Verifico ad ogni giro, quando il risultato e' "peggiore" mi fermo, in quando analisi WorstCase)
		if (personeAffette(parziale)>bestPeopleAffected) {
			bestPeopleAffected=personeAffette(parziale);
			soluzione=new ArrayList<>(parziale);
		}

		
		//CASO INTERMEDIO
		for (PowerOutages po : listaP) {
			if (verifica(parziale,po)==true) {
				//Verifico che la lista non contenga già l'elemento
				if (parziale.contains(po)==false) {
				//Scendo di livello
				parziale.add(po);
				//Ricorsione
				risolvi(parziale,livello+1);
				//backtracking
				parziale.remove(parziale.size()-1);
				}
			}
		}
	}
	
	public boolean verifica(List<PowerOutages> lista, PowerOutages prova) {
		
		//Inizializzo contatori
		long somma = prova.getOreBlackOut();
		int annoMin=2000000;
		int annoM=0;
		
		//Ciclo per trovare i valori che mi servono
		for (PowerOutages p : lista) {
			somma=somma+p.getOreBlackOut();
			if (p.getAnno()<=annoMin) annoMin=p.getAnno();
			if (p.getAnno()>annoMax) annoM=p.getAnno();
		}
	
		//Condizioni da rispettare
		if ( (somma<=oreMax) && (annoM-annoMin)<=annoMax) return true;
		else return false;
	}
	
	/**
	 * Metodo per ottenere il numero delle persone coinvolte, data una lista
	 * @param lista
	 * @return
	 */
	public int personeAffette(List<PowerOutages> lista) {
		int res=0;
		for (PowerOutages p : lista) res=res+p.getPersoneCoinvolte();
		return res;
	}

}