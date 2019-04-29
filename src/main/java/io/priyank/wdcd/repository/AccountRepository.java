package io.priyank.wdcd.repository;

import io.priyank.wdcd.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

// We are extending PagingAndSortingRepository<> as of now to use the static pagination support available within spring boot
// We are not passing any sorting parameters as of now
@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

    // We are retrieving all the accounts as of now in paginated manner, no other parameters are considered
    @Override
    Page<Account> findAll(Pageable pageable);
}
