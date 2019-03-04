package mariavv.fitnesspal.other

import mariavv.fitnesspal.App

object Const {

    //нужен
    val LOG_TAG = App::class.java.getSimpleName()

    internal val DATE_PATTERN_DEFOULT = "dd MMMM yyyy"

    object Screen {
        val JOURNAL = "journal"
        val HANDBOOK = "hand_book"
        val ADD_DISH = "add_dish"
        val ADD_FOOD = "add_food"
    }

    object BundleArg {
        val ARG_DATE = "date"
    }
}
