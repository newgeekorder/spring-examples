package zipi.dao;

import org.apache.solr.client.solrj.beans.Field;

public class HowTo {
    public enum restrictedDiet {
        DiabeticDiet,
        GlutenFreeDiet,
        HalalDiet,
        HinduDiet,
        KosherDiet,
        LowCalorieDiet,
        LowFatDiet,
        LowLactoseDiet,
        LowSaltDiet,
        VeganDiet,
        VegetarianDiet
    };

    @Field
    String estimatedCost;
    @Field
    String performTime;
    @Field
    String prepTime;
    @Field
    String steps;
    @Field
    String supply;
    @Field
    String tool;
    @Field
    String totalTime;
    @Field
    String yield;
    @Field
    String suitableForDiet;

}
