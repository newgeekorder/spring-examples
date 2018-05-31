package zipi.dao;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "Recipes")
public class Recipe {
    @Id
    @Field
    public  String id;

    @Field
    public String title;
    public String prepTime;
    public String totalTime;


    // from schema.org
    @Field
    public String cookTime ;
    @Field
    public String cookingMethod;
    @Field
    public String nutrition;
    @Field
    String recipeCategory;
    @Field
    String recipeCuisine;
    @Field
    String ecipeIngredient;
    @Field
    String recipeInstructions;
    @Field
    String recipeYield;
    @Field
    String suitableForDiet;

    HowTo howTo;
}
