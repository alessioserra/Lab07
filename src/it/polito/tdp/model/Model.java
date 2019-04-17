package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dao.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<String> getNercList() {
		
		List<String> string = new ArrayList<String>();
		List<Nerc> lista = podao.getNercList();
		
		for (Nerc n : lista) string.add(n.getValue());
		
		return string;
	}

}