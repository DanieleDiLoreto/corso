package com.sistemi.informativi.service;

import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface AcademyService {

    public Academy checkSaveOrUpdate(Academy academy);


    public Academy checkfindAcademyByCode(String code) throws CustomException;

    public Map<String,Boolean> checkremoveAcademy(String code);


    public List<Academy> checkFindAllAcademies() throws CustomException;
}
