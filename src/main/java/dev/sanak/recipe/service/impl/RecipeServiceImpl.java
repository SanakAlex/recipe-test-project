package dev.sanak.recipe.service.impl;

import dev.sanak.recipe.domain.Recipe;
import dev.sanak.recipe.repositories.RecipeRepository;
import dev.sanak.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public List<Recipe> getAllRecipes() {
    log.info("finding all recipes");
    return recipeRepository.findAll();
  }
}
