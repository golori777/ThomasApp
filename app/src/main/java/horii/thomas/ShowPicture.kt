package horii.thomas

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_show_picture.*
import java.util.*

class ShowPicture : AppCompatActivity() {

    var animation: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_picture)

        picture.setImageResource(R.drawable.thomas)

        picture.setOnClickListener {
            Log.v("a", "clicked")

            animationReset(picture)

            var pictureArray = resources.obtainTypedArray(R.array.pictureArrays)

            val random = Random()
            val n = random.nextInt(pictureArray.length())
            var drawable = pictureArray.getDrawable(n)

            Log.v("NonResourceString:", pictureArray.getResourceId(n, 0).toString())


            picture.setImageDrawable(drawable)

            if (n == 5 || n == 6) {
                animation = animateRotation(picture)
            } else if (n == 7 || n == 8 || n == 9)
                animation = animateZoom(picture)
        }

        back.setOnClickListener {
            finish()
        }
    }

    fun animationReset(img: ImageView) {
        ObjectAnimator.ofFloat(img, "alpha", 1.0f).start()
        ObjectAnimator.ofFloat(img, "rotation", 0.0f).start()
        ObjectAnimator.ofFloat(img, "rotation", 0.0f).start()
    }

    fun animateRotation(img: ImageView): ObjectAnimator {
        var objectAnimator = ObjectAnimator.ofFloat(img, "rotation", 360f)
        objectAnimator.duration = 2000
        objectAnimator.repeatCount = 0
        objectAnimator.start()
        return objectAnimator
    }

    fun animateZoom(img: ImageView): ObjectAnimator {
        var objectAnimator = ObjectAnimator.ofFloat(img, "alpha", 0.0f, 1.0f)
        objectAnimator.duration = 2000
        objectAnimator.repeatCount = 0
        objectAnimator.start()
        return objectAnimator
    }
}
