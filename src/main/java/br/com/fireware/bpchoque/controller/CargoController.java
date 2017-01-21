package br.com.fireware.bpchoque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fireware.bpchoque.model.Cargo;
import br.com.fireware.bpchoque.service.CargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	private static final String CADASTRO_VIEW = "Cargos/CadastroCargo";
	
	@Autowired
	private CargoService cargoService;
	
	@RequestMapping
	public ModelAndView cargos() {
		Iterable<Cargo> todosCargos = cargoService.findAll();
		ModelAndView mv = new ModelAndView("Cargos/Cargos");
		mv.addObject("cargos", todosCargos);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cargo());
		return mv;
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cargo cargo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		cargo.setNome(cargo.getNome().toUpperCase());
		cargo.setAbreviacao(cargo.getAbreviacao().toUpperCase());
		
		try {
			cargoService.save(cargo);
			attributes.addFlashAttribute("mensagem", "Cargo salvo com sucesso!");
			return "redirect:/cargos";
		} catch (IllegalArgumentException e) {
			
			return CADASTRO_VIEW;
		}
	}
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Cargo cargo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cargo);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		cargoService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/cargos";
	}
	
	
	
	
	
}