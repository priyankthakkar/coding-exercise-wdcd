package io.priyank.wdcd.repository;

import io.priyank.wdcd.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {

    @Query("from Transaction t where t.account.accountNumber =:accountId order by t.valueDate desc")
    Page<Transaction> findAllTransactionsByAccountId(@Param("accountId") Integer accountId, Pageable pageable);
}
