package it.gestionecurricula.service.esperienza;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaServiceImpl implements EsperienzaService {
	private EsperienzaDAO esperienzaDao;

	@Override
	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDao = esperienzaDao;
	}

	@Override
	public List<Esperienza> listAll() throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Esperienza input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Esperienza input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);
			System.out.println(input.getCurriculum().getId());
			List<Esperienza> esperienzePresenti = esperienzaDao.searchAllByCurriculumId(input.getCurriculum().getId());
			System.out.println(esperienzePresenti.size());

			for (Esperienza esperienzaItem : esperienzePresenti) {
				if (esperienzaItem.getDataFine() == null) {
					esperienzaItem.setDataFine(input.getDataInizio());
					esperienzaDao.update(esperienzaItem);
				}
			}

			for (Esperienza esperienzaItem : esperienzePresenti) {
				System.out.println(esperienzaItem);
				if (esperienzaItem.getDataFine().after(input.getDataInizio())
						|| esperienzaItem.getDataInizio().before(input.getDataFine())) {
					throw new Exception("Il periodo dell'esperienza inserita va in conflitto con le altre.");
				}
			}
			result = esperienzaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Esperienza input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza input) throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
