package com.example.converter

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import kotlinx.android.synthetic.main.convert_main.*


private const val TAG = "MainActivity"
const val UNIT_TO_SAVE = "unitToSave"

class MainActivity : AppCompatActivity(), SettingsDialog.PassUnitsFromToConvert {

    private var defaultUnitToSave = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert_main)
        Log.d(TAG, "onCreate")

        defaultUnitToSave = getString(R.string.pls_select_unit)
        gettingSettings()

        convertMain_title.text = defaultUnitToSave

        val unitsToFromArray = resources.getStringArray(R.array.values_from_to_convert)

        convertMain_convert_button.setOnClickListener {
            convertingAndDisplayingResults(unitsToFromArray)
        }

        convertMain_clear_button.setOnClickListener {
            clearValues()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        Log.d(TAG, "onCreateOptionsMenu called")

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Log.d(TAG, "onOptionsItemSelected called")

        when(item.itemId){
            R.id.menu_settings -> {
                val dialog = SettingsDialog()
                dialog.show(supportFragmentManager, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clearValues(){
        convertMain_value_to_convert.text.clear()
        convertMain_converted_value.text = getString(R.string.empty_string)
    }

    override fun passUnitsFromToConvert(defaultUnitToFromConvert: String, defaultIntValueUnit: Int) {
        convertMain_title.text = when(defaultIntValueUnit){
            0 -> getString(R.string.pls_select_unit)
            else -> defaultUnitToFromConvert
        }
        clearValues()
    }

    override fun onStop() {
        super.onStop()

        val unitToSave = convertMain_title.text.toString()

        with(getDefaultSharedPreferences(this).edit()){
            if(unitToSave != defaultUnitToSave){
                putString(UNIT_TO_SAVE, unitToSave)
            }
            apply()
        }
        Log.d(TAG, "onStop: unitToSave: $unitToSave,  defaultUnitToSave: $defaultUnitToSave")
    }

    private fun gettingSettings(){
        with(getDefaultSharedPreferences(this)){
            defaultUnitToSave = getString(UNIT_TO_SAVE, defaultUnitToSave)
        }

        Log.d(TAG, "defaultUnitToSave = $defaultUnitToSave")
    }

    private fun convertingAndDisplayingResults(unitsToFromArray: Array<String>){
        val valueToConvert = convertMain_value_to_convert.text.toString()
        val convert = Convert()
        val unitFromTo = convertMain_title.text.toString()
        val textMessage = if (valueToConvert.isNotEmpty()) getString(R.string.pls_select_unit) else getString(R.string.pls_enter_value)
        val toast = Toast.makeText(this, textMessage, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0,150)

        if (valueToConvert.isNotEmpty()) {
            val vToConvert = valueToConvert.toDouble()
            when (unitFromTo) {
                unitsToFromArray[0], getString(R.string.pls_select_unit) -> toast.show()
                unitsToFromArray[1] -> convertMain_converted_value.text = convert.cmToCal(vToConvert)
                unitsToFromArray[2] -> convertMain_converted_value.text = convert.calToCm(vToConvert)
                unitsToFromArray[3] -> convertMain_converted_value.text = convert.cmToFeet(vToConvert)
                unitsToFromArray[4] -> convertMain_converted_value.text = convert.feetToCm(vToConvert)
                unitsToFromArray[5] -> convertMain_converted_value.text = convert.cmToYard(vToConvert)
                unitsToFromArray[6] -> convertMain_converted_value.text = convert.yardToCm(vToConvert)

            }
        }else if(unitFromTo != getString(R.string.pls_select_unit)){
            toast.show()
        }
    }
}

