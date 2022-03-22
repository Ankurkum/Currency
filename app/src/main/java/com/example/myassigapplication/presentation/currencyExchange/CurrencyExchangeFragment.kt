package com.example.myassigapplication.presentation.currencyExchange

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.myassigapplication.R
import com.example.myassigapplication.databinding.CurrencyExchangeFragmentBinding
import com.example.myassigapplication.presentation.currencyExchange.viewModel.CurrencyExchangeViewModel
import com.example.myassigapplication.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyExchangeFragment : Fragment() {

    private var _binding: CurrencyExchangeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CurrencyExchangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyExchangeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CurrencyExchangeViewModel::class.java]

        binding.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    viewModel.calculateCostModel.value = s.toString().toDouble()
                    viewModel.calculateCost()
                }
            }

        })

        registerObservers()
        setListeners()
    }

    private fun registerObservers() {
        viewModel.baseCurrencyList.observe(viewLifecycleOwner) {
            setFromAdapter(it)
            setToAdapter(it)
        }

        viewModel.result.observe(viewLifecycleOwner) {
            binding.result.text = it.toString()
        }

        viewModel.notifyList.observe(viewLifecycleOwner) {
            binding.from.setSelection(viewModel.calculateCostModel.from)
            binding.to.setSelection(viewModel.calculateCostModel.to)
        }
        viewModel.navigateToHistoricalScreen.observe(viewLifecycleOwner) {
            it.second?.let { it2 ->
                findNavController().navigate(
                    CurrencyExchangeFragmentDirections.actionCurrencyExchangeFragment2ToHistoricalDetailsFragment(
                        resources.getString(R.string.eur), it2))
            }
        }
        viewModel.showToast.observe(viewLifecycleOwner) {
            Utils.showToast(context, it)
        }
    }

    private fun setFromAdapter(items: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.from.adapter = adapter
        binding.from.setSelection(47)
    }

    private fun setToAdapter(items: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.to.adapter = adapter
    }

    private fun setListeners() {
        binding.from.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                viewModel.calculateCostModel.from = position
                viewModel.calculateCost()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.to.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                viewModel.calculateCostModel.to = position
                viewModel.calculateCost()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.swapBtn.setOnClickListener {
            viewModel.onSwapClick()
        }

        binding.btnDetails.setOnClickListener {
            viewModel.navigateToHistoricalScreen()
        }
    }

}