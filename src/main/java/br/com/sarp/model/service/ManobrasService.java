package br.com.sarp.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.ManobrasDAO;
import br.com.sarp.model.entidades.Manobras;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class ManobrasService {

	@Inject
	private ManobrasDAO manobrasDao;

	@SuppressWarnings("unused")
	public void salvar(Manobras manobras) throws ServiceException {

		/*
		 * validacao das regras de negocio a manobras,setor,funcao,situacao
		 * devem existir no cadastro nenhum campo pode ser vazio * if (manobras
		 * == null || manobras.getNome() == "") { throw new ServiceException(
		 * "Campo nome manobras nao pode ser vazio!"); }
		 * 
		 * 
		 * verificar se existe informacoes pendentes pratico embarcacao inicio
		 * local manobra
		 */
		// validacao da regra exemplo

		// validacao da data se superior a atual ou inferior
		Calendar calendar = Calendar.getInstance();

		Integer diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
		Integer mesAtual = calendar.get(Calendar.MONTH);
		Integer anoAtual = calendar.get(Calendar.YEAR);

		Integer horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
		Integer minAtual = calendar.get(Calendar.MINUTE);

		String hora, hr, min, ultimaHora, hu, mu;
		Integer hh, mm, hhu, mmu, hhm, mmm, hhi, mmi;
		hh = 0;
		mm = 0;
		hhu = 0;
		mmu = 0;
		hhm = 0;
		mmm = 0;
		hhi = 0;
		mmi = 0;
		ultimaHora = null;
		hora = null;

		List<Manobras> manobrasDia = new ArrayList<Manobras>();

		manobrasDia = manobrasDao
				.buscarManobraDia(diaAtual, mesAtual, anoAtual);

		if ((manobras.getDia().equals(diaAtual) == true)
				&& (manobras.getMes().equals(mesAtual) == true)
				&& (manobras.getAno().equals(anoAtual) == true)) {

			if (manobrasDia.isEmpty() == false) {

				for (Manobras m : manobrasDia) {
					ultimaHora = m.getFim();
				}

				hu = ultimaHora.substring(0, 2);
				mu = ultimaHora.substring(3, 5);
				hhu = Integer.parseInt(hu);
				mmu = Integer.parseInt(mu);

				hora = manobras.getInicio();
				hr = hora.substring(0, 2);
				min = hora.substring(3, 5);
				hh = Integer.parseInt(hr);
				mm = Integer.parseInt(min);
				hhi = hh;
				mmi = mm;

				if ((hh < hhu) || (hh == hhu && mm < mmu)) {
					throw new ServiceException("Horario Manobra Invalido!!!");
				}
			}

			if (manobras.getInicio() != null) {
				hora = manobras.getInicio();
				hr = hora.substring(0, 2);
				min = hora.substring(3, 5);
				hh = Integer.parseInt(hr);
				mm = Integer.parseInt(min);

				if (hh > 24 || mm > 60) {
					throw new ServiceException("Verificar Inicio Manobra!!!");
				} else if (hh > horaAtual) {
					throw new ServiceException(
							"Inicio nao pode ser superior que Hora Atual !!!!");
				}

			}

			if (manobras.getFim() != null) {
				hora = manobras.getFim();
				hr = hora.substring(0, 2);
				min = hora.substring(3, 5);
				hh = Integer.parseInt(hr);
				mm = Integer.parseInt(min);

				if (hh > 24 || mm > 60) {
					throw new ServiceException("Verificar final Manobra!!!");
				} else if (hh > horaAtual) {
					throw new ServiceException(
							"Final nao pode ser superior que Hora Atual !!!!");
				}

			}

			// determinar tempo de manobra
			// converter tudo para minutos e efetuar subtracao
			hhm = ((hh * 60) + mm) - ((hhi * 60) + mmi);
			manobras.setTempoManobra(hhm);
manobras.setVlrManobra(hhm*17);
			
		} else if ((manobras.getDia().equals(diaAtual) == false)
				|| (manobras.getMes().equals(mesAtual) == false)
				|| (manobras.getAno().equals(anoAtual) == false)) {

			throw new ServiceException(
					"Data invalida , Mdb Encerrado para esta data!!!!");
		}

		if (manobras.getInicio() == null || manobras.getInicio() == "") {
			throw new ServiceException("Campo Inicio deve ser Informado!!!!");
		} else if (manobras.getPratico() == null
				|| manobras.getPratico().getNome() == "") {
			throw new ServiceException("Pratico deve se Informado!!!!");
		} else if (manobras.getEmbarcacao() == null
				|| manobras.getEmbarcacao().getNome() == "") {
			throw new ServiceException("Embarcacao deve ser Informada!!!!");
		} else if (manobras.getLocais() == null
				|| manobras.getLocais().getNome() == "") {
			throw new ServiceException("Local deve ser Informado!!!!");
		} else if (manobras.getTipoManobras() == null
				|| manobras.getTipoManobras().getNome() == "") {
			throw new ServiceException("Manobra deve ser Informado!!!!");
		} else {
			try {
				manobrasDao.salvar(manobras);
			} catch (DAOException causa) {
				throw new ServiceException("Nao foi possivel salvar !", causa);
			}
		}
	}

	public Manobras buscarId(int id) {
		// validacao das regras de negocio

		return manobrasDao.buscarPorId(id);

	}

	public List<Manobras> buscaTodos() {

		List<Manobras> lista = manobrasDao.buscarTodos();
		return lista;

	}

	public void excluir(Manobras manobras) {
		// validacao a exclusao
		manobrasDao.excluir(manobras);

	}

	public Integer buscarNumeroManobra(Integer mes, Integer ano) {
		// TODO Auto-generated method stub
		return manobrasDao.buscarNumeroManobra(mes, ano);
	}

	public List<Manobras> buscarManobraDia(Integer dia, Integer mes, Integer ano) {
		// TODO Auto-generated method stub

		return manobrasDao.buscarManobraDia(dia, mes, ano);
	}

	public List<Manobras> buscarManobraPeriodo(Integer iniciodia,
			Integer iniciomes, Integer inicioano, Integer fimdia,
			Integer fimmes, Integer fimano) {
		// TODO Auto-generated method stub
		return manobrasDao.buscarManobraPeriodo(iniciodia, iniciomes,
				inicioano, fimdia, fimmes, fimano);
	}

	public List<Manobras> buscarManobraPeriodoRb(Integer iniciodia,
			Integer iniciomes, Integer inicioano, Integer fimdia,
			Integer fimmes, Integer fimano, Rebocador rbRelatorio) {
		
		return manobrasDao.buscarManobraPeriodoRb(iniciodia,
				iniciomes,inicioano, fimdia,
				fimmes,fimano, rbRelatorio);
	}

	public List<Manobras> buscarManobraDiaRb(Integer dia, Integer mes,
			Integer ano, Rebocador rebocador) {
		// TODO Auto-generated method stub
		
		return manobrasDao.buscarManobraDiaRb(dia, mes, ano,rebocador);
	}

}
