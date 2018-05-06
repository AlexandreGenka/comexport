package br.com.comexport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.comexport.beans.ContabilBean;
import br.com.comexport.beans.StatsBean;
import br.com.comexport.exceptions.ContabilException;

@Service
public class ContabilService {

	private static List<ContabilBean> db = new ArrayList<ContabilBean>();
	private static Integer index = 0;

	public synchronized void include(ContabilBean contabilBean) throws ContabilException {
		try {
			index += 1;
			contabilBean.setId(index);
			db.add(contabilBean);
			findById(1);
		} catch (Exception e) {
			index -= 1;
			throw new ContabilException("Erro ao adicionar o registro.", HttpStatus.BAD_REQUEST.value());
		}
	}

	public ContabilBean findById(Integer id) throws ContabilException {
		List<ContabilBean> listResult = null;
		try {
			listResult = db.stream().filter(item -> item.getId() == id)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ContabilException("Erro ao buscar o registro.", HttpStatus.BAD_REQUEST.value());
		}
		if (listResult != null && !listResult.isEmpty()) {
			return listResult.get(0);
		}else {
			throw new ContabilException("Nenhum registro encontrado.", HttpStatus.NO_CONTENT.value());
		}
	}

	public List<ContabilBean> findByConta(Integer conta) throws ContabilException {
		try {
			List<ContabilBean> listResult = db.stream().filter(item -> item.getContaContabil().equals(conta))
					.collect(Collectors.toList());
			if (listResult != null && listResult.isEmpty()) {
				return listResult;
			} else {
				throw new ContabilException("Nenhum registro encontrado.", HttpStatus.NO_CONTENT.value());
			}
		} catch (Exception e) {
			throw new ContabilException("Erro ao buscar o registro.", HttpStatus.BAD_REQUEST.value());
		}
	}

	public StatsBean stats() throws ContabilException {

		StatsBean stats = new StatsBean();
		try {
			OptionalDouble media = db.stream().mapToDouble(ContabilBean::getValor).average();
			stats.setMedia(media.getAsDouble());

			OptionalDouble min = db.stream().mapToDouble(ContabilBean::getValor).min();
			stats.setMin(min.getAsDouble());

			OptionalDouble max = db.stream().mapToDouble(ContabilBean::getValor).max();
			stats.setMax(max.getAsDouble());
			
			Double sum = db.stream().mapToDouble(ContabilBean::getValor).sum();
			stats.setSoma(sum);

			stats.setQtde(db.size());
		} catch (Exception e) {
			throw new ContabilException("Erro verificar o status.", HttpStatus.BAD_REQUEST.value());
		}
		return stats;
	}
}
