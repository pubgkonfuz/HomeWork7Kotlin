package com.example.homework7kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework7kotlin.databinding.FragmentCBinding

class CFragment : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding: FragmentCBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnAddItem.setOnClickListener {
            val image = etImage.text.toString().trim()
            val name = etName.text.toString().trim()
            val leaves = etLeaves.text.toString().trim()

            if (image.isNotEmpty() && name.isNotEmpty() && leaves.isNotEmpty()) {
                val arguments = Bundle()
                arguments.putString(AFragment.IMAGE_KEY, image)
                arguments.putString(AFragment.NAME_KEY, name)
                arguments.putString(AFragment.LEAVES_KEY, leaves)

                parentFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, AFragment()::class.java, arguments)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}