package br.com.fireware.bpchoque.controller.def;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.Avaliador;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.service.def.AvaliadorService;
import br.com.fireware.bpchoque.service.def.PessoaDefService;


@Controller
@RequestMapping("/avaliadores")
public class AvaliadorController {

	private static final String CADASTRO_AVALIADOR = "avaliadores/CadastroAvaliador";

	@Autowired
	private AvaliadorService avaliadorService;

	@Autowired
	private PessoaDefService pessoaDefService;

	@RequestMapping
	public ModelAndView avaliadores() {
		Iterable<Avaliador> todosAvaliadores = avaliadorService.findAll();
		ModelAndView mv = new ModelAndView("avaliadores/PesquisaAvaliadores");
		mv.addObject("avaliadores", todosAvaliadores);

		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(CADASTRO_AVALIADOR);
		Avaliador avaliador = new Avaliador();
		mv.addObject(avaliador);
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Avaliador avaliador, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {
			return CADASTRO_AVALIADOR;
		}
		avaliador.setAtualizadoem(LocalDateTime.now());
		avaliador.setAtualizadopor(usuarioSistema.getUsername());

		if (avaliador.getCriadopor() == null || avaliador.getAtualizadopor().equals("")) {
			avaliador.setCriadoem(LocalDateTime.now());
			avaliador.setCriadopor(usuarioSistema.getUsername());
		}

		try {
			avaliadorService.save(avaliador);
			attributes.addFlashAttribute("mensagem", "Avaliador salvo com sucesso!");
			return "redirect:/avaliadores";
		} catch (IllegalArgumentException e) {

			return CADASTRO_AVALIADOR;
		}
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Avaliador avaliador) {
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIADOR);
		mv.addObject(avaliador);
		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		avaliadorService.delete(id);
		System.out.println("Entrou no delete");

		return "redirect:/avaliadores";
	}

	@ModelAttribute("pessoas")
	public Iterable<PessoaDef> pessoas() {
		return pessoaDefService.findAll();
	}

}