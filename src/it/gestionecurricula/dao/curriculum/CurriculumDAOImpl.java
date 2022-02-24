package it.gestionecurricula.dao.curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Curriculum;

public class CurriculumDAOImpl extends AbstractMySQLDAO implements CurriculumDAO {

	@Override
	public List<Curriculum> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Curriculum> result = new ArrayList<Curriculum>();
		Curriculum curriculumTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from curriculum")) {

			while (rs.next()) {
				curriculumTemp = new Curriculum();
				curriculumTemp.setNome(rs.getString("NOME"));
				curriculumTemp.setCognome(rs.getString("COGNOME"));
				curriculumTemp.setDataDiNascita(rs.getDate("datadinascita"));
				curriculumTemp.setEmail(rs.getString("email"));
				curriculumTemp.setTelefono(rs.getString("telefono"));
				curriculumTemp.setId(rs.getLong("ID"));
				result.add(curriculumTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curriculum get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curriculum result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from curriculum where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Curriculum();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setDataDiNascita(rs.getDate("datadinascita"));
					result.setEmail(rs.getString("email"));
					result.setTelefono(rs.getString("telefono"));
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
	public int update(Curriculum curriculumInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (curriculumInput == null || curriculumInput.getId() == null || curriculumInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE curriculum SET nome=?, cognome=?, datadinascita=?, email=?, telefono=? where id=?;")) {
			ps.setString(1, curriculumInput.getNome());
			ps.setString(2, curriculumInput.getCognome());
			ps.setDate(3, new java.sql.Date(curriculumInput.getDataDiNascita().getTime()));
			ps.setString(4, curriculumInput.getEmail());
			ps.setString(5, curriculumInput.getTelefono());
			ps.setLong(6, curriculumInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Curriculum curriculumInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (curriculumInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO curriculum (nome, cognome, email, datadinascita, telefono) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, curriculumInput.getNome());
			ps.setString(2, curriculumInput.getCognome());
			ps.setString(3, curriculumInput.getEmail());
			ps.setDate(4, new java.sql.Date(curriculumInput.getDataDiNascita().getTime()));
			ps.setString(5, curriculumInput.getTelefono());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Curriculum curriculumInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (curriculumInput == null || curriculumInput.getId() == null || curriculumInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM curriculum WHERE ID=?")) {
			ps.setLong(1, curriculumInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum example) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Curriculum> result = new ArrayList<Curriculum>();
		Curriculum curriculumTemp = null;

		String query = "select * from curriculum where 1=1 ";
		if (example.getCognome() != null && !example.getCognome().isEmpty()) {
			query += " and cognome like '" + example.getCognome() + "%' ";
		}
		if (example.getNome() != null && !example.getNome().isEmpty()) {
			query += " and nome like '" + example.getNome() + "%' ";
		}

		if (example.getDataDiNascita() != null) {
			query += " and datadinascita like '" + new java.sql.Date(example.getDataDiNascita().getTime()) + "%' ";
		}

		if (example.getEmail() != null && !example.getEmail().isBlank()) {
			query += " and email like '" + example.getEmail() + "%' ";
		}

		if (example.getTelefono() != null && !example.getTelefono().isBlank()) {
			query += " and telefono='" + example.getTelefono() + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				curriculumTemp = new Curriculum();
				curriculumTemp.setNome(rs.getString("NOME"));
				curriculumTemp.setCognome(rs.getString("COGNOME"));
				curriculumTemp.setDataDiNascita(rs.getDate("datadinascita"));
				curriculumTemp.setEmail(rs.getString("email"));
				curriculumTemp.setTelefono(rs.getString("telefono"));
				curriculumTemp.setId(rs.getLong("ID"));
				result.add(curriculumTemp);
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
