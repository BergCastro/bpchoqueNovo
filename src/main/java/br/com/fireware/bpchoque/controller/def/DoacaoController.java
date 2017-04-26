package br.com.fireware.bpchoque.controller.def;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.service.def.DoacaoDetalheService;
import br.com.fireware.bpchoque.service.def.DoacaoService;

@Controller
@RequestMapping("/doacoes")
public class DoacaoController {
	
	private static final String CADASTRO_DOACAO = "doacoes/CadastroDoacoes";
	private static final String CADASTRO_VALOR = "doacoes/CadastroDoacaoValor";
	
	@Autowired
	private DoacaoService doacaoService;
	
	@Autowired
	private DoacaoDetalheService doacaoDetalheService;
	
	List<DoacaoDetalhe> detalhes;
	
	private Doacao doacao;
	
	@RequestMapping
	public ModelAndView doacoes() {
		Iterable<Doacao> todosDoacoes = doacaoService.findAll();
		ModelAndView mv = new ModelAndView("doacoes/doacoes");
		mv.addObject("doacoes", todosDoacoes);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Doacao> listaDoacoes() {
		List<Doacao> todosDoacoes = doacaoService.findAll();
		
		return todosDoacoes;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(CADASTRO_DOACAO);
		Doacao doacao = new Doacao();
		doacao.setDataDoacao(LocalDate.now());
		
		mv.addObject("doacao", doacao);
		mv.addObject("tipos", DoacaoTipo.values());
		return mv;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Doacao doacao, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {
			return CADASTRO_DOACAO;
		}
		doacao.setAtualizadoem(LocalDateTime.now());
		doacao.setAtualizadopor(usuarioSistema.getUsername());

		if (doacao.getCriadopor() == null || doacao.getAtualizadopor().equals("")) {
			doacao.setCriadoem(LocalDateTime.now());
			doacao.setCriadopor(usuarioSistema.getUsername());
		}

		try {
			doacaoService.save(doacao);
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			return "redirect:/doacoes";
		} catch (IllegalArgumentException e) {

			return CADASTRO_DOACAO;
		}
	}
	
	@RequestMapping(value="/detalheNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarDetalhe(@RequestBody @Valid DoacaoDetalhe detalhe , Doacao doacao, BindingResult result) {
		System.out.println("Entrou no detalhe Novo");
		System.out.println(detalhe.toString());
		System.out.println(doacao.toString());
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		detalhe.setDoacao(doacao);
		detalhe = doacaoDetalheService.save(detalhe);
		return ResponseEntity.ok(detalhe);
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doacao doacao) {
		ModelAndView mv = new ModelAndView(CADASTRO_DOACAO);
		mv.addObject(doacao);
		return mv;
	}
	
	public List<DoacaoDetalhe> listaDetalhes(){
		return detalhes;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idDoacao, DoacaoDetalhe detalhe ) {
		Doacao doacao = doacaoService.findById(idDoacao);
		ModelAndView mv = new ModelAndView("doacoes/doacoes");
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid DoacaoDetalhe detalhe, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		//doacao = doacaoService.findById(id);
		
		return ResponseEntity.ok(detalhe);
	}
	
	
	
	
	@RequestMapping(value="/salvar" ,method = RequestMethod.POST)
	public String salvar( @Validated Doacao doacao, @Validated DoacaoDetalhe doacaoDetalhe, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_DOACAO;
		}
		
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		doacao.setDatanasc(dataNascimento);*/
		
			
			
		
		try {
			doacaoService.save(doacao);
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			
			return "redirect:/doacoes";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_DOACAO;
		}
	}
	
//	@PostMapping("/item")
//	public ModelAndView adicionarItem(Long id, Integer quantidade) {
//		Doacao doacao = doacaoService.findById(id);
//		//tabelaItens.adicionarItem(doacao, 1);
//		return mv;
//	}
	
	
	
	
	
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		doacaoService.delete(id);
		
		
		
		return "redirect:/doacoes";
	}
	
	
	
	
}