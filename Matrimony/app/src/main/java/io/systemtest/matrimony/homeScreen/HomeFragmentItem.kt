package io.systemtest.matrimony.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.databinding.ItemHomeAdapterBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class HomeFragmentItem : Fragment() {
    lateinit var profile: ProfileTables

    private lateinit var binding: ItemHomeAdapterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_home_adapter,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        val data = arguments?.getString("data")
        Log.d("setupData",data.toString())
        profile = Gson().fromJson(data.toString(), ProfileTables::class.java)
        binding.homemodel = profile
    }

}