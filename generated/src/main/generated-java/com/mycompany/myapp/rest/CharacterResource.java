/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
package com.mycompany.myapp.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import com.mycompany.myapp.domain.Character;
import com.mycompany.myapp.dto.CharacterDTO;
import com.mycompany.myapp.dto.CharacterDTOService;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.dto.support.PageResponse;
import com.mycompany.myapp.repository.CharacterRepository;
import com.mycompany.myapp.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/characters")
public class CharacterResource {

    private final Logger log = LoggerFactory.getLogger(CharacterResource.class);

    @Inject
    private CharacterRepository characterRepository;
    @Inject
    private CharacterDTOService characterDTOService;

    /**
     * Create a new Character.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterDTO> create(@RequestBody CharacterDTO characterDTO) throws URISyntaxException {

        log.debug("Create CharacterDTO : {}", characterDTO);

        if (characterDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Character with existing ID").body(null);
        }

        CharacterDTO result = characterDTOService.save(characterDTO);

        return ResponseEntity.created(new URI("/api/characters/" + result.id)).body(result);
    }

    /**
    * Find by id Character.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterDTO> findById(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Find by id Character : {}", id);

        return Optional.ofNullable(characterDTOService.findOne(id)).map(characterDTO -> new ResponseEntity<>(characterDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update Character.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO characterDTO) throws URISyntaxException {

        log.debug("Update CharacterDTO : {}", characterDTO);

        if (!characterDTO.isIdSet()) {
            return create(characterDTO);
        }

        CharacterDTO result = characterDTOService.save(characterDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Character using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<CharacterDTO>> findAll(@RequestBody PageRequestByExample<CharacterDTO> prbe) throws URISyntaxException {
        PageResponse<CharacterDTO> pageResponse = characterDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CharacterDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<CharacterDTO> results = characterDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id Character.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Delete by id Character : {}", id);

        try {
            characterRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}