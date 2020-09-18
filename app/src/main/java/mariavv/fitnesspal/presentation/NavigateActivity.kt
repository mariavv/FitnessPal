package mariavv.fitnesspal.presentation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric
import mariavv.fitnesspal.presentation.adddish.AddDishFragment
import mariavv.fitnesspal.presentation.addfood.AddFoodFragment
import mariavv.fitnesspal.presentation.handbook.HandbookFragment
import mariavv.fitnesspal.presentation.journal.JournalFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

class NavigateActivity : MvpAppCompatActivity(), NavigateView {

    @InjectPresenter
    lateinit var presenter: NavigatePresenter

    private var navigationMenu: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard -> {
                presenter.onNavigationJournalSelected()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                presenter.onNavigationHandbookSelected()
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
    }

    private val navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.container) {

        override fun createFragment(screenKey: String, data: Any?): Fragment {
            Log.d("test", screenKey)
            when (screenKey) {
                Const.Screen.JOURNAL -> return JournalFragment()
                Const.Screen.HANDBOOK -> return HandbookFragment()
                Const.Screen.ADD_DISH -> return AddDishFragment()
                Const.Screen.ADD_FOOD -> return AddFoodFragment()
                else -> throw RuntimeException("Unknown screen key!")
            }
        }

        override fun showSystemMessage(message: String) {
            Toast.makeText(this@NavigateActivity, message, Toast.LENGTH_SHORT).show()
        }

        override fun exit() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigationMenu = findViewById(R.id.navigation)
        navigationMenu?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager
                .addOnBackStackChangedListener { presenter.changeFragment(supportFragmentManager, R.id.container) }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun setNavigator() {
        App.getNavigator().setNavigator(navigator)
    }

    override fun removeNavigator() {
        App.getNavigator().removeNavigator()
    }

    override fun hideBottomMenu() {
        navigationMenu?.visibility = View.GONE
    }

    override fun showBottomMenu() {
        navigationMenu?.visibility = View.VISIBLE
    }

    override fun showHomeAsUp(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
        supportActionBar?.setDisplayShowHomeEnabled(enabled)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is FrmFabric.IFragment) {
            (fragment as FrmFabric.IFragment).onBackPressed()
        } else {
            super.onBackPressed()
        }

        if (supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
