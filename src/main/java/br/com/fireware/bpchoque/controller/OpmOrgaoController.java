package br.com.fireware.bpchoque.controller;


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


import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.OpmOrgao.TipoOpm;

import br.com.fireware.bpchoque.service.OpmOrgaoService;


@Controller
@RequestMapping("/opms")
public class OpmOrgaoController {
	
	private static final String CADASTRO_OPM = "OpmsOrgaos/CadastroOpmOrgao";
	
	
	
	@Autowired
	private OpmOrgaoService opmOrgaoService;
	
	
	@RequestMapping
	public ModelAndView opms() {
		Iterable<OpmOrgao> todasOpms = opmOrgaoService.findAll();
		ModelAndView mv = new ModelAndView("OpmsOrgaos/OpmsOrgaos");
		mv.addObject("opms", todasOpms);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
			ModelAndView mv = new ModelAndView(CADASTRO_OPM);
			mv.addObject(new OpmOrgao());
			return mv;
						
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated OpmOrgao opm, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_OPM;
		}
		
		opm.setNome(opm.getNome().toUpperCase());
		
		try {
			opmOrgaoService.save(opm);
			attributes.addFlashAttribute("mensagem", "OPM salva com sucesso!");
			return "redirect:/opms";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_OPM;
		}
	}
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") OpmOrgao opm) {
		ModelAndView mv = new ModelAndView(CADASTRO_OPM); 
		mv.addObject(opm);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		opmOrgaoService.delete(id);
				
		return "redirect:/opms";
	}
	
	
	@ModelAttribute("tiposOpms")
	public List<TipoOpm> tiposOpms() {
		return Arrays.asList(TipoOpm.values());
	}
	
	
	
	
	
}