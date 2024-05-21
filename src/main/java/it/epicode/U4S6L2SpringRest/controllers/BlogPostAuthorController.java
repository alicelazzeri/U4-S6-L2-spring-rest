package it.epicode.U4S6L2SpringRest.controllers;

import it.epicode.U4S6L2SpringRest.services.BlogPostAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class BlogPostAuthorController {

    @Autowired
    private BlogPostAuthorService blogPostAuthorService;
}
