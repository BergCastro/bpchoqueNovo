package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TesteFisicoService {

	@Autowired
	private TesteFisicoRepository repository;

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
	
	

}
