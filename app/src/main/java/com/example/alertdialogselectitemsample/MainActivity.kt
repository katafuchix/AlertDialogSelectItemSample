package com.example.alertdialogselectitemsample

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button click listener
        button.setOnClickListener{
            // Show the single choice list items on an alert dialog
            showDialog()
        }
    }

    // Method to show an alert dialog with single choice list items
    private fun showDialog(){
        // Initialize an array of colors
        val array = arrayOf("RED","GREEN","YELLOW","BLACK","MAGENTA","PINK")

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        builder.setTitle("Choose a color.")

        /*
            **** reference source developer.android.com ***

            AlertDialog.Builder setItems (CharSequence[] items, DialogInterface.OnClickListener listener)
                Set a list of items to be displayed in the dialog as the content, you will be
                notified of the selected item via the supplied listener.

            Parameters
            items : CharSequence
            listener : DialogInterface.OnClickListener

            Returns
                AlertDialog.Builder This Builder object to allow for chaining of calls to set methods
        */

        // Set items form alert dialog
        builder.setItems(array,{_, which ->
            // Get the dialog selected item
            val selected = array[which]

            // Try to parse user selected color string
            try {
                // Change the layout background color using user selection
                root_layout.setBackgroundColor(Color.parseColor(selected))
                toast("$selected color selected.")
            }catch (e:IllegalArgumentException){
                // Catch the color string parse exception
                toast("$selected Color not supported.")
            }
        })

        // Create a new AlertDialog using builder object
        val dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}

// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}