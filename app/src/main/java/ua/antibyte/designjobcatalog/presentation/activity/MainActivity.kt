package ua.antibyte.designjobcatalog.presentation.activity

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import ua.antibyte.designjobcatalog.R
import ua.antibyte.designjobcatalog.common.jobList
import ua.antibyte.designjobcatalog.presentation.adapter.JobCatalogAdapter

class MainActivity : AppCompatActivity() {
    private var adapter: JobCatalogAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initJobCatalogRecyclerView()
        setListeners()
    }

    private fun showDoneButton() {
        btn_main_done.visibility = View.VISIBLE
        getDoneButtonAnimator().apply {
            target = btn_main_done
        }.start()
    }

    private fun setListeners() {
        btn_main_done.setOnClickListener { onDoneClickButton() }
    }

    private fun onDoneClickButton() {
        finish()
        startActivity(intent)
    }

    private fun initJobCatalogRecyclerView() {
        adapter = createJobCatalogAdapter()
        rv_main_job_catalog.adapter = adapter
    }

    private fun getDoneButtonAnimator() =
        AnimatorInflater.loadAnimator(this, R.animator.done_button_appearance) as ObjectAnimator


    private fun createJobCatalogAdapter(): JobCatalogAdapter {
        val listener = createOnClickListener()
        return JobCatalogAdapter(listener).apply {
            setData(jobList.map { it.copy() })
        }
    }

    private fun createOnClickListener() = object : JobCatalogAdapter.OnClickListener {
        override fun onClick(position: Int) {
            if (!btn_main_done.isVisible) {
                showDoneButton()
            }
        }
    }
}