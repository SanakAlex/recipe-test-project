package dev.sanak.recipe.controllers;

import dev.sanak.recipe.service.RecipeService;
import dev.sanak.recipe.domain.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

  private final RecipeService recipeService;

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping(path = {"", "/", "/index"})
  public String getIndexPage(Model model) {
    List<Recipe> recipes = recipeService.getAllRecipes();

    model.addAttribute("recipes", recipes);

    return "index";
  }

}
