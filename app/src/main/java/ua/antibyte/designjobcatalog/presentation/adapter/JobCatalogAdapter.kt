package ua.antibyte.designjobcatalog.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import ua.antibyte.designjobcatalog.R
import ua.antibyte.designjobcatalog.data.JobInfo
import ua.antibyte.designjobcatalog.common.dpToPx

class JobCatalogAdapter(
    private val listener: OnClickListener
) : RecyclerView.Adapter<JobCatalogAdapter.JobCatalogHolder>() {
    private val jobInfoList = ArrayList<JobInfo>()
    private var prevSelectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobCatalogHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobCatalogHolder(itemView)
    }

    fun setData(jobInfoList: List<JobInfo>) {
        this.jobInfoList.clear()
        this.jobInfoList.addAll(jobInfoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: JobCatalogHolder, position: Int) {
        holder.bind(jobInfoList[position])
        holder.itemView.setOnClickListener {
            onItemClick(position)
            listener.onClick(position)
        }
    }

    private fun onItemClick(position: Int) {
        if (position != prevSelectedPosition && prevSelectedPosition != -1) {
            jobInfoList[prevSelectedPosition].isActive = false
            notifyItemChanged(prevSelectedPosition)
        }

        jobInfoList[position].isActive = true
        notifyItemChanged(position)

        prevSelectedPosition = position
    }

    override fun getItemCount() = jobInfoList.size

    class JobCatalogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val containerCardView: MaterialCardView = itemView as MaterialCardView
        private val logoImage: ImageView = itemView.findViewById(R.id.item_service_logo)
        private val titleText: TextView = itemView.findViewById(R.id.item_service_title)

        fun bind(info: JobInfo) {
            logoImage.setImageResource(info.logoResId)
            titleText.text = info.title
            if (info.isActive) {
                setBorderIfNeed()
            } else {
                hideBorder()
            }
        }

        private fun setBorderIfNeed() {
            containerCardView.strokeColor =
                ContextCompat.getColor(itemView.context, R.color.teal_200)
            containerCardView.strokeWidth = dpToPx(5)
        }

        private fun hideBorder() {
            containerCardView.strokeColor =
                ContextCompat.getColor(itemView.context, R.color.teal_200)
            containerCardView.strokeWidth = dpToPx(0)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}