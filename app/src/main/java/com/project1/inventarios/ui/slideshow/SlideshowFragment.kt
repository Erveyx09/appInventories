package com.project1.inventarios.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project1.inventarios.databinding.FragmentSlideshowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val slideshowViewModel: SlideshowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*slideshowViewModel.putCardInventories(9,1)
        slideshowViewModel.inventories.observe(viewLifecycleOwner){
                cardInventories ->
            if (cardInventories != null) {
                    Log.e("slideshowViewModel-->",cardInventories.toString())
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        slideshowViewModel.clear()
        slideshowViewModel.inventories.removeObservers(this)
    }
}