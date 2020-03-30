package org.rodrigo.beauty.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.provider_result_list_item.view.*
import org.rodrigo.beauty.R
import org.rodrigo.beauty.model.data.Vendor


class VendorResultListAdapter(lifecycleOwner: LifecycleOwner, result: LiveData<List<Vendor>>) :
    RecyclerView.Adapter<VendorResultListAdapter.ViewHolder>() {

    var dataSet: List<Vendor>? = null

    init {
        result.observe(lifecycleOwner, Observer {
            dataSet = it
            notifyDataSetChanged()
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
        val address = view.address
        val picture = view.picture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.provider_result_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vendor = dataSet?.get(position)
        holder.name.text = vendor?.name
        holder.address.text = vendor?.address?.addressLine

        vendor?.picture?.let {
            Glide.with(holder.itemView.context)
                .load(it)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.picture)
        }
    }
}