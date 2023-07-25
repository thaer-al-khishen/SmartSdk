package com.example.smartsdk.wrappers

import android.content.res.Resources
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

data class ViewConstraintsWrapper(
    var topConstraint: TopConstraintWrapper? = null,
    var startConstraint: StartConstraintWrapper? = null,
    var endConstraint: EndConstraintWrapper? = null,
    var bottomConstraint: BottomConstraintWrapper? = null,
    var height: SpecialSizeModifiers? = null,
    var width: SpecialSizeModifiers? = null,
    var margins: Margins? = null,
)

data class Margins(var start: Float? = null, var top: Float? = null, var end: Float? = null, var bottom: Float? = null)

data class TopConstraintWrapper(val topConstraintModifier: TopConstraintModifiers, val referenceView: View)
data class EndConstraintWrapper(val endConstraintModifier: EndConstraintModifiers, val referenceView: View)
data class StartConstraintWrapper(val startConstraintModifier: StartConstraintModifiers, val referenceView: View)
data class BottomConstraintWrapper(val bottomConstraintModifier: BottomConstraintModifiers, val referenceView: View)

enum class StartConstraintModifiers {
    START_TO_START_OF, START_TO_END_OF
}

enum class EndConstraintModifiers {
    END_TO_START_OF, END_TO_END_OF
}

enum class TopConstraintModifiers {
    TOP_TO_TOP_OF, TOP_TO_BOTTOM_OF
}

enum class BottomConstraintModifiers {
    BOTTOM_TO_TOP_OF, BOTTOM_TO_BOTTOM_OF
}

sealed class SpecialSizeModifiers(val staticSize: Float? = null) {
    object MATCH_PARENT: SpecialSizeModifiers()
    object WRAP_CONTENT: SpecialSizeModifiers()
    data class StaticSize(val valueInDp: Float): SpecialSizeModifiers(valueInDp)
}

fun View.applyNewConstraints(viewWrapper: ViewConstraintsWrapper?) {
    viewWrapper?.let {
        val constraintSet = ConstraintSet()
        val parent = this.parent as? ConstraintLayout
        parent?.let {
            constraintSet.clone(it)

            // Clear existing constraints before applying new ones
            constraintSet.clear(this.id)

            viewWrapper.topConstraint?.let { constraintPair ->
                applyTopConstraint(this.id, constraintPair, constraintSet)
            }

            viewWrapper.startConstraint?.let { constraintPair ->
                applyStartConstraint(this.id, constraintPair, constraintSet)
            }

            viewWrapper.endConstraint?.let { constraintPair ->
                applyEndConstraint(this.id, constraintPair, constraintSet)
            }

            viewWrapper.bottomConstraint?.let { constraintPair ->
                applyBottomConstraint(this.id, constraintPair, constraintSet)
            }

            viewWrapper.margins?.let { margins ->
                margins.start?.let { startMargin ->
                    constraintSet.setMargin(this.id, ConstraintSet.START, dpToPx(startMargin))
                }
                margins.top?.let { topMargin ->
                    constraintSet.setMargin(this.id, ConstraintSet.TOP, dpToPx(topMargin))
                }
                margins.end?.let { endMargin ->
                    constraintSet.setMargin(this.id, ConstraintSet.END, dpToPx(endMargin))
                }
                margins.bottom?.let { bottomMargin ->
                    constraintSet.setMargin(this.id, ConstraintSet.BOTTOM, dpToPx(bottomMargin))
                }
                margins
            }

            when(viewWrapper.width) {
                is SpecialSizeModifiers.MATCH_PARENT -> constraintSet.constrainWidth(this.id, ConstraintSet.MATCH_CONSTRAINT)
                is SpecialSizeModifiers.WRAP_CONTENT -> constraintSet.constrainWidth(this.id, ConstraintSet.WRAP_CONTENT)
                is SpecialSizeModifiers.StaticSize -> constraintSet.constrainWidth(this.id, dpToPx((viewWrapper.width as SpecialSizeModifiers.StaticSize).valueInDp))
                else -> constraintSet.constrainWidth(this.id, ConstraintSet.WRAP_CONTENT)
            }

            when(viewWrapper.height) {
                is SpecialSizeModifiers.MATCH_PARENT -> constraintSet.constrainHeight(this.id, ConstraintSet.MATCH_CONSTRAINT)
                is SpecialSizeModifiers.WRAP_CONTENT -> constraintSet.constrainHeight(this.id, ConstraintSet.WRAP_CONTENT)
                is SpecialSizeModifiers.StaticSize -> constraintSet.constrainHeight(this.id, dpToPx((viewWrapper.height as SpecialSizeModifiers.StaticSize).valueInDp))
                else -> constraintSet.constrainHeight(this.id, ConstraintSet.WRAP_CONTENT)
            }

            constraintSet.applyTo(it)
        }
    }
}

private fun applyTopConstraint(viewId: Int, constraintPair: TopConstraintWrapper, constraintSet: ConstraintSet) {
    when (constraintPair.topConstraintModifier) {
        TopConstraintModifiers.TOP_TO_TOP_OF -> constraintSet.connect(viewId, ConstraintSet.TOP, constraintPair.referenceView.id, ConstraintSet.TOP)
        TopConstraintModifiers.TOP_TO_BOTTOM_OF -> constraintSet.connect(viewId, ConstraintSet.TOP, constraintPair.referenceView.id, ConstraintSet.BOTTOM)
    }
}

private fun applyStartConstraint(viewId: Int, constraintPair: StartConstraintWrapper, constraintSet: ConstraintSet) {
    when (constraintPair.startConstraintModifier) {
        StartConstraintModifiers.START_TO_START_OF -> constraintSet.connect(viewId, ConstraintSet.START, constraintPair.referenceView.id, ConstraintSet.START)
        StartConstraintModifiers.START_TO_END_OF -> constraintSet.connect(viewId, ConstraintSet.START, constraintPair.referenceView.id, ConstraintSet.END)
    }
}

private fun applyEndConstraint(viewId: Int, constraintPair: EndConstraintWrapper, constraintSet: ConstraintSet) {
    when (constraintPair.endConstraintModifier) {
        EndConstraintModifiers.END_TO_START_OF -> constraintSet.connect(viewId, ConstraintSet.END, constraintPair.referenceView.id, ConstraintSet.START)
        EndConstraintModifiers.END_TO_END_OF -> constraintSet.connect(viewId, ConstraintSet.END, constraintPair.referenceView.id, ConstraintSet.END)
    }
}

private fun applyBottomConstraint(viewId: Int, constraintPair: BottomConstraintWrapper, constraintSet: ConstraintSet) {
    when (constraintPair.bottomConstraintModifier) {
        BottomConstraintModifiers.BOTTOM_TO_TOP_OF -> constraintSet.connect(viewId, ConstraintSet.BOTTOM, constraintPair.referenceView.id, ConstraintSet.TOP)
        BottomConstraintModifiers.BOTTOM_TO_BOTTOM_OF -> constraintSet.connect(viewId, ConstraintSet.BOTTOM, constraintPair.referenceView.id, ConstraintSet.BOTTOM)
    }
}

// You need to convert dp to px because constrainWidth/Height methods take values in pixels.
// You can adjust this function according to your requirements
private fun dpToPx(dp: Float): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (dp * density).toInt()
}
