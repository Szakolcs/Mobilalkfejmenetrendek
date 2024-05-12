package com.example.menetrendek.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.menetrendek.R
import com.example.menetrendek.databinding.FragmentTicketsBinding
import com.google.android.material.slider.RangeSlider

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private lateinit var buyTickets: Button
    private lateinit var slider: RangeSlider
    private lateinit var numberOfTickets: TextView
    private lateinit var total: TextView
    private lateinit var edtBuy: TextView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buyTickets = view.findViewById(R.id.buy)
        slider = view.findViewById(R.id.slider)
        numberOfTickets = view.findViewById(R.id.tv_num_tickets)
        total = view.findViewById(R.id.sziaakos)
        edtBuy = view.findViewById(R.id.num_buy)
        edtBuy.text = slider.valueFrom.toInt().toString()
        var totalnum = slider.valueFrom.toInt() * 300
        total.text = "$totalnum Ft"
        slider.addOnChangeListener { slider, value, fromUser ->
            edtBuy.text = value.toInt().toString()
            totalnum = (value*300).toInt()
            total.text = "$totalnum Ft"

        }

        buyTickets.setOnClickListener {
            //TODO
        }
    }
}