package mariavv.fitnesspal.domain

import com.google.gson.annotations.SerializedName

class Dish2(var date: String, var meal: String, @field:SerializedName("food_id")
var foodId: Int, var weight: Int)
