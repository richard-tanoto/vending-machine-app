package com.richard.vendingmachineapp.feature.balance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.richard.vendingmachineapp.data.preferences.PreferenceHelper
import com.richard.vendingmachineapp.databinding.ActivityBalanceBinding
import com.richard.vendingmachineapp.util.NumberUtil

class BalanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBalanceBinding
    private lateinit var preferenceHelper: PreferenceHelper
    private var balance: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.title = "Balance"
            it.setDisplayHomeAsUpEnabled(true)
        }

        getBalance()
        setClickListener()
    }

    private fun getBalance() {
        preferenceHelper = PreferenceHelper(this)
        balance = preferenceHelper.getBalance()
        binding.tvBalance.text = NumberUtil.convertToRupiah(balance)
    }

    private fun setClickListener() {
        binding.btnTopUp.setOnClickListener {
            val topup = when(binding.chipTopUp.checkedChipId) {
                binding.chip1.id -> 2000
                binding.chip2.id -> 5000
                binding.chip3.id -> 10000
                binding.chip4.id -> 20000
                binding.chip5.id -> 50000
                else -> 0
            }
            preferenceHelper.saveBalance(balance + topup)
            Toast.makeText(this, "Top up successful.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
