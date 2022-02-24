package it.gestionecurricula.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.gestionecurricula.model.Curriculum;
import it.gestionecurricula.model.Esperienza;
import it.gestionecurricula.service.MyServiceFactory;
import it.gestionecurricula.service.curriculum.CurriculumService;
import it.gestionecurricula.service.esperienza.EsperienzaService;

public class TestCurriculum {

	public static void main(String[] args) {
		EsperienzaService esperienzaService = MyServiceFactory.getEsperienzaServiceImpl();
		CurriculumService curriculumService = MyServiceFactory.getCurriculumServiceImpl();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println("In tabella ci sono " + esperienzaService.listAll().size() + " elementi.");

			testInserimentoNuovaEsperienza(esperienzaService, curriculumService);
			System.out.println("In tabella ci sono " + esperienzaService.listAll().size() + " elementi.");

			// E TUTTI I TEST VANNO FATTI COSI'

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testInserimentoNuovaEsperienza(EsperienzaService esperienzaService,
			CurriculumService curriculumService) throws Exception {
		System.out.println(".......testInserimentoNuovaEsperienza inizio.............");

		List<Curriculum> elencoCurricula = curriculumService.listAll();
		if (elencoCurricula.isEmpty() || elencoCurricula.get(0) == null)
			throw new Exception("Nessun curriculum presente nel db");
		Curriculum curriculumInserito = elencoCurricula.get(elencoCurricula.size() - 1);
		Esperienza newEsperienzaInstance = new Esperienza("mauro",
				new SimpleDateFormat("dd-MM-yyyy").parse("02-01-2022"),
				new SimpleDateFormat("dd-MM-yyyy").parse("10-01-2022"), "bobobo", curriculumInserito);
		if (esperienzaService.inserisciNuovo(newEsperienzaInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoUser FAILED ");

		System.out.println(".......testInserimentoNuovaEsperienza PASSED.............");
	}

}
