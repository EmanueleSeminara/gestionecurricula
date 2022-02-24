package it.gestionecurricula.service.curriculum;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.curriculum.CurriculumDAO;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.dao.esperienza.EsperienzaDAOImpl;
import it.gestionecurricula.model.Curriculum;
import it.gestionecurricula.model.Esperienza;

public class CurriculumServiceImpl implements CurriculumService {

	private CurriculumDAO curriculumDao;
	private EsperienzaDAO esperienzaDao = new EsperienzaDAOImpl();

	@Override
	public void setCurriculumDao(CurriculumDAO curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	@Override
	public List<Curriculum> listAll() throws Exception {
		List<Curriculum> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curriculum findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curriculum result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Curriculum input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Curriculum input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Curriculum input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);
			esperienzaDao.setConnection(connection);

			List<Esperienza> elencoEsperienze = esperienzaDao.searchAllByCurriculumId(input.getId());
			if (elencoEsperienze.size() > 0) {
				throw new RuntimeException("Impossibile eliminare il curriculum, sono presenti delle esperienze");
			}

			// eseguo quello che realmente devo fare
			result = curriculumDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum input) throws Exception {
		List<Curriculum> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
