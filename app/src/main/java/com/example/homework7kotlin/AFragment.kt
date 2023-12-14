package com.example.homework7kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework7kotlin.databinding.FragmentABinding

class AFragment : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding get() = _binding!!
    private val plantsListAdapter = PlantsListAdapter(this::onItemClick)

    private fun onItemClick(plants: Plants) {
        val args = Bundle().apply {
            putSerializable(MODEL, plants)
        }
        parentFragmentManager
            .beginTransaction().replace(R.id.fragment_container, BFragment::class.java, args)
            .addToBackStack("A Fragment")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        initRecycle()
        dataTransfer()
    }

    private fun setupListener() = with(binding) {
        btnAddItem.setOnClickListener {
            parentFragmentManager
                .beginTransaction().replace(R.id.fragment_container, CFragment())
                .addToBackStack("A Fragment")
                .commit()
        }
    }

    private fun initRecycle() {
        binding.rvFragmentPlant.adapter = plantsListAdapter
    }

    private fun dataTransfer() {
        arguments?.let {
            val image = it.getString(IMAGE_KEY)
            val name = it.getString(NAME_KEY)
            val leaves = it.getString(LEAVES_KEY)
            if (image != null && name != null && leaves != null) {
                val plants = Plants(image, name, leaves.toInt())
                SafeList.plantsList.add(plants)

                // distinct() удаляет вся повторяющиеся элементы
                val cleansList : MutableList<Plants> = SafeList.plantsList.distinct().toMutableList()
                SafeList.plantsList = cleansList
                plantsListAdapter.addItem(SafeList.plantsList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MODEL = "model"
        const val IMAGE_KEY = "image"
        const val NAME_KEY = "name"
        const val LEAVES_KEY = "leaves"
    }
}