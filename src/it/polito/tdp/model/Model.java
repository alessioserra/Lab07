package it.polito.tdp.model;

import java.util.List;

import it.polito.tdp.dao.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

}