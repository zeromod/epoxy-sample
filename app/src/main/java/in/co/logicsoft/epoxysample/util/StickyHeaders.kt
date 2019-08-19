package `in`.co.logicsoft.epoxysample.util

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController

class StickyHeaders constructor(
    private val epoxyController: EpoxyController,
    private val headerClass: Class<*>
) : RecyclerView.ItemDecoration() {
    private var stickyHeaderHeight: Int = 0

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) return

        val headerPosition = getHeaderPositionForItem(topChildPosition)
        val currentHeader = getHeaderViewForItem(headerPosition, parent)
        fixLayoutSize(parent, currentHeader)
        val contactPoint = currentHeader.bottom
        val childInContact = getChildInContact(parent, contactPoint, headerPosition)

        childInContact?.let {
            if (isHeader(parent.getChildAdapterPosition(it))) {
                moveHeader(c, currentHeader, childInContact)
                return
            }
        }
        drawHeader(c, currentHeader)
    }

    private fun getHeaderPositionForItem(position: Int): Int {
        var headerPosition = 0
        var itemPosition = position
        do {
            if (isHeader(itemPosition)) {
                headerPosition = itemPosition
                break
            }
            itemPosition -= 1
        } while (itemPosition >= 0)
        return headerPosition
    }


    private fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View {
        val viewHolder = epoxyController.adapter.onCreateViewHolder(
            parent,
            epoxyController.adapter.getItemViewType(headerPosition)
        )
        epoxyController.adapter.onBindViewHolder(viewHolder, headerPosition)
        return viewHolder.itemView
    }

    private fun fixLayoutSize(parent: ViewGroup, view: View) {
        // Specs for parent (RecyclerView)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        // Specs for children (headers)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidthSpec, childHeightSpec)
        stickyHeaderHeight = view.measuredHeight
        view.layout(0, 0, view.measuredWidth, stickyHeaderHeight)
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int, currentHeaderPos: Int): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            var heightTolerance = 0
            val child = parent.getChildAt(i)
            //measure height tolerance with child if child is another header
            if (currentHeaderPos != i) {
                val isChildHeader = isHeader(parent.getChildAdapterPosition(child))
                if (isChildHeader) {
                    heightTolerance = stickyHeaderHeight - child.height
                }
            }
            //add heightTolerance if child top be in display area
            val childBottomPosition: Int
            childBottomPosition = if (child.top > 0) {
                child.bottom + heightTolerance
            } else {
                child.bottom
            }
            if (childBottomPosition > contactPoint) {
                if (child.top <= contactPoint) {
                    // This child overlaps the contactPoint
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    private fun isHeader(itemPosition: Int): Boolean {
        if (itemPosition != RecyclerView.NO_POSITION) {
            val model = epoxyController.adapter.getModelAtPosition(itemPosition)
            return model.javaClass == headerClass
        }
        return false
    }

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View) {
        c.save()
        c.translate(0f, (nextHeader.top - currentHeader.height).toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun drawHeader(c: Canvas, header: View) {
        c.save()
        c.translate(0f, 0f)
        header.draw(c)
        c.restore()
    }
}