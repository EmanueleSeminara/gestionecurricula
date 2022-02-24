package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaDAOImpl extends AbstractMySQLDAO implements EsperienzaDAO {

	@Override
	public List<Esperienza> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from esperienza")) {

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setConoscenzeAquisite(rs.getString("conoscenzeacquisite"));
				esperienzaTemp.setDataFine(rs.getDate("datafine"));
				esperienzaTemp.setDataInizio(rs.getDate("datainizio"));
				esperienzaTemp.setDescrizione(rs.getString("descrizione"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from esperienza where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Esperienza();
					result.setConoscenzeAquisite(rs.getString("conoscenzeacquisite"));
					result.setDataFine(rs.getDate("datafine"));
					result.setDataInizio(rs.getDate("datainizio"));
					result.setDescrizione(rs.getString("descrizione"));
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Esperienza esperienzaInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE esperienza SET conoscenzeacquisite=?, datafine=?, datainizio=?, descrizione=? where id=?;")) {
			ps.setString(1, esperienzaInput.getConoscenzeAquisite());
			ps.setString(2, esperienzaInput.getDescrizione());
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataFine().getTime()));
			ps.setDate(4, new java.sql.Date(esperienzaInput.getDataInizio().getTime()));
			ps.setLong(6, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Esperienza esperienzaInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO esperienza (conoscenzeacquisite, descrizione, datafine, datainizio) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, esperienzaInput.getConoscenzeAquisite());
			ps.setString(2, esperienzaInput.getDescrizione());
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataFine().getTime()));
			ps.setDate(4, new java.sql.Date(esperienzaInput.getDataInizio().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Esperienza esperienzaInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM esperienza WHERE ID=?")) {
			ps.setLong(1, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza example) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		String query = "select * from esperienza where 1=1 ";
		if (example.getConoscenzeAquisite() != null && !example.getConoscenzeAquisite().isEmpty()) {
			query += " and conoscenzeacquisite like '" + example.getConoscenzeAquisite() + "%' ";
		}

		if (example.getDataFine() != null) {
			query += " and datafine like '" + new java.sql.Date(example.getDataFine().getTime()) + "%' ";
		}

		if (example.getDataInizio() != null) {
			query += " and datainizio like '" + new java.sql.Date(example.getDataInizio().getTime()) + "%' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setConoscenzeAquisite(rs.getString("conoscenzeacquisite"));
				esperienzaTemp.setDataFine(rs.getDate("datafine"));
				esperienzaTemp.setDataInizio(rs.getDate("datainizio"));
				esperienzaTemp.setDescrizione(rs.getString("descrizione"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
