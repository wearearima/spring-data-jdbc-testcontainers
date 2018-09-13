package eu.arima.springdatajdbctestcontainers;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

    @Query("SELECT COUNT(*) FROM ACCOUNT")
    int accountsLength();

    @Modifying
    @Query("UPDATE ACCOUNT SET name = :name WHERE id = :id")
    boolean updateName(@Param("id") int id, @Param("name") String name);

    @Modifying
    @Query("DELETE FROM ACCOUNT WHERE id in (:ids)")
    void deleteIdList(@Param("ids") List<Integer> ids);

}
