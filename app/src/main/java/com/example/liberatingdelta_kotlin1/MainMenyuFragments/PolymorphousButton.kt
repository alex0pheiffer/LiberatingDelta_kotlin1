package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

//see the invisible image button test for more information...
class PolymorphousButton : AppCompatImageView {
    constructor(context: Context?) : super(context) {
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean { // Process ACTION_UP and ACTION_DOWN events
//if (event.getAction()) {
        if (event.action == MotionEvent.ACTION_DOWN) { // For the ACTION_DOWN event, we want to check the color at the touch location
            val touchLocationColor: Int
            // Get the location of the touch event and cast to an Int
            val xTouchLocation = event.x.toInt()
            val yTouchLocation = event.y.toInt()
            touchLocationColor =
                try { // Get the pixel at the touch location, which means getting the
// pixel color.
                    val bitty = this.drawingCache
                    if (bitty == null) {
                        Log.d("UNKNOWN", ">>Bitmap is null<<")
                        return false
                    }
                    bitty.getPixel(xTouchLocation, yTouchLocation)
                } catch (e: IllegalArgumentException) { // The IllegalArgumentException error will get thrown only if the
// location of the touch event was outside the bounds of the view.
// And in that case, it obviously should be bypassed.
                    return false
                }
            // Return true and accept the touch event if the color of the pixel at the
// touch location was TRANSPARENT. Otherwise, don't process the event.
            return touchLocationColor != Color.TRANSPARENT
        }
        if (event.action == MotionEvent.ACTION_UP) { // Presumably, by this point, you have determined that the touch event was
// inside the view bounds and not TRANSPARENT. So you can process the click.
            performClick()
            return true
        }
        //}
        return false // Return false for other touch events
    }
}