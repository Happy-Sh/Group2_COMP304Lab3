package com.group2_comp304lab3

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Exercise1 : AppCompatActivity() {
    private lateinit var lineThickness: Array<String>
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var colorTextView: TextView
    private lateinit var dropdown: Spinner
    private lateinit var radioGroup: RadioGroup
    private var colorValue = Color.LTGRAY

    private var beginX = 10
    private var beginY = 10
    private var endX = 0
    private var endY = 0

    private lateinit var paint: Paint
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise1)
        // Initialize dropdown spinner and set line thickness options
        dropdown = findViewById(R.id.spinner)
        lineThickness = resources.getStringArray(R.array.lineThickness)
        dropdown.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lineThickness)
        // Initialize paint object with default color and stroke width
        paint = Paint().apply {
            color = Color.MAGENTA
            strokeWidth = 0f // Set default stroke width to 0
        }
        // Initialize imageView, bitmap, and canvas for drawing
        imageView = findViewById<View>(R.id.ImageViewForDrawing) as ImageView
        @Suppress("DEPRECATION")
        bitmap = Bitmap.createBitmap(
            windowManager.defaultDisplay.height,
            windowManager.defaultDisplay.height,
            Bitmap.Config.ARGB_8888
        )
        canvas = Canvas(bitmap)
        canvas.drawColor(Color.LTGRAY)

        // Set the bitmap to the imageView and make it visible
        imageView.setImageBitmap(bitmap)
        imageView.visibility = View.VISIBLE
        // Initialize textView and colorTextView
        textView = findViewById<View>(R.id.yTextView) as TextView
        textView.text = "Y = 0"
        colorTextView = findViewById<View>(R.id.colorTextView) as TextView

        radioGroup = findViewById<View>(R.id.radioGrp) as RadioGroup

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Set colorValue based on the selected color in radioGroup
            colorValue = when (checkedId) {
                R.id.red -> {
                    colorTextView.setBackgroundColor(Color.RED)
                    Color.RED
                }

                R.id.yellow -> {
                    colorTextView.setBackgroundColor(Color.YELLOW)
                    Color.YELLOW
                }

                R.id.cyan -> {
                    colorTextView.setBackgroundColor(Color.CYAN)
                    Color.CYAN
                }

                else -> {
                    colorTextView.setBackgroundColor(Color.TRANSPARENT)
                    Color.LTGRAY
                }
            }
        }
        // Set focus and key listener to imageView for android keypad events
        imageView.isFocusable = true
        imageView.requestFocus()

        imageView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_UP -> {
                        btnUp(null)
                        return@OnKeyListener true
                    }

                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                        btnDown(null)
                        return@OnKeyListener true
                    }

                    KeyEvent.KEYCODE_DPAD_LEFT -> {
                        btnLeft(null)
                        return@OnKeyListener true
                    }

                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        btnRight(null)
                        return@OnKeyListener true
                    }
                }
            }
            false
        })
    }

    // Clear the canvas and reset variables to default values
    fun clearCanvas(v: View?) {
        canvas.drawColor(Color.LTGRAY)
        beginX = 10
        beginY = 10
        endX = 0
        endY = 0
        textView.text = "Y = 0"
        radioGroup.clearCheck()
        colorTextView.setBackgroundColor(Color.TRANSPARENT)
        imageView.invalidate()
    }

    fun drawLine() {
        paint.color = colorValue
        paint.strokeWidth = dropdown.selectedItem.toString().toInt().toFloat()
        textView.text = "Y = " + endY
        canvas.drawLine(
            beginX.toFloat(),
            beginY.toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        beginX = endX
        beginY = endY
        imageView.invalidate()
    }

    // Move the endpoint of the line down
    fun btnDown(v: View?) {
        if (isSelectionValid()) {
            endY += 20
            drawLine()
        } else {
            Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show()
        }
    }

    // Move the endpoint of the line up
    fun btnUp(v: View?) {
        if (isSelectionValid()) {
            endY -= 20
            drawLine()
        } else {
            Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show()
        }
    }

    // Move the endpoint of the line left
    fun btnLeft(v: View?) {
        if (isSelectionValid()) {
            endX -= 20
            drawLine()
        } else {
            Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show()
        }
    }

    // Move the endpoint of the line right
    fun btnRight(v: View?) {
        if (isSelectionValid()) {
            endX += 20
            drawLine()
        } else {
            Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show()
        }
    }

    // Check if a color is selected in the RadioGroup
    private fun isSelectionValid(): Boolean {
        return radioGroup.checkedRadioButtonId != -1
    }
}