package com.mobdeve.s17.dizon.palmares.alintana.helpers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Toast
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
        matchAdapter.removeMatchCard(position, direction)
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
    }
}