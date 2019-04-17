package it.polito.tdp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.polito.tdp.model.Nerc;
import it.polito.tdp.model.PowerOutages;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	/** 
	 * Metodo per trovare ID dato il nome del NERC
	 * @param value
	 * @return
	 */
	public int getIdNerc(String value) {
		String sql = "SELECT id FROM nerc WHERE value=?";
        int id=0;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, value);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				id = res.getInt("id");
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}
	
	public List<PowerOutages> getPowerOutages(int id) {

		String sql = "SELECT id,customers_affected,date_event_began,date_event_finished FROM poweroutages WHERE nerc_id=?";
		List<PowerOutages> POList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			//Setto valore del nerc_id
			st.setInt(1, id);
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages p = new PowerOutages(res.getInt("id"),res.getInt("customers_affected"),res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime());
				POList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return POList;
	    }
}