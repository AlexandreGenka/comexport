package br.com.comexport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.comexport.beans.ContabilBean;
import br.com.comexport.beans.StatsBean;
import br.com.comexport.exceptions.ContabilException;
import br.com.comexport.service.ContabilService;

@RestController
public class ContabilController {

	@Autowired
	private ContabilService contabilService;

	@RequestMapping(path = "/lancamentos-contabeis/", method = RequestMethod.POST)
	public void lancamentosContabeis(@RequestBody ContabilBean contabilBean) throws ContabilException {
		if (contabilBean != null) {
			contabilService.include(contabilBean);
		} else {
			throw new ContabilException("Objeto contabilBean obrigatório.", HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(path = "/lancamentos-contabeis/{id}", method = RequestMethod.GET)
	public @ResponseBody ContabilBean buscaPorId(@PathVariable(value = "id") Integer id) throws ContabilException {
		if (id != null) {
			ContabilBean contabilBean = contabilService.findById(id);
			return contabilBean;
		} else {
			throw new ContabilException("Parâmetro id obrigatório.", HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(path = "/lancamentos-contabeis/", method = RequestMethod.GET)
	public @ResponseBody List<ContabilBean> buscaPorConta(HttpServletRequest request) throws ContabilException {
		try {
			String conta = request.getParameter("contaContabil");
			if (conta != null && !conta.isEmpty()) {
				List<ContabilBean> lista = contabilService.findByConta(Integer.parseInt(conta));
				if(lista != null && lista.isEmpty()) {
					throw new ContabilException("Nenhum registro encontrado.", HttpStatus.NO_CONTENT.value());
				}
				return lista;
			} else {
				throw new ContabilException("Parâmetro contaContabil obrigatório.", HttpStatus.BAD_REQUEST.value());
			}
		} catch (NumberFormatException e) {
			throw new ContabilException("Erro ao buscar o registro.", HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(path = "/lancamentos-contabeis/_stats/", method = RequestMethod.GET)
	public @ResponseBody StatsBean stats() throws ContabilException {
		return contabilService.stats();
	}

	@ExceptionHandler
	private void handleContabilException(ContabilException e, HttpServletResponse response) throws IOException {
		if (e.getStatusCode() != null) {
			response.sendError(e.getStatusCode(), e.getMessage());
		} else {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

}
