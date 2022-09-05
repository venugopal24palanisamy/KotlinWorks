package io.systemtest.matrimony.homeScreen.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.systemtest.matrimony.homeScreen.adapter.HomeAdapter.ViewHolder
import io.systemtest.matrimony.homeScreen.MainActivity
import io.systemtest.matrimony.ProfileDetailsScreen.ProfileDetailActivity
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.databinding.ItemHomeAdapterBinding

class HomeAdapter(
    private var list: MutableList<ProfileTables>,
    private var mainActivity: MainActivity
) :
    RecyclerView.Adapter<ViewHolder>() {

    lateinit var itemHomeAdapterBinding: ItemHomeAdapterBinding

    class ViewHolder(var view: ItemHomeAdapterBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(profileTables: ProfileTables) {
            view.homemodel = profileTables
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        itemHomeAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_home_adapter,
            parent,
            false
        )

        return ViewHolder(itemHomeAdapterBinding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])


        holder.itemView.setOnClickListener {
            mainActivity.startActivity(
                Intent(mainActivity, ProfileDetailActivity::class.java).putExtra(
                    "data",
                    Gson().toJson(list[position])
                )
            )
        }

        holder.view.NoTv.setOnClickListener {
            Toast.makeText(
                mainActivity,
                list[position].profile_name + " got removed",
                Toast.LENGTH_SHORT
            ).show()
            list.remove(list[position])
            notifyDataSetChanged()
            mainActivity.UpdateUi()
        }
        holder.view.YesTv.setOnClickListener {
            Toast.makeText(
                mainActivity,
                list[position].profile_name + " got removed",
                Toast.LENGTH_SHORT
            ).show()
            list.remove(list[position])
            notifyDataSetChanged()
            mainActivity.UpdateUi()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @Override
    fun onClickListener(profileTables: ProfileTables) {
        mainActivity.startActivity(
            Intent(mainActivity, ProfileDetailActivity::class.java).putExtra(
                "data",
                Gson().toJson(profileTables)
            )
        )
    }
}