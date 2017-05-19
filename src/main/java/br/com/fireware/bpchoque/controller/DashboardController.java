package br.com.fireware.bpchoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.repository.Cervejas;
import br.com.fireware.bpchoque.repository.Clientes;
import br.com.fireware.bpchoque.repository.Vendas;
import br.com.fireware.bpchoque.repository.def.AvaliacaoIndividualRepository;
import br.com.fireware.bpchoque.repository.def.PessoaDefRepository;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;

@Controller
public class DashboardController {

	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private PessoaDefRepository pessoas;
	
	@Autowired
	private TesteFisicoRepository testesFisicos;
	
	@Autowired
	private AvaliacaoIndividualRepository testesIndividuais;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());
		
		mv.addObject("valorItensEstoque", cervejas.valorItensEstoque());
		mv.addObject("totalPessoas", pessoas.count());
		mv.addObject("testesColetivos", testesFisicos.count());
		mv.addObject("testesIndividuais", testesIndividuais.count());
		return mv;
	}
	
}
