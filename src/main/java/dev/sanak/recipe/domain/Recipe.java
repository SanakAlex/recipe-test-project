package dev.sanak.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  private String description;
  private Integer preparationTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;

  @Lob
  private String directions;

  @Lob
  private byte[] image;

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "recipe_category",
      joinColumns = @JoinColumn(name = "recipe_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  public Recipe addIngredient(Ingredient ingredient) {
    if (ingredient != null) {
      ingredient.setRecipe(this);
      this.ingredients.add(ingredient);
    }
    return this;
  }
}
