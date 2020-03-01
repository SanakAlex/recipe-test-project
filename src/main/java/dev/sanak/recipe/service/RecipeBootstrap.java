package dev.sanak.recipe.service;

import dev.sanak.recipe.domain.Category;
import dev.sanak.recipe.domain.Difficulty;
import dev.sanak.recipe.domain.Ingredient;
import dev.sanak.recipe.domain.Recipe;
import dev.sanak.recipe.domain.UnitOfMeasure;
import dev.sanak.recipe.repositories.CategoryRepository;
import dev.sanak.recipe.repositories.RecipeRepository;
import dev.sanak.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
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
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("Preparing recipe Data to be saved");
    List<Recipe> recipes = prepareListOfRecipes();
    recipeRepository.saveAll(recipes);
    log.info(recipes.size() + " recipes were saved");
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
    burgerRecipe.getCategories().add(american);
    burgerRecipe.setCookTime(2);
    burgerRecipe.setDifficulty(Difficulty.SEMI_HARD);
    burgerRecipe.setDescription("Burger");
    burgerRecipe.setServings(1);
    burgerRecipe.setPreparationTime(20);
    burgerRecipe.setSource("web");
    burgerRecipe
        .addIngredient(new Ingredient("Meet", BigDecimal.valueOf(1L), slice))
        .addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(5L), ripe))
        .addIngredient(new Ingredient("Cheese", BigDecimal.valueOf(1L), ounce))
        .addIngredient(new Ingredient("Souse", BigDecimal.valueOf(2L), tablespoon));

    Recipe saladRecipe = new Recipe();
    saladRecipe.getCategories().add(italian);
    saladRecipe.setCookTime(10);
    saladRecipe.setDifficulty(Difficulty.EASY);
    saladRecipe.setDescription("Salat");
    saladRecipe.setServings(5);
    saladRecipe.setPreparationTime(40);
    saladRecipe.setSource("magazine");
    saladRecipe
        .addIngredient(new Ingredient("Cucumber", BigDecimal.valueOf(10L), slice))
        .addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(10L), slice))
        .addIngredient(new Ingredient("Cheese", BigDecimal.valueOf(1L), ounce))
        .addIngredient(new Ingredient("Juice", BigDecimal.valueOf(1L), cup))
        .addIngredient(new Ingredient("Salt", BigDecimal.valueOf(1L), pinch))
        .addIngredient(new Ingredient("Souse", BigDecimal.valueOf(2L), teaspoon));

    List<Recipe> recipes = new ArrayList<>();
    recipes.add(burgerRecipe);
    recipes.add(saladRecipe);
    return recipes;
  }
}
