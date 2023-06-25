package com.group2_comp304lab3

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    //variables
    private var longDate: Boolean = false

    //function to display the date in long format
    private fun modifyDate()
    {
        if (longDate)
        {
            val simpleDateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy  HH:mm:ss", Locale.getDefault())
            val calendarDateInfo = Calendar.getInstance()

            val longDateFormatted = simpleDateFormat.format(calendarDateInfo.time)
            setText(longDateFormatted)
        }
    }

    fun formatLongDate(longDate: Boolean)
    {
        this.longDate = longDate
        modifyDate()
    }

}