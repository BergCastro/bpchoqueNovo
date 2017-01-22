package br.com.fireware.bpchoque.controller.def;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.model.Cargo;

import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.PessoaDef.EstadoCivil;
import br.com.fireware.bpchoque.model.TipoPessoa;
import br.com.fireware.bpchoque.model.Sexo;


import br.com.fireware.bpchoque.service.CargoService;

import br.com.fireware.bpchoque.service.OpmOrgaoService;
import br.com.fireware.bpchoque.service.def.PessoaService;

@Controller
@RequestMapping("/pessoasdef")
public class PessoaController {
	
	private static final String CADASTRO_PESSOA = "pessoasdef/CadastroPessoa";
		
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private OpmOrgaoService opmOrgaoService;
	
	
	
	@RequestMapping
	public ModelAndView pessoas() {
		Iterable<PessoaDef> todosPessoas = pessoaService.findAll();
		ModelAndView mv = new ModelAndView("pessoasdef/PesquisaPessoas");
		mv.addObject("pessoas", todosPessoas);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<PessoaDef> listaPessoas() {
		List<PessoaDef> todosPessoas = pessoaService.findAll();
		
		return todosPessoas;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(@RequestParam(required=false) String tipo) {
		
		ModelAndView mv = new ModelAndView();
		
			
			
			mv.setViewName("pessoasdef/CadastroPessoa");
			
			mv.addObject("pessoa", new PessoaDef());
			return mv;
			
	
		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar( @Validated PessoaDef pessoa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_PESSOA;
		}
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		pessoa.setDatanasc(dataNascimento);*/
		
			pessoa.setNome(pessoa.getNome().toUpperCase());		
			pessoa.setNome_guerra(pessoa.getNome_guerra().toUpperCase());
			pessoa.setEmail(pessoa.getEmail().toLowerCase());
			
			
			
		
		try {
			pessoaService.save(pessoa);
			attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
			return "redirect:/pessoas";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_PESSOA;
		}
	}
	
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") PessoaDef pessoa) {
		
		ModelAndView mv = new ModelAndView();
		
		
			mv.setViewName(CADASTRO_PESSOA);
		
			
		
		mv.addObject(pessoa);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		pessoaService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/pessoas";
	}
	
	@ModelAttribute("sexos")
	public List<Sexo> sexos() {
		return Arrays.asList(Sexo.values());
	}
	
	@ModelAttribute("estadosCivis")
	public List<EstadoCivil> estadosCivis() {
		return Arrays.asList(EstadoCivil.values());
	}
	
	@ModelAttribute("tiposPessoa")
	public List<TipoPessoa> tiposPessoa() {
		return Arrays.asList(TipoPessoa.values());
	}
	
	@ModelAttribute("cargos")
	public Iterable<Cargo> cargos() {
		return cargoService.findAll();
	}
	
	
			
	@ModelAttribute("opms")
	public Iterable<OpmOrgao> opms() {
		return opmOrgaoService.findByTipoMilitar();
	}
	@ModelAttribute("orgaos")
	public Iterable<OpmOrgao> orgaos() {
		return opmOrgaoService.findByTipoCivil();
	}
	
	
}