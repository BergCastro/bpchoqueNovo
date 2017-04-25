package br.com.fireware.bpchoque.controller.def;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.service.def.DoacaoDetalheService;
import br.com.fireware.bpchoque.service.def.DoacaoService;

@Controller
@RequestMapping("/doacoes")
public class DoacaoController {
	
	private static final String CADASTRO_OBJETO = "Doacoes/CadastroDoacaoObjeto";
	private static final String CADASTRO_VALOR = "Doacoes/CadastroDoacaoValor";
	
	@Autowired
	private DoacaoService doacaoService;
	
	@Autowired
	private DoacaoDetalheService doacaoDetalheService;
	
	List<DoacaoDetalhe> detalhes;
	
	Doacao doacao;
	
	@RequestMapping
	public ModelAndView doacoes() {
		Iterable<Doacao> todosDoacoes = doacaoService.findAll();
		ModelAndView mv = new ModelAndView("Doacoes/Doacoes");
		mv.addObject("doacoes", todosDoacoes);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Doacao> listaDoacoes() {
		List<Doacao> todosDoacoes = doacaoService.findAll();
		
		return todosDoacoes;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(@RequestParam(required=false) String tipo) {
		
		ModelAndView mv = new ModelAndView();
		detalhes = new ArrayList<DoacaoDetalhe>();
		doacao = new Doacao();
		mv.addObject(doacao);	
		
			
		return mv;
			
		
		
	}
	
	public List<DoacaoDetalhe> listaDetalhes(){
		return detalhes;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idDoacao, DoacaoDetalhe detalhe ) {
		Doacao doacao = doacaoService.findById(idDoacao);
		ModelAndView mv = new ModelAndView("Doacoes/Doacoes");
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
			return CADASTRO_OBJETO;
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
			return CADASTRO_OBJETO;
		}
	}
	
//	@PostMapping("/item")
//	public ModelAndView adicionarItem(Long id, Integer quantidade) {
//		Doacao doacao = doacaoService.findById(id);
//		//tabelaItens.adicionarItem(doacao, 1);
//		return mv;
//	}
	
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doacao doacao) {
		
		ModelAndView mv = new ModelAndView();
		
		
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		doacaoService.delete(id);
		
		
		
		return "redirect:/doacoes";
	}
	
	
	
	
}