package com.bingo.lottoservice.repository;

import com.bingo.lottoservice.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/db", produces = MediaType.APPLICATION_JSON_VALUE)
public class dbController {

    @Autowired
    private AppConfiguration appConfiguration;

    @RequestMapping(value = "/putDbMapping", method = RequestMethod.POST)
    public ResponseEntity<String> putDbMapping(@RequestBody String abc) {
//        curl -X PUT "localhost:9200/twitter/_mapping?pretty" -H 'Content-Type: application/json' -d'
//        {
//            "properties": {
//            "email": {
//                "type": "keyword"
//            }
//        }
//        }
        System.out.println(abc);
        return new ResponseEntity<>(abc, HttpStatus.OK);
    }

    @RequestMapping(value = "/putDbData", method = RequestMethod.POST)
    public ResponseEntity<String> putDbData(@RequestBody String abc) {
        System.out.println(abc);
        return new ResponseEntity<>(abc, HttpStatus.OK);
    }

}
