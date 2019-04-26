package com.joseneves.pdf

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.print.PrintAttributes
import android.print.PrintManager
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.support.v7.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    var mRecyclerView: RecyclerView? = null
    private var mAdapter: LineAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generate_button.setOnClickListener {
            printDocument(page_view)
        }

        val items: MutableList<String> = ArrayList()
        items.add(0, "item 0")
        items.add(1, "item 1")
        items.add(2, "item 2")
        items.add(3, "item 3")
        items.add(4, "item 4")
        items.add(5, "item 5")
        items.add(6, "item 6")
        items.add(7, "item 7")
        items.add(8, "item 8")
        items.add(9, "item 9")
        items.add(10, "item 10")

        mAdapter = LineAdapter(items)

        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView!!.layoutManager = GridLayoutManager(this, 1)
        mRecyclerView!!.adapter = mAdapter
    }

    private fun printDocument(view: View) {
        val printManager = this.getSystemService(Context.PRINT_SERVICE) as PrintManager

        val jobName = "Document"
        val attributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .build()
        printManager.print(
            jobName, PDFAdapter(this, view), attributes
        )
    }

}
