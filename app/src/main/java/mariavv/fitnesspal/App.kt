package mariavv.fitnesspal

import android.app.Application
import android.content.Context
import android.support.annotation.StringRes

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    val navigatorHolder: NavigatorHolder
        get() = cicerone.navigatorHolder

    val router: Router
        get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        context = this
        cicerone = Cicerone.create()
    }

    companion object {
        lateinit var context: Context
        private lateinit var cicerone: Cicerone<Router>

        fun getAppString(@StringRes stringRes: Int): String {
            return context.getString(stringRes)
        }

        fun getNavigator(): NavigatorHolder = cicerone.navigatorHolder

        fun getRouter(): Router = cicerone.router
    }
}
