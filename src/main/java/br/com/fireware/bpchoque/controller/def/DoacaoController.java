package br.com.fireware.bpchoque.controller.def;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.lucene.queryparser.flexible.standard.builders.FieldQueryNodeBuilder;
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
	
	private List<DoacaoDetalhe> detalhes;
	
	private Doacao doacao;
	
	@RequestMapping
	public ModelAndView doacoes() {
		/*if(detalhes.get(0).getDoacao()==null){
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
		}*/
		
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
		detalhes = new ArrayList<DoacaoDetalhe>();
		mv.setViewName(CADASTRO_DOACAO);
		this.doacao = new Doacao();
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
			
			System.out.println("Entrou no salvar");
			doacaoService.save(doacao);
			System.out.println("Salvou doacao");
			for(DoacaoDetalhe detalhe: detalhes){
				System.out.println(detalhe.toString());
				detalhe.setDoacao(doacao);
				System.out.println(detalhe.toString());
				doacaoDetalheService.save(detalhe);
			}
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			return "redirect:/doacoes";
		} catch (IllegalArgumentException e) {

			return CADASTRO_DOACAO;
		}
	}
	
	@RequestMapping(value="/detalheNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarDetalhe(@RequestBody @Valid DoacaoDetalhe detalhe , BindingResult result) {
		/*System.out.println("Entrou no detalhe Novo");
		System.out.println(detalhe.toString());
		System.out.println(doacao.toString());*/
		
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		detalhe.setDoacao(doacao);
		
		detalhes.add(detalhe);
		return ResponseEntity.ok(detalhe);
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doacao doacao) {
		ModelAndView mv = new ModelAndView(CADASTRO_DOACAO);
		mv.addObject(doacao);
		List<DoacaoDetalhe> detalhesBusca = doacaoDetalheService.findByDoacao(doacao);
		detalhes = new ArrayList<DoacaoDetalhe>();
		detalhes.addAll(detalhesBusca);
		mv.addObject("detalhes", detalhes);
		mv.addObject("tipos", DoacaoTipo.values());
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
	
	
	
	
	
	
	
	
	
//	@PostMapping("/item")
//	public ModelAndView adicionarItem(Long id, Integer quantidade) {
//		Doacao doacao = doacaoService.findById(id);
//		//tabelaItens.adicionarItem(doacao, 1);
//		return mv;
//	}
	
	
	
	
	
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Doacao doacao = doacaoService.findById(id);
		doacaoDetalheService.deleteByDoacao(doacao);
		doacaoService.delete(id);
		
		
		
		
		return "redirect:/doacoes";
	}
	
	
	
	
}