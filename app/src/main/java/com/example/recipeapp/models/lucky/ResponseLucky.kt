package com.example.recipeapp.models.lucky

import com.example.recipeapp.models.detail.ResponseDetail
import com.google.gson.annotations.SerializedName

data class ResponseLucky(
    @SerializedName("recipes")
    val recipes: List<Recipe>?
) {
    data class Recipe(
        @SerializedName("aggregateLikes")
        val aggregateLikes: Int?, // 15
        @SerializedName("analyzedInstructions")
        val analyzedInstructions: List<ResponseDetail.AnalyzedInstruction>?,
        @SerializedName("cheap")
        val cheap: Boolean?, // false
        @SerializedName("cookingMinutes")
        val cookingMinutes: Int?, // -1
        @SerializedName("creditsText")
        val creditsText: String?, // Foodista.com – The Cooking Encyclopedia Everyone Can Edit
        @SerializedName("cuisines")
        val cuisines: List<Any?>?,
        @SerializedName("dairyFree")
        val dairyFree: Boolean?, // true
        @SerializedName("diets")
        val diets: List<String>?,
        @SerializedName("dishTypes")
        val dishTypes: List<String?>?,
        @SerializedName("extendedIngredients")
        val extendedIngredients: List<ResponseDetail.ExtendedIngredient>?,
        @SerializedName("gaps")
        val gaps: String?, // no
        @SerializedName("glutenFree")
        val glutenFree: Boolean?, // false
        @SerializedName("healthScore")
        val healthScore: Int?, // 3
        @SerializedName("id")
        val id: Int?, // 640461
        @SerializedName("image")
        val image: String?, // https://spoonacular.com/recipeImages/640461-556x370.jpg
        @SerializedName("imageType")
        val imageType: String?, // jpg
        @SerializedName("instructions")
        val instructions: String?, // In a saute pan heat 1 teaspoon oil, add chopped onion, red pepper and celery and cook until tender, about 2-3 minutes.Remove pan from heat and add garlic, crawfish meat, and 1 teaspoon Creole spice (or to taste). Transfer to a mixing bowl and set aside to cool.Mix in egg and enough bread crumbs for mixture to bind; adjust seasonings to taste with salt, pepper and Creole spice.Form into 8 equal patties and flatten to 3/4-inch thick. Heat remaining oil in a saute pan and cook cakes on both sides until brown and crispy.Serve with a dollop of tartar sauce.
        @SerializedName("license")
        val license: String?, // CC BY 3.0
        @SerializedName("lowFodmap")
        val lowFodmap: Boolean?, // false
        @SerializedName("occasions")
        val occasions: List<Any?>?,
        @SerializedName("originalId")
        val originalId: Any?, // null
        @SerializedName("preparationMinutes")
        val preparationMinutes: Int?, // -1
        @SerializedName("pricePerServing")
        val pricePerServing: Double?, // 65.12
        @SerializedName("readyInMinutes")
        val readyInMinutes: Int?, // 45
        @SerializedName("servings")
        val servings: Int?, // 8
        @SerializedName("sourceName")
        val sourceName: String?, // Foodista
        @SerializedName("sourceUrl")
        val sourceUrl: String?, // https://www.foodista.com/recipe/MLDXBH7V/crawfish-cake-sliders
        @SerializedName("spoonacularSourceUrl")
        val spoonacularSourceUrl: String?, // https://spoonacular.com/crawfish-cake-sliders-640461
        @SerializedName("summary")
        val summary: String?, // Crawfish Cake Sliders might be a good recipe to expand your hor d'oeuvre recipe box. Watching your figure? This dairy free and pescatarian recipe has <b>188 calories</b>, <b>10g of protein</b>, and <b>7g of fat</b> per serving. This recipe serves 8. For <b>65 cents per serving</b>, this recipe <b>covers 8%</b> of your daily requirements of vitamins and minerals. From preparation to the plate, this recipe takes around <b>45 minutes</b>. This recipe from Foodista has 15 fans. Head to the store and pick up oil, slider buns, onion, and a few other things to make it today. With a spoonacular <b>score of 30%</b>, this dish is rather bad. Similar recipes include <a href="https://spoonacular.com/recipes/cajun-crawfish-sliders-404940">Cajun Crawfish Sliders</a>, <a href="https://spoonacular.com/recipes/crawfish-jalapeno-cheese-cornbread-topped-with-crawfish-etouffee-362029">Crawfish Jalapeno Cheese Cornbread topped with Crawfish Etouffee</a>, and <a href="https://spoonacular.com/recipes/salmon-cake-sliders-591419">Salmon Cake Sliders</a>.
        @SerializedName("sustainable")
        val sustainable: Boolean?, // false
        @SerializedName("title")
        val title: String?, // Crawfish Cake Sliders
        @SerializedName("vegan")
        val vegan: Boolean?, // false
        @SerializedName("vegetarian")
        val vegetarian: Boolean?, // false
        @SerializedName("veryHealthy")
        val veryHealthy: Boolean?, // false
        @SerializedName("veryPopular")
        val veryPopular: Boolean?, // false
        @SerializedName("weightWatcherSmartPoints")
        val weightWatcherSmartPoints: Int? // 5
    )
}