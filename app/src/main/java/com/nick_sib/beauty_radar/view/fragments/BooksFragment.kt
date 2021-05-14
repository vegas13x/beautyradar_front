package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view.utils.Util2

class BooksFragment : Fragment() {

//    private lateinit var binding: BooksFragmentBinding

    fun newInstance(): BooksFragment {
        return BooksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View? = inflater.inflate(R.layout.books_fragment, container, false)
        val rvBooks : RecyclerView = view!!.findViewById(R.id.clientRecycler)
        rvBooks.layoutManager = LinearLayoutManager(activity);
        val recyclerAdapter = ClientAdapter(Util2().getBooks())
        rvBooks.adapter = recyclerAdapter
        return view
    }
}