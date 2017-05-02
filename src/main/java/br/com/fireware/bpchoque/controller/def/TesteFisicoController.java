package br.com.fireware.bpchoque.controller.def;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.model.def.TipoTeste;

import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.service.def.ProvaService;
import br.com.fireware.bpchoque.service.def.TesteFisicoService;
import br.com.fireware.bpchoque.service.def.TipoTesteService;

@Controller
@RequestMapping("/testesFisicos")
public class TesteFisicoController {

	private static final String CADASTRO_TESTE_FISICO = "testesFisicos/CadastroTesteFisico";

	@Autowired
	private TesteFisicoService testeFisicoService;

	@Autowired
	private TipoTesteService tipoTesteService;

	private List<TipoTeste> tiposTestes;

	private TesteFisico testeFisico;
	
	private boolean testeFisicoSalvo = false;
	
	@RequestMapping
	public ModelAndView tiposTeste() {

		Iterable<TesteFisico> todosTestes = testeFisicoService.findAll();
		ModelAndView mv = new ModelAndView("testesFisicos/TestesFisicos");
		mv.addObject("testes", todosTestes);

		return mv;
	}

	

	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();
		List<TipoTeste> todosTipos = tipoTesteService.findAll();
		tiposTestes = new ArrayList<TipoTeste>();
		mv.setViewName(CADASTRO_TESTE_FISICO);
		this.testeFisico = new TesteFisico();
		testeFisicoSalvo = false;
		mv.addObject("testeFisico", testeFisico);
		mv.addObject("provasIncluir", todosTipos);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated TesteFisico testeFisico, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		if (errors.hasErrors()) {
			return CADASTRO_TESTE_FISICO;
		}
		testeFisico.setAtualizadoem(LocalDateTime.now());
		testeFisico.setAtualizadopor(usuarioSistema.getUsername());

		if (testeFisico.getCriadopor() == null || testeFisico.getCriadopor().equals("")) {
			testeFisico.setCriadoem(LocalDateTime.now());
			testeFisico.setCriadopor(usuarioSistema.getUsername());
		}
		
		try {
			
			testeFisicoService.save(testeFisico);
			testeFisicoSalvo = true;
			attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
			return "redirect:/tiposTeste/" + testeFisico.getId();
		} catch (IllegalArgumentException e) {

			return CADASTRO_TESTE_FISICO;
		}
	}

	@RequestMapping(value = "/provaNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView salvarDetalhe(@RequestBody @Valid TipoTeste tipoTeste, BindingResult result, RedirectAttributes attributes,
			Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		ModelAndView mv = new ModelAndView();
		
		// detalhe.setTipoTeste(tipoTeste);
		tipoTeste = tipoTesteService.findById(tipoTeste.getId());
		
		
		
		testeFisico.getProvas().add(prova);
		mv.addObject("tipoTeste", tipoTeste);
	
		//provas = tipoTeste.getProvas();
		
			
			
			tipoTesteService.save(tipoTeste);
			

			attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
			mv.setViewName("redirect:/tiposTeste/" + tipoTeste.getId());
			return mv;
		
		
	}

	

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") TipoTeste tipoTeste) {
		ModelAndView mv = new ModelAndView(CADASTRO_TESTE_FISICO);
		this.tipoTeste = tipoTeste;
		mv.addObject(tipoTeste);
		
		List<Prova> provasIncluir = provaService.findAll();
		for(int i = 0; i <  provasIncluir.size(); i++){
			for(int j = 0; j < tipoTeste.getProvas().size();j++){
				if(tipoTeste.getProvas().get(j).getId() == provasIncluir.get(i).getId()){
					provasIncluir.remove(i);
				}
			}
		}
		testeFisicoSalvo = true;
		provasIncluir.remove(tipoTeste.getProvas());
		
		mv.addObject("provasIncluir", provasIncluir);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		
		return mv;
	}

	public List<Prova> listaDetalhes() {
		return provas;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		TipoTeste tipoTeste = tipoTesteService.findById(id);
		// provaService.deleteByTipoTeste(tipoTeste);
		tipoTesteService.delete(id);

		return "redirect:/tiposTeste";
	}

	@RequestMapping(value = "/deleteProva/{id}")
	public ModelAndView excluirProva(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		
		
		Prova provaRemover = provaService.findById(id); 
		
		tipoTeste.getProvas().remove(provaRemover);
		
		tipoTesteService.save(tipoTeste);
		
		mv.addObject("tipoTeste", tipoTeste);
		mv.setViewName("redirect:/tiposTeste/" + tipoTeste.getId());
		return mv;
	}

}