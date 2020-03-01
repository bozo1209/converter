package com.example.converter

import android.util.Log


private const val TAG = "Convert"

class Convert {

    fun cmToCal(valueToConvert: Double): String{
        Log.d(TAG, "cmToCal called")
        Log.d(TAG, "cmToCal value to convert = $valueToConvert")
        val convertedValue = valueToConvert / 2.54
        Log.d(TAG, "cmToCal converted value = $convertedValue")
        return convertedValue.toString()
    }

    fun calToCm(valueToConvert: Double): String{
        Log.d(TAG, "calToCm called")
        Log.d(TAG, "calToCm value to convert = $valueToConvert")
        val convertedValue = valueToConvert * 2.54
        Log.d(TAG, "calToCm converted value = $convertedValue")
        return convertedValue.toString()
    }

    fun cmToFeet(valueToConvert: Double): String{
        Log.d(TAG, "cmToFeet called")
        Log.d(TAG, "cmToFeet value to convert = $valueToConvert")
        val convertedValue = valueToConvert / 30.48
        Log.d(TAG, "cmToFeet converted value = $convertedValue")
        return convertedValue.toString()
    }

    fun feetToCm(valueToConvert: Double): String{
        Log.d(TAG, "feetToCm called")
        Log.d(TAG, "feetToCm value to convert = $valueToConvert")
        val convertedValue = valueToConvert * 30.48
        Log.d(TAG, "feetToCm converted value = $convertedValue")
        return convertedValue.toString()
    }

    fun cmToYard(valueToConvert: Double): String{
        Log.d(TAG, "cmToYard called")
        Log.d(TAG, "cmToYard value to convert = $valueToConvert")
        val convertedValue = valueToConvert / 91.44
        Log.d(TAG, "cmToYard converted value = $convertedValue")
        return convertedValue.toString()
    }

    fun yardToCm(valueToConvert: Double): String{
        Log.d(TAG, "yardToCm called")
        Log.d(TAG, "yardToCm value to convert = $valueToConvert")
        val convertedValue = valueToConvert * 91.44
        Log.d(TAG, "yardToCm converted value = $convertedValue")
        return convertedValue.toString()
    }

}