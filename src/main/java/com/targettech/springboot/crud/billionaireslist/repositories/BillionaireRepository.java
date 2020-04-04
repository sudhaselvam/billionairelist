package com.targettech.springboot.crud.billionaireslist.repositories;

import com.targettech.springboot.crud.billionaireslist.entities.Billionaire;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillionaireRepository extends CrudRepository<Billionaire,Long> {

    public List<Billionaire> findAll(Sort sort);

}
