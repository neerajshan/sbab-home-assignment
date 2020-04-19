package com.neeraj.test.demoproject.integrationTest.db.repository;

import com.neeraj.test.demoproject.integrationTest.db.model.Businformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface BusInformationRepository extends CrudRepository<Businformation, Long> {
    Collection<Businformation> findByBusnumber(String busnumber);


    @Query("SELECT distinct busnumber FROM Businformation")
    Collection<String> findAllUniqueBusNumbers();


    @Query(
            value = "SELECT BUSNUMBER , COUNT(BUSNUMBER) as TOTAL FROM BUSINFORMATION group by BUSNUMBER order by TOTAL desc",
            nativeQuery = true)
    Object[][] findTopBusNumbers();

}