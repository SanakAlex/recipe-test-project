package dev.sanak.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"recipe"})
@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  private String description;
  private BigDecimal amount;

  @ManyToOne
  private Recipe recipe;

  @OneToOne
  private UnitOfMeasure unitOfMeasure;

  public Ingredient() {
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
    this.description = description;
    this.amount = amount;
    this.unitOfMeasure = unitOfMeasure;
  }

}
