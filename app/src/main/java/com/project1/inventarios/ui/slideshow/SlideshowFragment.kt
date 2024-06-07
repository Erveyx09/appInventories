package com.project1.inventarios.ui.slideshow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project1.inventarios.R
import com.project1.inventarios.databinding.FragmentSlideshowBinding
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.History
import com.project1.inventarios.ui.home.adapter.RecyclerAdapter
import com.project1.inventarios.ui.slideshow.adapter.HistoryListener
import com.project1.inventarios.ui.slideshow.adapter.HistoryRecyclerAdapter
import com.project1.inventarios.utils.DialogFragments
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment(),HistoryListener {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val slideshowViewModel: SlideshowViewModel by viewModels()

    lateinit var mRecyclerView : RecyclerView

    val mAdapter : HistoryRecyclerAdapter = HistoryRecyclerAdapter()
    private lateinit var searchText: EditText

    var historyMutable: MutableList<History>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpRecyclerView()
        return root
    }

    fun setUpRecyclerView(){
        mRecyclerView = binding.root.findViewById(R.id.historyList) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.adapter = mAdapter
        mAdapter.RecyclerAdapter(historyMutable, requireContext(),this)

        searchText = binding.root.findViewById(R.id.searchHistoryId) as EditText
        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                filterInfo(p0.toString())
            }
        })

    }

    fun filterInfo(text:String){
        slideshowViewModel.getAllHistories("%$text%")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slideshowViewModel.getAllHistories(null)

        slideshowViewModel.inventories.observe(viewLifecycleOwner){
                histories ->

            if (histories != null) {
                //mAdapter.RecyclerAdapter(cardInventories?.toMutableList(), requireContext(),this)
                mAdapter.setMutableInfo(histories?.toMutableList())
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        slideshowViewModel.clear()
        slideshowViewModel.inventories.removeObservers(this)
    }

    override fun onClick(id: Int?, representation: Int, quantity: Int, type: Int, position: Int) {
        TODO("Not yet implemented")
    }
}