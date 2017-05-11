package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Resultado;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TesteFisicoService {

	@Autowired
	private TesteFisicoRepository repository;

	@Autowired
	private PessoaDefService pessoaDefService;
	
	@Autowired
	private ResultadoTesteService resultadoTesteService;
	
	@Autowired
	private ProvaService provaService;
	
	@Transactional(readOnly = false)
	public void save(TesteFisico testeFisico) {

		repository.save(testeFisico);

	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(TesteFisico testeFisico) {

		repository.delete(testeFisico);

	}

	public TesteFisico findById(Long id) {

		return repository.findOne(id);

	}
	
	

	public List<TesteFisico> findAll() {
		return repository.findAll();
	}
	
	
	public void salvaResultado(Resultado resultado, TesteFisico testeFisico) {
		ResultadoTeste resultadoPronto;
		PessoaDef pessoa = pessoaDefService.findById(resultado.getPessoa());
		Prova prova;
		Integer idProvas = 0;
		Integer auxProvas = 0;
		//System.out.println("Teste Fisico: "+testeFisico.getTipos());
		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			auxProvas = 0;
			resultadoPronto = new ResultadoTeste();
			// for(int j = 0; j <
			// testeFisico.getTipos().get(i).getProvas().size(); j++){
			resultadoPronto.setPessoa(pessoa);
			resultadoPronto.setTeste(testeFisico);
			resultadoPronto.setTipoTeste(testeFisico.getTipos().get(i));
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva1(prova.getTipo()+"");
				
				resultadoPronto.setValorProva1(resultado.getValores().get(idProvas));
				idProvas++;
				auxProvas++;
				
			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva2(prova.getTipo()+"");
				resultadoPronto.setValorProva2(resultado.getValores().get(idProvas));
				idProvas++;
				auxProvas++;
			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva3(prova.getTipo()+"");
				resultadoPronto.setValorProva3(resultado.getValores().get(idProvas));
				idProvas++;
				auxProvas++;
			
			} 
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva4(prova.getTipo()+"");
				resultadoPronto.setValorProva4(resultado.getValores().get(idProvas));
				idProvas++;
				auxProvas++;
			}
			
	

			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva5(prova.getTipo()+"");
				resultadoPronto.setValorProva5(resultado.getValores().get(idProvas));
				idProvas++;
				auxProvas++;
			}

		

			resultadoTesteService.save(resultadoPronto);
		}
		
	}
	

}
