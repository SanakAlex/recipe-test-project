package dev.sanak.recipe.service;

import dev.sanak.recipe.domain.Category;
import dev.sanak.recipe.domain.Difficulty;
import dev.sanak.recipe.domain.Ingredient;
import dev.sanak.recipe.domain.Recipe;
import dev.sanak.recipe.domain.UnitOfMeasure;
import dev.sanak.recipe.repositories.CategoryRepository;
import dev.sanak.recipe.repositories.RecipeRepository;
import dev.sanak.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final RecipeRepository recipeRepository;

  public RecipeBootstrap(CategoryRepository categoryRepository,
      UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    recipeRepository.saveAll(prepareListOfRecipes());
  }

  private List<Recipe> prepareListOfRecipes() {
    Category american = categoryRepository.findByDescription("American")
        .orElseThrow(NoSuchElementException::new);
    Category italian = categoryRepository.findByDescription("Italian")
        .orElseThrow(NoSuchElementException::new);

    UnitOfMeasure slice = unitOfMeasureRepository.findByDescription("Slice")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure ounce = unitOfMeasureRepository.findByDescription("Ounce")
        .orElseThrow(NoSuchElementException::new);
    UnitOfMeasure ripe = unitOfMeasureRepository.findByDescription("Ripe")
        .orElseThrow(NoSuchElementException::new);

    Recipe burgerRecipe = new Recipe();
    burgerRecipe.setCategories(Collections.singleton(american));
    burgerRecipe.setCookTime(2);
    burgerRecipe.setDifficulty(Difficulty.SEMI_HARD);
    burgerRecipe.setDescription("Burger");
    burgerRecipe.setServings(1);
    burgerRecipe.setPreparationTime(20);
    burgerRecipe.setSource("web");
    burgerRecipe.addIngredient(new Ingredient("Meet", BigDecimal.valueOf(1L), slice));
    burgerRecipe.addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(5L), ripe));
    burgerRecipe.addIngredient(new Ingredient("Cheese", BigDecimal.valueOf(1L), ounce));
    burgerRecipe.addIngredient(new Ingredient("Souse", BigDecimal.valueOf(2L), tablespoon));

    Recipe salatRecipe = new Recipe();
    salatRecipe.setCategories(Collections.singleton(italian));
    salatRecipe.setCookTime(10);
    salatRecipe.setDifficulty(Difficulty.EASY);
    salatRecipe.setDescription("Salat");
    salatRecipe.setServings(5);
    salatRecipe.setPreparationTime(40);
    salatRecipe.setSource("magazine");
    salatRecipe.addIngredient(new Ingredient("Cucumber", BigDecimal.valueOf(10L), slice));
    salatRecipe.addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(10L), slice));
    salatRecipe.addIngredient(new Ingredient("Cheese", BigDecimal.valueOf(1L), ounce));
    salatRecipe.addIngredient(new Ingredient("Juice", BigDecimal.valueOf(1L), cup));
    salatRecipe.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(1L), pinch));
    salatRecipe.addIngredient(new Ingredient("Souse", BigDecimal.valueOf(2L), teaspoon));

    List<Recipe> recipes = new ArrayList<>();
    recipes.add(burgerRecipe);
    recipes.add(salatRecipe);
    return recipes;
  }
}
