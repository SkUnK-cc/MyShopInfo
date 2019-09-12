package com.example.myapplication.shopinfo.fragment.active

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.custom.PileView


import com.example.myapplication.shopinfo.fragment.active.FragmentActive.OnListFragmentInteractionListener
import com.example.myapplication.shopinfo.fragment.active.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ShopFragActiveRecyclerViewAdapter(
    private val mValues: List<DummyItem>,
    private val header: View?,
    private val footer: View?,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ShopFragActiveRecyclerViewAdapter.ViewHolder>() {

    companion object{
        val TYPE_HEADER = 0
        val TYPE_NORMAL = 1
        val TYPE_FOOTER = 2
    }

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(header==null && footer==null){
            return TYPE_NORMAL
        }
        if(position==0){
            return TYPE_HEADER
        }
        if(position==itemCount-1){
            return TYPE_FOOTER
        }
        return TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View?
        when(viewType) {
            TYPE_HEADER ->{
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_item_header, parent, false)
                var holder = ViewHolder(view)
                return holder
            }
            TYPE_FOOTER ->{
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_item_footer, parent, false)
                var holder = ViewHolder(view)
                return holder
            }
            else-> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_item, parent, false)
                var holder = ViewHolder(view)
                holder.pileview?.setAvertImages(4)
                return holder
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        if(footer==null && header==null){
            return mValues.size
        }else if(header==null && footer!=null){
            return mValues.size+1
        } else if(header!=null && footer==null){
            return mValues.size+1
        }else{
            return mValues.size+2
        }
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var pileview: PileView? = null

        constructor(view: View) : super(view) {
            if(view==header){
                return
            }
            if(view==footer){
                return
            }
            pileview = view.pileview
        }
    }

}
