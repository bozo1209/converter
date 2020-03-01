package com.example.converter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import kotlinx.android.synthetic.main.settings_dialog.*


private const val TAG = "SettingsDialog"

const val UNITS_FROM_TO_CONVERT = "UnitsFromToConvert"

class SettingsDialog: AppCompatDialogFragment() {

    private var defaultUnitToFromConvert = 0

    lateinit var listener: PassUnitsFromToConvert

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setStyle(AppCompatDialogFragment.STYLE_NORMAL, R.style.SettingsDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView called")
        val inflate = inflater.inflate(R.layout.settings_dialog, container, false)
        inflate.setBackgroundResource(R.color.primary_light)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        dialog?.setTitle(R.string.action_settings)

        ok_button.setOnClickListener {
            saveValues()
            dismiss()
        }

        cancel_button.setOnClickListener {
            dismiss()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        readValues()
        setdia_units_to_convert.setSelection(defaultUnitToFromConvert)
    }

    private fun readValues(){
        with(getDefaultSharedPreferences(context)){
            defaultUnitToFromConvert = getInt(UNITS_FROM_TO_CONVERT, defaultUnitToFromConvert)
        }

    }

    private fun saveValues(){
        val newUnitFromToConvert = setdia_units_to_convert.selectedItemPosition
        val newUnitsFromToConvert = setdia_units_to_convert.selectedItem.toString()

        with(getDefaultSharedPreferences(context).edit()){
            if (newUnitFromToConvert != defaultUnitToFromConvert) {
                putInt(UNITS_FROM_TO_CONVERT, newUnitFromToConvert)
            }
            apply()
        }

        Log.d(TAG, "*************************")
        Log.d(TAG, "$newUnitFromToConvert")
        listener.passUnitsFromToConvert(newUnitsFromToConvert, newUnitFromToConvert)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as PassUnitsFromToConvert
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement PassUnitsFromToConvert")
        }
    }

    interface PassUnitsFromToConvert{
        fun passUnitsFromToConvert(defaultUnitToFromConvert: String, defaultIntValueUnit: Int)
    }

}