package com.example.myapplication.mvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMusicBinding

import com.example.myapplication.mvvm.dummy.DummyContent.DummyItem
import com.example.myapplication.mvvm.model.result.QueryMergeResp
import com.example.myapplication.mvvm.model.song.Song

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MusicFragment.OnListFragmentInteractionListener] interface.
 */
class MusicFragment : Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private var binding:FragmentMusicBinding? = null

    private var list: MutableList<Song> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //DataBinding 加载布局文件，然后给布局文件中的变量赋值，可以使各种info 或者 ViewModel
        binding = DataBindingUtil.inflate<FragmentMusicBinding>(inflater,R.layout.fragment_music,container,false)
        binding?.viewModel = ViewModelProviders.of(this).get(ViewModelMusicFragment::class.java)       //给 viewModel 赋值
        var recyclerview = binding?.recycler

        if (recyclerview is RecyclerView) {
            with(recyclerview) {
                layoutManager =  LinearLayoutManager(context)
                adapter = MyItemRecyclerViewAdapter(list,null)
            }
        }

        binding?.searchBt?.setOnClickListener {
            Log.e("click","bt click")
            binding?.viewModel?.getSongList(binding?.searchWord?.text.toString()!!)

        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel?.list?.observe(this,
            Observer<QueryMergeResp> { t ->
                if(t!=null){
                    list.clear()
                    list.addAll(t.result.song_info.song_list)
                    binding?.recycler?.adapter?.notifyDataSetChanged()
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MusicFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
