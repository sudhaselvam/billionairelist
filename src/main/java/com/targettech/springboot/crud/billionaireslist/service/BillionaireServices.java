package com.targettech.springboot.crud.billionaireslist.service;
import com.targettech.springboot.crud.billionaireslist.entities.Billionaire;
import com.targettech.springboot.crud.billionaireslist.repositories.BillionaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillionaireServices {

    private final BillionaireRepository billionaireRepository;

    @Autowired
    public BillionaireServices(BillionaireRepository billionaireRepository){
        this.billionaireRepository = billionaireRepository;
    }

    public List<Billionaire> findSortedBillionaireNetWorth(){
        //list of billionaires are sorted by networth in descending .
        return  billionaireRepository.findAll(Sort.by(Sort.Direction.DESC,"networth"));
    }
    /*
    findbyid method return billionaire objecy based on id .
     */
    public Billionaire findById(long id)
    {
        return billionaireRepository.findById(id).orElseThrow(() -> new  IllegalArgumentException("Invalid billionaire Id:" + id));
    }
    public void save(Billionaire billionaire)
    {
         billionaireRepository.save(billionaire);
    }

    public void delete(Billionaire billionaire) {
        billionaireRepository.delete(billionaire);
    }
}
