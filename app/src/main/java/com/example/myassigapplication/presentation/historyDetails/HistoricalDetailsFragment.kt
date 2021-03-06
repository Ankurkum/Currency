package com.example.myassigapplication.presentation.historyDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassigapplication.R
import com.example.myassigapplication.databinding.HistoricalDetailsFragmentBinding
import com.example.myassigapplication.domain.model.CurrencyDetailsData
import com.example.myassigapplication.domain.model.ExchangeItemData
import com.example.myassigapplication.presentation.currencyExchange.ExchangeRatesAdapter
import com.example.myassigapplication.presentation.historyDetails.viewModel.HistoricalDetailsViewModel
import com.example.myassigapplication.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricalDetailsFragment : Fragment() {

    private var _binding: HistoricalDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoricalDetailsViewModel
    private val args: HistoricalDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoricalDetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HistoricalDetailsViewModel::class.java]

        viewModel.onViewCreated(args.baseCurrency, args.convertCurrency)
        registerObservers()

        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_historicalDetailsFragment_to_currencyExchangeFragment23)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun registerObservers(){
        viewModel.setAdapter.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
        viewModel.setTopCurrRatesRecyclerView.observe(viewLifecycleOwner) {
            setTopCurrRatesRecyclerView(it)
        }
        viewModel.showToast.observe(viewLifecycleOwner) {
            Utils.showToast(context, it)
        }

    }

    private fun setupRecyclerView(items: ArrayList<ExchangeItemData>) {
        val exchangeAdapter = ExchangeRatesAdapter(items)
        binding.rvExchangeRates.apply {
            adapter = exchangeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setTopCurrRatesRecyclerView(items: ArrayList<CurrencyDetailsData>) {
        val exchangeAdapter = TopCurrenciesRatesAdapter(items)
        binding.rvTopCurrencies.apply {
            adapter = exchangeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

}