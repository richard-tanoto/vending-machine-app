package com.richard.vendingmachineapp.feature.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.richard.vendingmachineapp.R
import com.richard.vendingmachineapp.data.Result
import com.richard.vendingmachineapp.data.model.Item
import com.richard.vendingmachineapp.data.preferences.PreferenceHelper
import com.richard.vendingmachineapp.databinding.ActivityMainBinding
import com.richard.vendingmachineapp.databinding.BottomsheetConfirmBinding
import com.richard.vendingmachineapp.databinding.BottomsheetItemDetailBinding
import com.richard.vendingmachineapp.feature.balance.BalanceActivity
import com.richard.vendingmachineapp.util.NumberUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var preferenceHelper: PreferenceHelper
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButton()

        mainViewModel.itemList.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    showLoading(false)
                    itemAdapter.setData(result.data)
                }
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Error -> {
                    showLoading(false)
                    showMessage(result.exception.message.toString())
                }
            }
        }
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter()
        binding.rvItem.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }
        itemAdapter.setOnItemClickCallback(this)
    }

    private fun setupButton() {
        preferenceHelper = PreferenceHelper(this)
        updateBalance()
        binding.btnBalance.setOnClickListener {
            startActivity(Intent(this@MainActivity, BalanceActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateBalance()
    }

    override fun onItemClicked(data: Item) {
        if (data.stock != 0) showBottomsheetPurchaseDialog(data)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showBottomsheetPurchaseDialog(item: Item) {
        val dialog = BottomSheetDialog(this)
        val dialogBinding = BottomsheetItemDetailBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.apply {
            Glide.with(this@MainActivity)
                .load(item.imageUrl)
                .into(ivItem)
            tvName.text = item.name
            tvPrice.text = NumberUtil.convertToRupiah(item.price)
            tvStock.text = String.format(
                getString(R.string.stock_format, item.stock)
            )
            btnPurchase.setOnClickListener {
                val balance = preferenceHelper.getBalance()
                if (balance < item.price)
                    showMessage("Not enough balance.")
                else {
                    item.stock = item.stock - 1
                    mainViewModel.updateItem(item)
                    preferenceHelper.saveBalance(balance - item.price.toInt())
                    updateBalance()
                    itemAdapter.notifyDataSetChanged()
                    showBottomsheetConfirmDialog()
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

    private fun showBottomsheetConfirmDialog() {
        val dialog = BottomSheetDialog(this)
        val dialogBinding = BottomsheetConfirmBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.apply {
            btnContinue.setOnClickListener {
                dialog.dismiss()
            }
            btnRetrieve.setOnClickListener {
                val balance = preferenceHelper.getBalance()
                preferenceHelper.saveBalance(0)
                updateBalance()
                showMessage("You retrieved ${NumberUtil.convertToRupiah(balance)} from the balance.")
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun updateBalance() {
        binding.btnBalance.text = String.format(
            getString(R.string.balance_format, NumberUtil.convertToRupiah(preferenceHelper.getBalance()))
        )
    }
}