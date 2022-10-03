/*
Name:Qingqing Wu
ID: 991500423
Date: 2021-02-27
This project is an app for simply order pizza with different size and various of toppings
1) Pizza price will be updated automatically upon option(s) selecting / un-selecting;
2) Delivery details should be collected only when delivery required.
   Pressing OK should process a delivery info with Toast message, and clear all form fields.
3) App will be persistent upon device rotations (layout and data).
 */

package project.stn991500423.org

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
private const val SIGN = "$"
private const val KEY_RESULT = "totalPrice"
private var res = "totalPrice"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // price & total Price
        var totalPrice = 0.0
        var toppingPrice = 0.0
        val textPrice = findViewById<TextView>(R.id.ptPrice)

        // save instance state
        if(savedInstanceState != null) {
            res = savedInstanceState.getString(KEY_RESULT).toString()
            textPrice.text = res
            res = ""
        }

        // Process Radio Group， Radio button, and text view
        val radioSizeGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val rbSmall = findViewById<RadioButton>(R.id.rbSmall)
        val rbMedium = findViewById<RadioButton>(R.id.rbMedium)
        val rbLarge = findViewById<RadioButton>(R.id.rbLarge)

        // Calculate total price upon size selection
        radioSizeGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbSmall){
                totalPrice = toppingPrice
                totalPrice += 9.0
                res = totalPrice.toString()+SIGN
                textPrice.text = res
                res = ""
            }
            if(checkedId == R.id.rbMedium){
                totalPrice = toppingPrice
                totalPrice += 10.0
                res = totalPrice.toString()+SIGN
                textPrice.text = res
                res = ""
            }
            if(checkedId == R.id.rbLarge){
                totalPrice = toppingPrice
                totalPrice += 11.0
                res = totalPrice.toString()+SIGN
                textPrice.text = res
                res = ""
            }
        }

        // access checkbox element by ID
        val checkboxMeat = findViewById<CheckBox>(R.id.cbMeat)
        val checkboxCheese = findViewById<CheckBox>(R.id.cbCheese)
        val checkboxVeggie = findViewById<CheckBox>(R.id.cbVeggie)

        // Calculate total price up toppings selection
        checkboxMeat.setOnCheckedChangeListener { compoundButton, b ->
            // Dont allow to select topping if user has not choose a size yet
            if (radioSizeGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select your size!", Toast.LENGTH_SHORT).show()
                checkboxMeat.setChecked(false)
            }else {
                if (checkboxMeat.isChecked) {
                    totalPrice += 2.0
                    toppingPrice += 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                } else {
                    totalPrice -= 2.0
                    toppingPrice -= 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                }
            }
        }
        checkboxCheese.setOnCheckedChangeListener { compoundButton, b ->
            // Dont allow to select topping if user has not choose a size yet
            if (radioSizeGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select your size!", Toast.LENGTH_SHORT).show()
                checkboxCheese.setChecked(false)
            }else {
                if (checkboxCheese.isChecked) {
                    totalPrice += 2.0
                    toppingPrice += 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                } else {
                    totalPrice -= 2.0
                    toppingPrice -= 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                }
            }
        }
        checkboxVeggie.setOnCheckedChangeListener{ compoundButton, b ->
            // Dont allow to select topping if user has not choose a size yet
            if (radioSizeGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select your size!", Toast.LENGTH_SHORT).show()
                checkboxVeggie.setChecked(false)
            }else {
                if (checkboxVeggie.isChecked) {
                    totalPrice += 2.0
                    toppingPrice += 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                } else {
                    totalPrice -= 2.0
                    toppingPrice -= 2.0
                    res = totalPrice.toString()+SIGN
                    textPrice.text = res
                    res = ""
                }
            }
        }


        // Process SwitchButton
        // access SwitchButton element by ID
        val swDelivery = findViewById<Switch>(R.id.swDelivery)

        val tvDelivery = findViewById<TextView>(R.id.tvDelivery)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val editAddress = findViewById<EditText>(R.id.editAddress)
        val btOk = findViewById<TextView>(R.id.btOk)
        val btSubmit = findViewById<TextView>(R.id.btSubmit)

        // Delivery information gather, if user has switched on delivery option,
        // Delivery info will become visible, vice versa
        swDelivery.setOnCheckedChangeListener{ buttonView, onSwitch ->
            if (onSwitch){
                tvDelivery.visibility = View.VISIBLE
                tvAddress.visibility = View.VISIBLE
                editAddress.visibility = View.VISIBLE
                btOk.visibility = View.VISIBLE
                btSubmit.visibility = View.INVISIBLE
            }else {
                tvDelivery.visibility = View.INVISIBLE
                tvAddress.visibility = View.INVISIBLE
                editAddress.visibility = View.INVISIBLE
                btOk.visibility = View.INVISIBLE
                btSubmit.visibility = View.VISIBLE
            }
        }

        // Button action with delivery details, after button is clicked, information will be pop-up as a toast message, and all value will be clear out
        btOk.setOnClickListener{
            if (!editAddress.text.isNullOrBlank() || !editAddress.text.isNullOrEmpty()) {
                Toast.makeText(this, "You delivery address is " + editAddress.text.toString() + " and your total price is " + totalPrice + SIGN, Toast.LENGTH_SHORT).show()
                checkboxCheese.setChecked(false)
                checkboxMeat.setChecked(false)
                checkboxVeggie.setChecked(false)
                radioSizeGroup.clearCheck()
                textPrice.text = ""
                swDelivery.setChecked(false)
                btOk.clearFocus()
                editAddress.text = null
            }else {
                Toast.makeText(this, "Please enter your delivery information!", Toast.LENGTH_SHORT).show()
                btOk.clearFocus()
            }
        }

        // Button action without delivery details, after button is clicked, information will be pop-up as a toast message, and all value will be clear out
        btSubmit.setOnClickListener{
            Toast.makeText(this, "Your total price is "+totalPrice+SIGN, Toast.LENGTH_SHORT).show()
            checkboxCheese.setChecked(false)
            checkboxMeat.setChecked(false)
            checkboxVeggie.setChecked(false)
            radioSizeGroup.clearCheck()
            textPrice.text = ""
            swDelivery.setChecked(false)
            btSubmit.clearFocus()
        }

    }

    // Override func that process instance state saving up rotation
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState() was called")
        savedInstanceState.putString(KEY_RESULT, res)

    }
}