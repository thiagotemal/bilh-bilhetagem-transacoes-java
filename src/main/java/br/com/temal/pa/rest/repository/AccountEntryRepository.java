package br.com.temal.pa.rest.repository;



import br.com.temal.pa.rest.model.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEntryRepository extends JpaRepository<AccountEntry, String> {


}
