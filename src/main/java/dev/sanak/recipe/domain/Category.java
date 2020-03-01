package dev.sanak.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"recipes"})
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  private String description;

  @ManyToMany(mappedBy = "categories")
  private Set<Recipe> recipes = new HashSet<>();
}
