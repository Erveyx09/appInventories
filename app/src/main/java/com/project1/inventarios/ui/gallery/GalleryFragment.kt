package com.project1.inventarios.ui.gallery

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.project1.inventarios.R
import com.project1.inventarios.databinding.FragmentGalleryBinding
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val galleryViewModel: GalleryViewModel by viewModels()
    private lateinit var editTextname:EditText
    private lateinit var editTextNumberSerial:EditText
    private lateinit var editTextQuantity:EditText
    private lateinit var description:EditText
    private var idInventory:Int? = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        init()
        return root
    }

    fun init() {

        val button = _binding!!.root.findViewById<Button>(R.id.buttonAccept)

        editTextname = _binding!!.root.findViewById(R.id.EditTextName)
        editTextNumberSerial = _binding!!.root.findViewById(R.id.EditTextNumberSerial)
        editTextQuantity = _binding!!.root.findViewById(R.id.EditTextQuantity)
        description = _binding!!.root.findViewById(R.id.description)


        button.setOnClickListener {
            val name = editTextname.text.toString()
            val numberSerial = editTextNumberSerial.text.toString()
            val quantity = editTextQuantity.text.toString()
            val desc = description.text.toString()

            if (name.isNotEmpty() && numberSerial.isNotEmpty() && desc.isNotEmpty()){
                if (idInventory!=null){
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    val current = LocalDateTime.now().format(formatter)
                    val inventory = Inventory(
                        idInventory,
                        name = name,
                        quantity.toInt(),
                        desc, "todos", serialNumber = numberSerial, current
                    )
                    galleryViewModel.insertInventory(inventory)
                }else{

                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    val current = LocalDateTime.now().format(formatter)
                    val inventory = Inventory(
                        null,
                        name = name,
                        quantity.toInt(),
                        desc, "todos", serialNumber = numberSerial, current
                    )
                    galleryViewModel.insertInventory(inventory)
                }
            }else{
                Toast.makeText(requireContext(),"Faltan datos por llenar",Toast.LENGTH_LONG).show()
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idInventory = arguments?.getInt("id")

        if (idInventory!=null){

            galleryViewModel.inventoriesUpdate.observe(viewLifecycleOwner) {
                    inventory ->
                if (inventory!=null){
                    editTextname.setText(inventory.name)
                    editTextNumberSerial.setText(inventory.serialNumber)
                    editTextQuantity.setText(inventory.quantity.toString())
                    description.setText(inventory.description)
                }else{
                    Log.e("catch","sin informacion")
                }
            }

            galleryViewModel.getInventory(idInventory!!)
        }

        galleryViewModel.inventories.observe(viewLifecycleOwner) { inventories ->
            if (inventories != null) {
                if (inventories>0){
                    Log.e("galleryViewModel-->{}", inventories.toString())
                    editTextNumberSerial.editableText.clear()
                    editTextname.editableText.clear()
                    editTextQuantity.setText("0")
                    description.editableText.clear()
                    //Toast.makeText(requireContext(),"Dato creado",Toast.LENGTH_LONG).show()
                }
                val navController = findNavController()
                navController.navigateUp()
            }else{
                //Toast.makeText(requireContext(),"algo salio mal",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        galleryViewModel.inventories.removeObservers(this)
        galleryViewModel.clear()
        //galleryViewModel.inventories.removeObservers(this)

    }
}