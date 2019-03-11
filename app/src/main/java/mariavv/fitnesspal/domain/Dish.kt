package mariavv.fitnesspal.domain

import com.google.gson.annotations.SerializedName
import java.util.*

class Dish(var date: Date, var meal: String, @field:SerializedName("food_id")
var foodId: Int, var weight: Int)
