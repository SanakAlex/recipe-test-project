package dev.sanak.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping(path = {"", "/", "/index"})
  public String getIndexPage() {
    return "index";
  }

}
