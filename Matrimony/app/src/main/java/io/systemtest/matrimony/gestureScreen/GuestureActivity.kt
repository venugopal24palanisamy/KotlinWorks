package io.systemtest.matrimony.gestureScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.littlemango.stacklayoutmanager.StackLayoutManager
import io.systemtest.matrimony.gestureScreen.adapter.CardAdapter
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.Dao.ProfilesDao
import io.systemtest.matrimony.appDatabase.DatabaseClient
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.databinding.ActivityGuestureBinding
import org.koin.android.viewmodel.ext.android.viewModel


class GuestureActivity : AppCompatActivity() {


    val viewModel: GuestureViewModel by viewModel()
    lateinit var database: ProfilesDao
    lateinit var binding: ActivityGuestureBinding
    lateinit var layoutManager: StackLayoutManager
    var profileTablesList = ArrayList<ProfileTables>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_guesture)

        this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.window.setStatusBarColor(resources.getColor(R.color.green))
        setUpDatabase()
        setListenersAndViews()
        getAllProfileDetails()
        setObservers()
    }

    fun setUpDatabase() {
        database = DatabaseClient().getDatabaseClient(this).profile_dao()

    }

    fun setListenersAndViews() {
        layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.TOP_TO_BOTTOM)
        binding.StackRv.layoutManager = layoutManager

        binding.BackImg.setOnClickListener {
            onBackPressed()
        }

    }

    private fun getAllProfileDetails() {

        viewModel.also {
            it.getAllProfiles(database)
        }
    }

    fun setObservers(){
        viewModel.GetProfileDetails().observe(this, Observer {
            profileTablesList = ArrayList<ProfileTables>()
            profileTablesList = it as ArrayList<ProfileTables>

            Log.d("setObservers", profileTablesList.size.toString())


            if (profileTablesList.size > 0) {
                binding.StackRv.adapter = CardAdapter(it, this)
                binding.NoDataCl.visibility = View.GONE
            }

        })
    }

    fun UpdateUi() {
        Log.d( "UpdateUi",profileTablesList.size.toString() )
        if (profileTablesList.size == 0) {
            binding.NoDataCl.visibility = View.VISIBLE

            binding.NoDataCl.setOnClickListener {
                getAllProfileDetails()
            }

        }else{
            binding.NoDataCl.visibility = View.GONE
        }
    }
}