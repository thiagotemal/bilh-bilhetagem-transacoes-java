package br.com.temal.pa.rest.service;


import br.com.temal.pa.rest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;



//    public Invoice save(Invoice person) {
//        return this.personRepository.save(person.toEntity()).toModel();
//    }
//
//    public boolean delete(Long id) {
//        return this.personRepository.deleteById(id);
//    }

}
