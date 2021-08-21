package com.mobdeve.s17.dizon.palmares.alintana

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s17.dizon.palmares.alintana.adapter.MatchAdapter

class SwipeCallback : ItemTouchHelper.SimpleCallback {
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var background: ColorDrawable

    constructor(matchAdapter: MatchAdapter) : super(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        this.matchAdapter = matchAdapter;
        background = ColorDrawable(Color.LTGRAY);



    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var position: Int = viewHolder.adapterPosition
        matchAdapter.removeMatchCard(position)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20

        if (dX > 0) { //Swipe right
            background = ColorDrawable(Color.LTGRAY)
            background.setBounds(
                itemView.left,
                itemView.top,
                itemView.left + dX.toInt() + backgroundCornerOffset,
                itemView.bottom
            )
        } else if (dX < 0) { //Swipe Left
            background = ColorDrawable(Color.DKGRAY)
            background.setBounds(
                itemView.right + dX.toInt() - backgroundCornerOffset,
                itemView.top,
                itemView.right,
                itemView.bottom
            )
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0)
        }

        background.draw(c)

    }
}