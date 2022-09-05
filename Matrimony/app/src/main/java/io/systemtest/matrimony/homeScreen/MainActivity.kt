package io.systemtest.matrimony.homeScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import io.systemtest.matrimony.appDatabase.Dao.ProfilesDao
import io.systemtest.matrimony.appDatabase.DatabaseClient
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.homeScreen.Modal.ImagesList
import io.systemtest.matrimony.R
import io.systemtest.matrimony.databinding.ActivityMainBinding
import io.systemtest.matrimony.gestureScreen.GuestureActivity
import io.systemtest.matrimony.homeScreen.adapter.HomeAdapter
import io.systemtest.matrimony.homeScreen.adapter.ViewPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), DiscreteScrollView.OnItemChangedListener<HomeAdapter.ViewHolder> {
    lateinit var profile: ProfileTables
    val viewModel: HomeScreenViewModel by viewModel()
    lateinit var database: ProfilesDao
    lateinit var binding: ActivityMainBinding
    private lateinit var mInfiniteScrollWrapper: InfiniteScrollAdapter<*>
    lateinit var adapter: HomeAdapter
    val profileImagesList = ArrayList<ImagesList>()
    var profileTablesList = ArrayList<ProfileTables>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setDatabaseClient()
        setListeners()
        getAllProfileDetails()
        setObservers()
    }


    private fun setListeners() {
        binding.MoreImg.setOnClickListener {
            startActivity(Intent(this, GuestureActivity::class.java).putExtra("data", ""))
        }
        binding.ProfileRv.layoutManager =
                // LoopingLayoutManager(this, LoopingLayoutManager.HORIZONTAL, false)
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }


    private fun setDatabaseClient() {
        database = DatabaseClient().getDatabaseClient(this).profile_dao()
    }

    private fun AddDetailsToDb() {
        profileImagesList.add(
            ImagesList(
                "https://img.freepik.com/premium-vector/african-american-woman-avatar-with-glasses-portrait-young-girl-vector-illustration-face_217290-363.jpg?w=740"
            )
        )

        profileImagesList.add(
            ImagesList(
                "https://img.freepik.com/premium-vector/beautiful-women-excited-expressions_96037-110.jpg?w=1380"
            )
        )
        profileImagesList.add(
            ImagesList(
                "https://img.freepik.com/premium-vector/portrait-young-beautiful-woman-profile-with-long-flowing-hair_478440-102.jpg?size=338&ext=jpg&ga=GA1.2.1002440564.1588571494"
            )
        )
        profileImagesList.add(
            ImagesList(
                "https://img.freepik.com/free-vector/flat-hand-drawn-female-team-leader_52683-55365.jpg?size=338&ext=jpg&ga=GA1.2.1002440564.1588571494"
            )
        )
        profileImagesList.add(
            ImagesList(
                "https://img.freepik.com/premium-vector/beautiful-young-lady-doctor-vector-illustration_96037-31.jpg?size=626&ext=jpg&ga=GA1.2.1002440564.1588571494"
            )
        )

        viewModel.let {
            it.AddProfileDetails(this, database, profileImagesList)
        }

    }

    private fun getAllProfileDetails() {

        viewModel.also {
            it.getAllProfiles(database)
        }
    }

    private fun setObservers() {

        viewModel.GetProfileDetails().observe(this, Observer {
            profileTablesList = ArrayList<ProfileTables>()
            profileTablesList = it as ArrayList<ProfileTables>

            Log.d("setObservers", profileTablesList.size.toString())


            if (profileTablesList.size > 0) {
                //binding.ProfileRv.adapter = HomeAdapter(it, this)
                //binding.viewPager.setAdapter(HomeAdapter(it,this))
                adapter = HomeAdapter(it, this)

                mInfiniteScrollWrapper = InfiniteScrollAdapter.wrap(adapter)

                binding.descreteScrollView.adapter = mInfiniteScrollWrapper
                binding.descreteScrollView.setItemTransformer(InfiniteCarouselTransformer())


                binding.NoDataCl.visibility = View.GONE
            } else {
                AddDetailsToDb()
            }

        })

        viewModel.getAddedSuccess().observe(this, Observer {

            getAllProfileDetails()
        })
    }

    fun UpdateUi() {
        Log.d("UpdateUi", profileTablesList.size.toString())
        if (profileTablesList.size == 0) {
            binding.NoDataCl.visibility = View.VISIBLE

            binding.NoDataCl.setOnClickListener {
                getAllProfileDetails()
            }

        } else {
            binding.NoDataCl.visibility = View.GONE
        }
    }

    override fun onCurrentItemChanged(p0: HomeAdapter.ViewHolder?, p1: Int) {
        val realPosition = mInfiniteScrollWrapper.realCurrentPosition

        Log.d("onCurrentItemChanged",realPosition.toString())

    }
}