package com.sistemi.informativi.controller;


import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;
import com.sistemi.informativi.service.AcademyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/*
l'annotation @RestController
fa si che la classe diventi un restful web service
provider in grado di ricevere file JSON e di
restituire file JSON
 */
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/rest/api/academies")
public class AcademyController {

    private AcademyService academyService;

    public AcademyController(AcademyService academyService){

        this.academyService = academyService;
    }


    /*
    Il consumer per consumare l'operazione dovrà fare la seguente chiamata:
    http://localhost:8080/rest/api/academies
     */

    @GetMapping
    public List<Academy> findAllAcademies() throws CustomException {

        System.out.println("AAA");

        /*
        Nel caso positivo il metodo del service ritorna una
        List<Academy> che essendo un oggetto java, viene automaticamente
        convertito dall'object Mappe
        in un file JSON
         */
        return academyService.checkFindAllAcademies();

    }

        /*
        Il consumer dovrà fare per consumare questa operzione,
        la seguente chiamata:
        http://localhost:8080/rest/api/academies/code/{code}
         */

        @GetMapping("/code/{code}")
                public Academy findAcademyByCode(@PathVariable String code) throws CustomException {
            return academyService.checkfindAcademyByCode(code);

        }

        /*
        Nel caso positivo il metodo
         */
        @PostMapping
    public Academy saveAcademy(@Valid @RequestBody Academy academy){

            return academyService.checkSaveOrUpdate(academy);

        }

        @PutMapping
    public Academy updateAcademy(@RequestBody Academy academy){

            return academyService.checkSaveOrUpdate(academy);


        }

        @DeleteMapping("/code/{code}")
        public Map<String,Boolean> removeAcademy(@PathVariable String code){
            return academyService.checkremoveAcademy(code);



        }
}
