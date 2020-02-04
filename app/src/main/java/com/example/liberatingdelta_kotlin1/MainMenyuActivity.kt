package com.example.liberatingdelta_kotlin1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.databinding.DataBindingUtil
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.*
import com.example.liberatingdelta_kotlin1.actFragments.MainMenyuFragment
import com.example.liberatingdelta_kotlin1.databinding.ActivityMainmenyuBinding

class MainMenyuActivity : AppCompatActivity(), UpButtonFragment.upButtonListener, MMCFragment.mmcFragmentListener {

    var pl : Int = 0
    var regionFragments = arrayOfNulls<Fragment?>(17) //list of all my region fragments, per the PL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        var binding : ActivityMainmenyuBinding = DataBindingUtil.setContentView(this, R.layout.activity_mainmenyu)

        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        //NavigationUI.setupActionBarWithNavController(this,navController)
    }


    override fun upPressed() {

        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        navController.navigateUp()


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        return navController.navigateUp()
    }

}
