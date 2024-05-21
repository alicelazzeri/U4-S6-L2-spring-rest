package it.epicode.U4S6L2SpringRest.controllers;

import it.epicode.U4S6L2SpringRest.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")

public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;
}
