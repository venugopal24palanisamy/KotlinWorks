package io.systemtest.matrimony.ProfileDetailsScreen


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.databinding.ActivityProfileDetailBinding
import org.json.JSONArray
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.reflect.Array.get


class ProfileDetailActivity : AppCompatActivity() {

    val viewModel: ProfileDetailViewModel by viewModel()

    lateinit var profileTables: ProfileTables
    var data: String? = null
    lateinit var binding: ActivityProfileDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_detail)

        data = intent.getStringExtra("data")
        profileTables = Gson().fromJson(data, ProfileTables::class.java)

        setViewsListeners()
    }

    private fun setViewsListeners() {
        var jsonArray = JSONArray(profileTables.profile_imagesList)
        val imageList = ArrayList<SlideModel>()
        for (jsonIndex in 0..(jsonArray.length() - 1)) {
            Log.d("JSON", jsonArray.getJSONObject(jsonIndex).getString("profileImages"))
            imageList.add(
                SlideModel(
                    jsonArray.getJSONObject(jsonIndex).getString("profileImages"),
                    ""
                )
            )
        }

        binding.BackImg.setOnClickListener {
            onBackPressed()
        }





        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
        binding.CountTv.text = (1).toString() + "/" + imageList.size
        binding.imageSlider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                Log.d("onItemChanged", position.toString())
                binding.CountTv.text = (position + 1).toString() + " / " + imageList.size
            }
        })
        binding.profileDetail = profileTables

    }


}


