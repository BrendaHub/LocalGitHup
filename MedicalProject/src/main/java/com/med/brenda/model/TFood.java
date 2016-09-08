package com.med.brenda.model;

public class TFood {
    private Long id;

    private String food_name;

    private Float weight;

    private Float hot;

    private Float protein;

    private Float fat;

    private Float sugar;

    private Float dietaryfiber;

    public TFood(Long id, String food_name, Float weight, Float hot, Float protein, Float fat, Float sugar, Float dietaryfiber) {
        this.id = id;
        this.food_name = food_name;
        this.weight = weight;
        this.hot = hot;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.dietaryfiber = dietaryfiber;
    }

    public TFood() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name == null ? null : food_name.trim();
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHot() {
        return hot;
    }

    public void setHot(Float hot) {
        this.hot = hot;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Float getSugar() {
        return sugar;
    }

    public void setSugar(Float sugar) {
        this.sugar = sugar;
    }

    public Float getDietaryfiber() {
        return dietaryfiber;
    }

    public void setDietaryfiber(Float dietaryfiber) {
        this.dietaryfiber = dietaryfiber;
    }
}