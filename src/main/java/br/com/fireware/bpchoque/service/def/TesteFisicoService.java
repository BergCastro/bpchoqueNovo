package br.com.fireware.bpchoque.service.def;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Prova.CampoTipo;
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
		// System.out.println("Teste Fisico: "+testeFisico.getTipos());
		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			auxProvas = 0;
			resultadoPronto = new ResultadoTeste();
			// for(int j = 0; j <
			// testeFisico.getTipos().get(i).getProvas().size(); j++){
			resultadoPronto.setPessoa(pessoa);
			resultadoPronto.setTeste(testeFisico);
			resultadoPronto.setTipoTeste(testeFisico.getTipos().get(i));
			resultadoPronto.setPontuacaoProva1(0.00);
			resultadoPronto.setPontuacaoProva2(0.00);
			resultadoPronto.setPontuacaoProva3(0.00);
			resultadoPronto.setPontuacaoProva4(0.00);
			resultadoPronto.setPontuacaoProva5(0.00);

			Integer qtdCalculoMedia = testeFisico.getTipos().get(i).getQtdProvasMedia();
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva1(prova.getTipo() + "");
				resultadoPronto.setValorProva1(resultado.getValores().get(idProvas));

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva1() == 1.00) {
						resultadoPronto.setPontuacaoProva1(100.00);
					} else {
						resultadoPronto.setPontuacaoProva1(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva1(calculaPontuacao(prova, resultadoPronto.getValorProva1(), 35));
				}

				idProvas++;
				auxProvas++;

			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva2(prova.getTipo() + "");
				resultadoPronto.setValorProva2(resultado.getValores().get(idProvas));

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva2() == 1.00) {
						resultadoPronto.setPontuacaoProva2(100.00);
					} else {
						resultadoPronto.setPontuacaoProva2(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva2(calculaPontuacao(prova, resultadoPronto.getValorProva2(), 35));
				}

				idProvas++;
				auxProvas++;
			}

			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva3(prova.getTipo() + "");
				resultadoPronto.setValorProva3(resultado.getValores().get(idProvas));

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva3() == 1.00) {
						resultadoPronto.setPontuacaoProva3(100.00);
					} else {
						resultadoPronto.setPontuacaoProva3(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva3(calculaPontuacao(prova, resultadoPronto.getValorProva3(), 35));
				}

				idProvas++;
				auxProvas++;

			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva4(prova.getTipo() + "");
				resultadoPronto.setValorProva4(resultado.getValores().get(idProvas));

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva4() == 1.00) {
						resultadoPronto.setPontuacaoProva4(100.00);
					} else {
						resultadoPronto.setPontuacaoProva4(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva4(calculaPontuacao(prova, resultadoPronto.getValorProva4(), 35));
				}

				idProvas++;
				auxProvas++;
			}

			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva5(prova.getTipo() + "");
				resultadoPronto.setValorProva5(resultado.getValores().get(idProvas));

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva5() == 1.00) {
						resultadoPronto.setPontuacaoProva5(100.00);
					} else {
						resultadoPronto.setPontuacaoProva5(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva5(calculaPontuacao(prova, resultadoPronto.getValorProva5(), 35));
				}

				idProvas++;
				auxProvas++;
			}
			resultadoPronto.setNotaFinal(((resultadoPronto.getPontuacaoProva1() + resultadoPronto.getPontuacaoProva2()
					+ resultadoPronto.getPontuacaoProva3() + resultadoPronto.getPontuacaoProva4()
					+ resultadoPronto.getPontuacaoProva5()) / qtdCalculoMedia) / 10);

			resultadoTesteService.save(resultadoPronto);
		}

	}

	public Double calculaPontuacao(Prova prova, Double valor, Integer idade) {
		Double referenciaInicialMasc = prova.getRefInicialMasc();
		Double referenciaFinalMasc = prova.getRefFinalMasc();
		Double referenciaInicialFem = prova.getRefInicialFem();
		Double referenciaFinalFem = prova.getRefFinalFem();
		Double intervaloReferencia = prova.getIntervaloRef();
		Integer idadeInicial = prova.getIdadeInicial();
		Integer idadeFinal = prova.getIdadeFinal();
		Integer intervaloIdade = prova.getIntervaloIdade();
		Integer faixaInicioPontuacao = 1;

		Double resultado = 0.00;

		if (prova.getId() == 1) {// FLEXÃO BARRA
			faixaInicioPontuacao = 3;
			Double auxResultado = 10.00;
			Double auxReferencia = 3.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;
			System.out.println("valor é: " + valor);
			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valor.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valor >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {
						System.out.println("entrou if resultado");

						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11;
			}

		}

		if (prova.getId() == 2) {// FLEXÃO SOLO
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 26.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;
			System.out.println("valor é: " + valor);
			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valor.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valor >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {
						System.out.println("entrou if resultado");

						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor >= auxReferencia && valor < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 3) {// ABDOMINAL
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 30.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;
			System.out.println("valor é: " + valor);
			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valor.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valor >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {
						System.out.println("entrou if resultado");

						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor >= auxReferencia && valor < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 4) {// CORRIDA 12MIN
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 2000.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;
			System.out.println("valor é: " + valor);
			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valor.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valor >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {
						System.out.println("entrou if resultado");

						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor >= auxReferencia && valor < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valor < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 5) {// CORRIDA 50 M
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 9.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;
			System.out.println("valor é: " + valor);
			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaInicialMasc
						- (referenciaFinalMasc - intervaloReferencia)) / intervaloReferencia; i++) {
					System.out.println("Valor: " + valor);
					System.out.println("Idade: " + idade);
					System.out.println("FaixaIdade: " + auxIdade + "-" + (auxIdade + intervaloIdade));
					System.out.println("AuxReferencia: " + auxReferencia);
					System.out.println("AuxResultado: " + auxResultado);
					System.out.println("AuxMaxREferencia: " + auxMaxRefIdade);

					if (valor <= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {
						System.out.println("entrou if resultado");

						if (valor > auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor > auxReferencia - intervaloReferencia && valor <= auxReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor <= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valor > auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valor.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valor <= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia -= intervaloReferencia;

				}
				auxMaxRefIdade += intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia += 11 * intervaloReferencia;
			}

		}
		if (prova.getId() >= 5 && prova.getTipo()==CampoTipo.INTEIRO && prova.getIdadeInicial()==null) {// função genérica
			faixaInicioPontuacao = 1;
			Double auxResultado = 10.00;
			Double auxReferencia = referenciaInicialMasc;
			
			
				for (int i = 1;i<10; i++) {
					
					 System.out.println("Valor: " + valor);
					 
					
					 System.out.println("AuxReferencia: " + auxReferencia);
					 System.out.println("AuxResultado: " + auxResultado);
					
					 
					if (valor.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						
						break;
					} else if (valor > referenciaFinalMasc-intervaloReferencia) {
						resultado = 100.00;
						
						break;

					} else if ((valor >= auxReferencia && valor < auxReferencia + intervaloReferencia)) {
						resultado = auxResultado;
						
						break;
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				
				
				
			}
		if (prova.getId() >= 5 && prova.getTipo()==CampoTipo.TEMPO && prova.getIdadeInicial()==null) {//GENÉRICA , SEM IDADE, TEMPO
			faixaInicioPontuacao = 1;
			Double auxResultado = 10.00;
			Double auxReferencia = referenciaInicialMasc;
			
			
				for (int i = 1;i<10; i++) {
					
					 System.out.println("Valor: " + valor);
					 
					
					 System.out.println("AuxReferencia: " + auxReferencia);
					 System.out.println("AuxResultado: " + auxResultado);
					
					 if (valor > referenciaInicialMasc) {
							resultado = 0.00;
							
							break;

					}else if (valor <= referenciaFinalMasc) {
						resultado = 100.00;
						
						break;

					} else if ((valor > auxReferencia - intervaloReferencia && valor <= auxReferencia)) {
						resultado = auxResultado;
						
						break;
					}

					auxResultado += 10;
					auxReferencia -= intervaloReferencia;

				}
				
				
				
			}

		

		return resultado;

	}

	public List<PessoaDef> pessoasIncluir(List<ResultadoTeste> resultados) {
		List<PessoaDef> pessoasIncluir = pessoaDefService.findAll();

		List<PessoaDef> pessoas = new ArrayList<>(); // Não Permite objetos
														// repetidos
		for (ResultadoTeste resultado : resultados) {
			pessoas.add(resultado.getPessoa());
		}

		if (pessoasIncluir.size() > 0) {

			for (int i = 0; i < pessoasIncluir.size(); i++) {
				for (int j = 0; j < pessoas.size(); j++) {
					if (pessoas.get(j).getId() == pessoasIncluir.get(i).getId()) {
						pessoasIncluir.remove(i);
					}
				}
			}

		}

		return pessoasIncluir;
	}

}
