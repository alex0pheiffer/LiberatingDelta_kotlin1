package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import kotlinx.android.synthetic.main.fragment_mmc.view.*


private const val CHARACTER = "character"
private const val PlayerLevel = "pl"

class MMCFragment : Fragment(), updateAllPL,
    CharacterArrowFragment.onCharacterArrowFragmentInteraction {
    private var listener: mmcFragmentListener? = null

    var character: main_character? = null
    var previous_character: main_character? = null
    var pl = 0
    lateinit var this_pl: PL
    lateinit var frontcharacter: ImageView

    private var arrowUp: Fragment? = null
    private var arrowDown: Fragment? = null
    private var isDeployed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            pl = it.getInt(PlayerLevel)
            this_pl = PL_VendingMachine.getPL(pl)
            character = this_pl.getCharacter(it.getString(CHARACTER) ?: "") ?: this_pl.getCharacter(0)!!

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewwy = inflater.inflate(R.layout.fragment_mmc, container, false)
        Log.d("DEPLOYARROWS", "fragment onCreate: "+viewwy?.toString()+ " "+ parentFragment.toString() )

        frontcharacter = viewwy.MMCcharacterImage

        if (listener != null) {
            Log.d("DEBUG","listener != null")
            Log.d("DEBUG","character be "+listener!!.grabMMC())
            this_pl = listener!!.grabCUR_PL()
            character = this_pl.getCharacter(listener!!.grabMMC()) ?: this_pl.getCharacter(0)!!
            Log.d("DEBUG"," character = "+character!!.name)
            frontcharacter.setImageDrawable(activity!!.getDrawable(character!!.charImgMain!!))
        }
        else {
                //Log.d("DEBUG","listener is null")
            this_pl = PL_VendingMachine.getPL(0)
            character = this_pl.getCharacter(0)
        }
        if (parentFragment is deployArrowsInterface) {
            //our parent wants us deploy arrows
            Log.d("DEPLOYARROWS", "parent is deployArrowsInterface")
            if (!isDeployed) {
                if (arrowUp == null) arrowUp = CharacterArrowFragment.newInstance(true, (parentFragment as deployArrowsInterface).hasEmpty())
                if (arrowDown == null) arrowDown = CharacterArrowFragment.newInstance(false, (parentFragment as deployArrowsInterface).hasEmpty())
                deployArrowsMMC(arrowUp, arrowDown)
            }
        }
        else {
            if (isDeployed) {
                if (arrowUp != null && arrowDown != null) {
                    reignArrowsMMC(arrowUp, arrowDown)
                }
                arrowUp = null
                arrowDown = null
            }
        }

        return viewwy
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is mmcFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement mmcFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    //changes the char showing
    //isUp --> do we shift the characters up or down
    //hasEmpty --> can it return an "empty" slot character
    override fun characterArrowPressed(isUp: Boolean, hasEmpty: Boolean) {
        previous_character = character
        if (character == null) {
            character = this_pl.rotateCharacter("empty", isUp, hasEmpty)
        }
        else {
            character = this_pl.rotateCharacter(character!!.name, isUp, hasEmpty)
        }
        if (character != null) {
            Log.d("CHARARROW", "charArrowPressed; " + character!!.name+ " showing")
            //change character image
            frontcharacter.setImageDrawable(activity!!.getDrawable(character!!.charImgMain!!))
            //update the db current_mc
            updateDB_mmc()
        }
        else {
            Log.d("CHARARROW", "charArrowPressed; " + "null" + " showing")
            frontcharacter.setImageDrawable(null)
        }
    }

    fun setPreviousCharacter() {
        //todo change to character's image
        character = previous_character ?: character
        frontcharacter.setImageDrawable(activity!!.getDrawable(character!!.charImgMain!!))
    }

    fun getEmptyCharacter(): Boolean {
        return if (character == null) true
        else false
    }

    fun deployArrowsMMC(arrowUp: Fragment?, arrowDown: Fragment?)
    {
        Log.d("DEPLOYARROWS", "arrows deployed")
        val fm = childFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.mmc_upArrow, arrowUp!!)
        ft.add(R.id.mmc_downArrow, arrowDown!!)
        ft.addToBackStack(null)
        ft.commit()
        isDeployed = true
    }

    fun reignArrowsMMC(arrowUp: Fragment?, arrowDown: Fragment?): main_character? {
        if (character == null) { throw java.lang.RuntimeException("Cannot reign when character DNE") }
        else {
            Log.d("DEPLOYARROWS", "arrows reigned")
            val fm = childFragmentManager
            val ft = fm.beginTransaction()
            ft.remove(arrowUp!!)
            ft.remove(arrowDown!!)
            ft.addToBackStack(null)
            ft.commit()
            isDeployed = false
        }
        return character
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    fun updateDB_mmc() {
        Log.d("DEBUG","Update mmc to "+character?.name)
        listener?.SUBupdateDB_mmc(character ?: previous_character ?: this_pl.getCharacter(0)!!, parentFragment!!)
    }

    interface mmcFragmentListener {
        fun grabMMC(): String
        fun grabCUR_PL(): PL
        fun SUBupdateDB_mmc(mainCharacter: main_character, parentFragment: Fragment)
    }

    companion object {
        @JvmStatic
        fun newInstance(mmcName: String, pl: Int) =
            MMCFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER, mmcName)
                    putInt(PlayerLevel, pl)
                }
            }
    }
}
