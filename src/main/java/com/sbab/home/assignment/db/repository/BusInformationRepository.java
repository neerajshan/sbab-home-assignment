package com.sbab.home.assignment.db.repository;

import com.sbab.home.assignment.db.model.Businformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;


public interface BusInformationRepository extends CrudRepository<Businformation, Long> {
    Collection<Businformation> findByBusnumber(String busnumber);


    @Query("SELECT distinct busnumber FROM Businformation")
    Collection<String> findAllUniqueBusNumbers();


    @Query(
            value = "SELECT BUSNUMBER , COUNT(BUSNUMBER) AS STOPCOUNTS  FROM BUSINFORMATION WHERE DIRECTIONCODE = 2 GROUP BY BUSNUMBER ORDER BY STOPCOUNTS DESC",
            nativeQuery = true)
    List<TopBusResult> findTopBusNumbers();


    interface TopBusResult {
        String getBusNumber();


        Integer getStopCounts();
    }

}