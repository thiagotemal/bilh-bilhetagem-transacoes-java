package br.com.temal.pa.rest.repository;



import br.com.temal.pa.rest.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Bill, String> {
    public Optional<Bill> findByNsuTransaction(String  nsuTransaction);
    public List<Bill> findByOriginAccount(String  OriginAccount);
}
