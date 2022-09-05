package io.systemtest.matrimony.gestureScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.systemtest.matrimony.gestureScreen.adapter.CardAdapter.ViewHolder
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.databinding.ItemSwipecardAdapterBinding
import io.systemtest.matrimony.gestureScreen.GuestureActivity

class CardAdapter(
    private var list: ArrayList<ProfileTables>,
    private var guestureActivity: GuestureActivity
) : RecyclerView.Adapter<ViewHolder>() {

    lateinit var bind: ItemSwipecardAdapterBinding

    class ViewHolder(var view: ItemSwipecardAdapterBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(profileTables: ProfileTables) {
            view.homemodel = profileTables
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        bind = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_swipecard_adapter,
            parent,
            false
        )

        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.view.NoTv.setOnClickListener {
            Toast.makeText(guestureActivity,list[position].profile_name+" got removed", Toast.LENGTH_SHORT).show()
            list.remove(list[position])
            notifyDataSetChanged()
            guestureActivity.UpdateUi()
        }
        holder.view.YesTv.setOnClickListener {
            Toast.makeText(guestureActivity,list[position].profile_name+" got removed", Toast.LENGTH_SHORT).show()
            list.remove(list[position])
            notifyDataSetChanged()
            guestureActivity.UpdateUi()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}