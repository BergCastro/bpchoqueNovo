package br.com.fireware.bpchoque.controller.def;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.fireware.bpchoque.model.def.PessoaDef;

import br.com.fireware.bpchoque.service.CargoService;
import br.com.fireware.bpchoque.service.OpmOrgaoService;
import br.com.fireware.bpchoque.service.def.PessoaService;
import br.com.fireware.bpchoque.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/pessoasdef2")
public class PessoaDefController {
	
	
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CargoService cargoService;
	@Autowired
	private OpmOrgaoService opmOrgaoService;
	
	@RequestMapping
	public ModelAndView pessoas() {
		Iterable<PessoaDef> todosPessoas = pessoaService.findAll();
		ModelAndView mv = new ModelAndView("pessoasdef/PesquisaPessoas2");
		mv.addObject("pessoas", todosPessoas);
		
		return mv;
	}

	@RequestMapping("/nova")
	public ModelAndView nova(PessoaDef pessoadef) {
		ModelAndView mv = new ModelAndView("pessoasdef/CadastroPessoa2");
		mv.addObject("cargos", cargoService.findAll());
		mv.addObject("opms", opmOrgaoService.findAll());
		
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid PessoaDef pessoadef, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(pessoadef);
		}
		
		pessoaService.save(pessoadef);
		attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
		return new ModelAndView("redirect:/pessoasdef2/nova");
	}
	
	
	
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") PessoaDef pessoadef) {
		try {
			pessoaService.delete(pessoadef);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") PessoaDef pessoadef) {
		ModelAndView mv = nova(pessoadef);
		mv.addObject(pessoadef);
		return mv;
	}
	
}
