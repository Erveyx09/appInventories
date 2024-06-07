package com.project1.inventarios.ui.home

import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project1.inventarios.R
import com.project1.inventarios.databinding.FragmentHomeBinding
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.History
import com.project1.inventarios.ui.home.adapter.InventoryListener
import com.project1.inventarios.ui.home.adapter.RecyclerAdapter
import com.project1.inventarios.utils.DialogFragments
import com.project1.inventarios.utils.DialogListener
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class HomeFragment : Fragment(), InventoryListener,DialogListener {

    private var _binding: FragmentHomeBinding? = null

    private val homeViewModel:HomeViewModel by viewModels()

    lateinit var mRecyclerView : RecyclerView
    lateinit var mDialogFragment: DialogFragments

    val mAdapter : RecyclerAdapter = RecyclerAdapter()
    private lateinit var searchText:EditText

    private val calendar = Calendar.getInstance()


    var inventoryMutable: MutableList<CardInventory>? = ArrayList()



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        setUpRecyclerView()
        return root
    }

    fun setUpRecyclerView(){
        mRecyclerView = binding.root.findViewById(R.id.inventoryList) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.adapter = mAdapter
        mAdapter.RecyclerAdapter(inventoryMutable, requireContext(),this)

        searchText = binding.root.findViewById(R.id.searchId) as EditText
        searchText.addTextChangedListener(object : TextWatcher{
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
        homeViewModel.getAllCardInventories("%$text%")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDialogFragment = DialogFragments()
        mDialogFragment.setListener(this)
        homeViewModel.getAllCardInventories(null)

        homeViewModel.inventories.observe(viewLifecycleOwner){
                cardInventories ->

            if (cardInventories != null) {
                //mAdapter.RecyclerAdapter(cardInventories?.toMutableList(), requireContext(),this)
                mAdapter.setMutableInfo(cardInventories?.toMutableList())
            }
        }

        homeViewModel.inventoriesUpdate.observe(viewLifecycleOwner){
                cardInventoriesUpdate ->
            if (cardInventoriesUpdate !=null) {
                if (cardInventoriesUpdate>0){
                    Log.e("inventoriesUpdate-->","todo bien")
                    Toast.makeText(requireContext(),"Dato actualidado",Toast.LENGTH_LONG).show()
                }else{
                    Log.e("inventoriesUpdate-->","salio algo mal")
                }
            }else{
                //Toast.makeText(requireContext(),"Algo salio mal",Toast.LENGTH_LONG).show()
                //Log.e("inventoriesUpdate-->","salio algo mal")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        searchText.setText("")
        homeViewModel.clear()
        homeViewModel.inventories.removeObservers(this)
        homeViewModel.inventoriesUpdate.removeObservers(this)

    }

    override fun onClick(cardInventory: CardInventory,note:String,type:Int,position:Int) {
        if (type==0){
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            val current = LocalDateTime.now().format(formatter)

            val StringDate = calendar.get(Calendar.YEAR).toString()+"-"+calendar.get(Calendar.MONTH).toString()+"-"+calendar.get(Calendar.DAY_OF_MONTH).toString()


            Log.e("MMMMMMMMMMMM=========>>",StringDate)
            val history:History = History(
                null,
                note,
                cardInventory.name,
                cardInventory.noReference,
                Date.valueOf(StringDate),
                Timestamp.valueOf(current)
            )

            homeViewModel.putCardInventories(cardInventory.id!!,cardInventory.representation,cardInventory.quantity,history)
        }else if (type==1){
            val bundle = Bundle()
            bundle.putInt("id", cardInventory.id!!)
            findNavController().navigate(R.id.action_nav_home_to_nav_gallery,bundle)
        }else{
            mDialogFragment.setIds(cardInventory.id!!,position)
            mDialogFragment.show(parentFragmentManager,"Alerta")
        }
    }

    override fun onClickYes(int: Int,position: Int) {
        homeViewModel.deleteCardInventories(int)
        mAdapter.setDeleteInfo(position)
    }
}