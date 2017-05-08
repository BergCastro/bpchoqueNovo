package br.com.fireware.bpchoque.controller.def;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Resultado;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.service.def.PessoaDefService;
import br.com.fireware.bpchoque.service.def.ProvaService;
import br.com.fireware.bpchoque.service.def.ResultadoTesteService;
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

	@Autowired
	private ResultadoTesteService resultadoTesteService;

	@Autowired
	private PessoaDefService pessoaDefService;

	@Autowired
	private ProvaService provaService;

	private List<TipoTeste> tiposTestes;

	private TesteFisico testeFisico;

	private boolean testeFisicoSalvo = false;

	private boolean temTipo = false;

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
		testeFisico.setData(LocalDate.now());
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
			this.testeFisico = testeFisico;
			testeFisicoSalvo = true;
			attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
			return "redirect:/testesFisicos/" + testeFisico.getId();
		} catch (IllegalArgumentException e) {

			return CADASTRO_TESTE_FISICO;
		}
	}

	@RequestMapping(value = "/tipoNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView salvarTipo(@RequestBody @Valid TipoTeste tipoTeste, BindingResult result,
			RedirectAttributes attributes, Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		ModelAndView mv = new ModelAndView();

		// detalhe.setTipoTeste(tipoTeste);
		tipoTeste = tipoTesteService.findById(tipoTeste.getId());

		testeFisico.getTipos().add(tipoTeste);

		mv.addObject("tipoTeste", tipoTeste);

		// provas = tipoTeste.getProvas();

		testeFisicoService.save(testeFisico);

		attributes.addFlashAttribute("mensagem", "Teste salvo com sucesso!");
		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return mv;

	}

	@RequestMapping(value = "/resultadoNovo", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView salvarResultado(@RequestBody @Valid Resultado resultado, BindingResult result,
			RedirectAttributes attributes, Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		ModelAndView mv = new ModelAndView();

		System.out.println("Resultado: " + resultado);
		//List<ResultadoTeste> resultadosTeste = new ArrayList<>();
		ResultadoTeste resultadoPronto;
		PessoaDef pessoa = pessoaDefService.findById(resultado.getPessoa());

		Integer idProvas = 0;
		//System.out.println("Teste Fisico: "+testeFisico.getTipos());
		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			resultadoPronto = new ResultadoTeste();
			// for(int j = 0; j <
			// testeFisico.getTipos().get(i).getProvas().size(); j++){
			resultadoPronto.setPessoa(pessoa);
			resultadoPronto.setTeste(testeFisico);
			resultadoPronto.setTipoTeste(testeFisico.getTipos().get(i));
			if (testeFisico.getTipos().get(i).getProvas().size() >= idProvas + 1) {
				resultadoPronto.setValorProva1(resultado.getValores().get(idProvas));
				idProvas++;
			}

			if (testeFisico.getTipos().get(i).getProvas().size() >= idProvas + 1) {
				resultadoPronto.setValorProva2(resultado.getValores().get(idProvas));
				idProvas++;
			} 
			if (testeFisico.getTipos().get(i).getProvas().size() >= idProvas + 1) {
				resultadoPronto.setValorProva3(resultado.getValores().get(idProvas));
				idProvas++;
			} 
			if (testeFisico.getTipos().get(i).getProvas().size() >= idProvas + 1) {
				resultadoPronto.setValorProva4(resultado.getValores().get(idProvas));
				idProvas++;
			} 

			if (testeFisico.getTipos().get(i).getProvas().size() >= idProvas + 1) {
				resultadoPronto.setValorProva5(resultado.getValores().get(idProvas));
				idProvas++;
			}

			

			// }

			resultadoTesteService.save(resultadoPronto);
		}

		// detalhe.setTipoTeste(tipoTeste);
		// resultadoTeste = resultadoTesteService.findById(tipoTeste.getId());

		// testeFisico.getTipos().get(0).getResultados();

		// mv.addObject("tipoTeste", tipoTeste);

		// provas = tipoTeste.getProvas();

		// testeFisicoService.save(testeFisico);

		attributes.addFlashAttribute("mensagem", "Teste salvo com sucesso!");
		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return mv;

	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") TesteFisico testeFisico) {
		ModelAndView mv = new ModelAndView(CADASTRO_TESTE_FISICO);
		this.testeFisico = testeFisico;
		mv.addObject(testeFisico);

		List<TipoTeste> tiposIncluir = tipoTesteService.findAll();

		if (testeFisico.getTipos().size() > 0) {
			System.out.println("entrou edção antes if");
			for (int i = 0; i < tiposIncluir.size(); i++) {
				for (int j = 0; j < testeFisico.getTipos().size(); j++) {
					if (testeFisico.getTipos().get(j).getId() == tiposIncluir.get(i).getId()) {
						tiposIncluir.remove(i);
					}
				}
			}

		}
		List<PessoaDef> pessoasIncluir = pessoaDefService.findAll();
		if (testeFisico.getPessoas().size() > 0) {
			System.out.println("entrou edção antes if");
			for (int i = 0; i < pessoasIncluir.size(); i++) {
				for (int j = 0; j < testeFisico.getPessoas().size(); j++) {
					if (testeFisico.getPessoas().get(j).getId() == pessoasIncluir.get(i).getId()) {
						pessoasIncluir.remove(i);
					}
				}
			}

		}
		/*
		 * List<TipoTeste> tipos = new ArrayList<>(); for (int i = 0; i <
		 * testeFisico.getTipos().size(); i++) {
		 * tipos.add(testeFisico.getTipos().get(i)); }
		 */
		if (testeFisico.getTipos().size() > 0) {
			temTipo = true;
		}
		testeFisicoSalvo = true;
		// provasIncluir.remove(tipoTeste.getProvas());

		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);

		Set<PessoaDef> pessoas = new HashSet<>(); // Não Permite objetos
													// repetidos
		for (ResultadoTeste resultado : resultados) {
			pessoas.add(resultado.getPessoa());
		}
		Integer qtdProvas = 0;

		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			qtdProvas += testeFisico.getTipos().get(i).getQtdProvas();
		}

		mv.addObject("tipos", testeFisico.getTipos());
		mv.addObject("qtdProvas", qtdProvas);
		mv.addObject("pessoas", pessoas);
		mv.addObject("resultados", resultados);
		mv.addObject("tiposIncluir", tiposIncluir);
		mv.addObject("pessoasIncluir", pessoasIncluir);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		mv.addObject("temTipo", temTipo);

		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		TesteFisico testeFisico = testeFisicoService.findById(id);
		// provaService.deleteByTipoTeste(tipoTeste);
		testeFisicoService.delete(id);

		return "redirect:/testesFisicos";
	}

	@RequestMapping(value = "/deleteTipo/{id}")
	public ModelAndView excluirTipo(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();

		TipoTeste tipoRemover = tipoTesteService.findById(id);

		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			if (testeFisico.getTipos().get(i).getId() == tipoRemover.getId()) {
				testeFisico.getTipos().remove(i);
			}
		}

		testeFisicoService.save(testeFisico);

		mv.addObject("testeFisico", testeFisico);
		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return mv;
	}

}