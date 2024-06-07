package com.project1.inventarios.ui.gallery

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project1.inventarios.R
import com.project1.inventarios.databinding.FragmentGalleryBinding
import com.project1.inventarios.model.Inventory
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val galleryViewModel: GalleryViewModel by viewModels()
    private val calendar = Calendar.getInstance()

    private lateinit var noReferenceId: EditText
    private lateinit var ambientacionId: EditText
    private lateinit var nameId: EditText
    private lateinit var representationQualityId: EditText

    private lateinit var representationQualityNameId: EditText
    private lateinit var equiQualityId: EditText
    private lateinit var equiQualityNameId: EditText
    private lateinit var loteId: EditText
    private lateinit var equipmentId: EditText
    private lateinit var deliveryById: EditText

    var date:Date? = null


    private var idInventory: Int? = 0

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
        val bottomCalendarID = _binding!!.root.findViewById<TextView>(R.id.bottomCalendarID)

        noReferenceId = _binding!!.root.findViewById(R.id.noReferenceId)
        ambientacionId = _binding!!.root.findViewById(R.id.ambientacionId)
        nameId = _binding!!.root.findViewById(R.id.nameId)
        representationQualityId = _binding!!.root.findViewById(R.id.representationQualityId)

        representationQualityNameId = _binding!!.root.findViewById(R.id.representationQualityNameId)
        equiQualityId = _binding!!.root.findViewById(R.id.equiQualityId)
        equiQualityNameId = _binding!!.root.findViewById(R.id.equiQualityNameId)
        loteId = _binding!!.root.findViewById(R.id.loteId)
        equipmentId = _binding!!.root.findViewById(R.id.equipmentId)
        deliveryById = _binding!!.root.findViewById(R.id.deliveryById)

        bottomCalendarID.setOnClickListener {
            showDatePicker()
        }

        button.setOnClickListener {
            val noReferenceId = noReferenceId.text.toString()
            val ambientacionId = ambientacionId.text.toString()
            val nameId = nameId.text.toString()
            val representationQualityId = representationQualityId.text.toString()

            val representationQualityNameId = representationQualityNameId.text.toString()

            val equiQualityId = equiQualityId.text.toString()
            val equiQualityNameId = equiQualityNameId.text.toString()

            val loteId = loteId.text.toString()
            val equipmentId = equipmentId.text.toString()
            val deliveryById = deliveryById.text.toString()



            if (noReferenceId.isNotEmpty() &&
                ambientacionId.isNotEmpty() &&
                nameId.isNotEmpty() &&
                representationQualityId.isNotEmpty() &&
                representationQualityNameId.isNotEmpty() &&
                equiQualityId.isNotEmpty() &&
                equiQualityNameId.isNotEmpty() &&
                loteId.isNotEmpty() &&
                equipmentId.isNotEmpty() &&
                deliveryById.isNotEmpty()
            ) {
                if (idInventory != null) {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                    val current = LocalDateTime.now().format(formatter)
                    val inventory = Inventory(
                        idInventory,
                        noReferenceId,
                        ambientacionId,
                        nameId,
                        Math.ceil(representationQualityId.toDouble()/equiQualityId.toInt()).toInt(),
                        representationQualityNameId,
                        equiQualityId.toInt(),
                        equiQualityNameId,
                        representationQualityId.toInt(),
                        date,
                        loteId,
                        equipmentId,
                        deliveryById,
                        Timestamp.valueOf(current)
                    )
                    galleryViewModel.updatedInventory(inventory)
                } else {

                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                    val current = LocalDateTime.now().format(formatter)
                    val inventory = Inventory(
                        null,
                        noReferenceId,
                        ambientacionId,
                        nameId,
                        Math.ceil(representationQualityId.toDouble()/equiQualityId.toInt()).toInt(),
                        representationQualityNameId,
                        equiQualityId.toInt(),
                        equiQualityNameId,
                        representationQualityId.toInt(),
                        date,
                        loteId,
                        equipmentId,
                        deliveryById,
                        Timestamp.valueOf(current)
                    )
                    galleryViewModel.insertInventory(inventory)
                }
            } else {
                Toast.makeText(requireContext(), "Faltan datos por llenar", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idInventory = arguments?.getInt("id")

        if (idInventory != null) {

            galleryViewModel.inventoriesUpdate.observe(viewLifecycleOwner) { inventory ->
                if (inventory != null) {
                    noReferenceId.setText(inventory.noReference)
                    ambientacionId.setText(inventory.setting)
                    nameId.setText(inventory.name)
                    representationQualityId.setText(inventory.quantity)
                    representationQualityNameId.setText(inventory.representationName)
                    equiQualityId.setText(inventory.equivalences)
                    equiQualityNameId.setText(inventory.equivalencesName)
                    //expiresId.setDate()
                    loteId.setText(inventory.portion)
                    equipmentId.setText(inventory.equipment)
                    deliveryById.setText(inventory.deliveryBy)

                } else {
                    Log.e("catch", "sin informacion")
                }
            }

            galleryViewModel.getInventory(idInventory!!)
        }

        galleryViewModel.inventories.observe(viewLifecycleOwner) { inventories ->
            if (inventories != null) {
                if (inventories > 0) {
                    Log.e("galleryViewModel-->{}", inventories.toString())
                    /*editTextNumberSerial.editableText.clear()
                    editTextname.editableText.clear()
                    editTextQuantity.setText("0")
                    description.editableText.clear()*/
                    //Toast.makeText(requireContext(),"Dato creado",Toast.LENGTH_LONG).show()
                }
                val navController = findNavController()
                navController.navigateUp()
            } else {
                //Toast.makeText(requireContext(),"algo salio mal",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showDatePicker(){
        val dialog = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, year:Int, month:Int, day:Int ->
                date = Date.valueOf(year.toString()+"-"+month.toString()+"-"+day.toString())
                Log.e("showDatePicker catch",date.toString())
            },
            calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        galleryViewModel.inventories.removeObservers(this)
        galleryViewModel.clear()

    }
}