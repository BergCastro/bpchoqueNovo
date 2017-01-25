package br.com.fireware.bpchoque.controller.def;



import java.time.LocalDate;
import java.util.Arrays;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;


import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Duracao;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Frequencia;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Objetivos;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Problemas;
import br.com.fireware.bpchoque.service.def.PessoaDefService;
import br.com.fireware.bpchoque.service.def.AvaliacaoIndividualService;

@Controller
@RequestMapping("/avaliacoesIndividuais")
public class AvaliacaoIndividualController {
	
	private static final String CADASTRO_AVALIACAO = "avaliacaoIndividual/CadastroAvaliacaoIndividual";
	
	
	@Autowired
	private AvaliacaoIndividualService avaliacaoIndividualService;
	
	@Autowired
	private PessoaDefService pessoaDefService;
	
	
	@RequestMapping
	public ModelAndView avaliacaoIndividuais() {
		Iterable<AvaliacaoIndividual> todosAvaliacaoIndividuais = avaliacaoIndividualService.findAll();
		ModelAndView mv = new ModelAndView("avaliacaoIndividual/PesquisaAvaliacaoIndividual");
		mv.addObject("avaliacoes", todosAvaliacaoIndividuais);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIACAO);
		AvaliacaoIndividual avaliacaoIndividual = new AvaliacaoIndividual();
		avaliacaoIndividual.setDataAvaliacao(LocalDate.now());
		mv.addObject(avaliacaoIndividual);
		return mv;

		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated AvaliacaoIndividual avaliacaoIndividual, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_AVALIACAO;
		}
		
		
		try {
			avaliacaoIndividualService.save(avaliacaoIndividual);
			attributes.addFlashAttribute("mensagem", "Avaliacao Individual salva com sucesso!");
			return "redirect:/avaliacoesIndividuais";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_AVALIACAO;
		}
	}
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") AvaliacaoIndividual avaliacaoIndividual) {
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIACAO); 
		mv.addObject(avaliacaoIndividual);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		avaliacaoIndividualService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/avaliacaoIndividuais";
	}
	
	@ModelAttribute("frequencias")
	public List<Frequencia> frequencias() {
		return Arrays.asList(Frequencia.values());
	}
	
	@ModelAttribute("duracoes")
	public List<Duracao> duracoes() {
		return Arrays.asList(Duracao.values());
	}
	
	@ModelAttribute("objetivos")
	public List<Objetivos> objetivos() {
		return Arrays.asList(Objetivos.values());
	}
	
	@ModelAttribute("problemas")
	public List<Problemas> problemas() {
		return Arrays.asList(Problemas.values());
	}
	
	@ModelAttribute("pessoas")
	public Iterable<PessoaDef> pessoas() {
		return pessoaDefService.findAll();
	}
	
	@ModelAttribute("avaliadores")
	public Iterable<PessoaDef> avaliadores() {
		return pessoaDefService.findAll();
	}
	
}