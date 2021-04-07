package skripsi.kohonen.signaturerecognition

import android.content.Context
import androidx.core.view.ViewPager
import android.util.AttributeSet

/**
 * Created by fep on 29/03/2017.
 */
class CustomViewPager(context: Context?, attrs: AttributeSet?) : androidx.core.view.ViewPager(context, attrs) {
    private var enabled = true
    fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (enabled) super.onTouchEvent(event) else false
    }

    fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return if (enabled) super.onInterceptTouchEvent(event) else false
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun isPagingEnabled(): Boolean {
        return enabled
    }
}