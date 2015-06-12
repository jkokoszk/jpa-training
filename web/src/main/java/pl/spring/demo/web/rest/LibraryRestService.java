package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.LibraryTo;

import java.util.List;

@Controller
public class LibraryRestService {

    @Autowired
    private LibraryService libraryService;

    // TODO JK :) Remove recurence from LibraryTo
    @RequestMapping(value = "/libraries", method = RequestMethod.GET, headers = "accept=application/json")
    @ResponseBody
    public List<LibraryTo> findAllLibraries() {
        return libraryService.findAllLibraries();
    }

}
