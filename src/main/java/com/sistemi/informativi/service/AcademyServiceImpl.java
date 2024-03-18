package com.sistemi.informativi.service;


import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;
import com.sistemi.informativi.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademyServiceImpl implements AcademyService {

    @Value("${no.academies}")
    private String noAcademies;

    @Value("${no.academy}")
    private String noAcademy;

    private AcademyRepository academyRepository;

    public AcademyServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    /*
        all'interno di un applicazione Spring boot
        in cui si implementa un restful web service
        provider è pratica consolidata far restituire
        ai netodi dello strato Service degli oggetti java
        in maniera tale che il RestController che
        invocherà i metodi del Service avrà già a disposizione
        tali oggetti che verranno successiavamente convertiti
        dall'Object Mapper di Sprng boot in formato JSON
         */
    @Override
    public Academy checkSaveOrUpdate(Academy academy) {

        Academy savedOrUpdated = null;

        try {


            academyRepository.save(academy);
        } catch (IllegalArgumentException | OptimisticLockingFailureException ex) {

            ex.printStackTrace();
        }

        return savedOrUpdated;

    }

    @Override
    public List<Academy> checkFindAllAcademies() throws CustomException {

        List<Academy> academies = academyRepository.findAll();

        if (academies.isEmpty()) throw new CustomException(noAcademies);
        return academies;


    }

    @Override
    public Academy checkfindAcademyByCode(String code) throws CustomException {

        /*
        Il metodo findById viene fornito da Spring Data JPA
        e consente in generale di restituire un Oggetto Java che
        rappresenta il contenuto informativo relativo ad un record del
        database con una specifica chiave primaria passata in input al metodo

        findById a partire dalla versione 2.7 di Spring boot
        è un metodo funzionale: possiamo infatti invocare con l'operatore una funzione
        di nome orElseThrow alla quale possiamo passare in input un Eccezione

        Nel caso in cui l'operazione non vada in Eccezione il metodo findById restituisce un Oggetto,
        altrimenti viene catturata l'Eccezione che richiamiamo nella funzione orElseThrow
         */

        return academyRepository.findById(code).orElseThrow(() -> new CustomException(noAcademies));


    }

    @Override
    public Map<String, Boolean> checkremoveAcademy(String code) {

        Map<String, Boolean> removeMap = new HashMap<>();


        try {
            academyRepository.deleteById(code);
            removeMap.put("deletion", true);
        } catch (IllegalArgumentException ex) {

            removeMap.put("deletion", false);

            ex.printStackTrace();
        }
        return removeMap;


    }

}
