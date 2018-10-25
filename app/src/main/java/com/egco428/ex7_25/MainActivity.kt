package com.egco428.ex7_25

import android.app.ListActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.egco428.ex7_25.DataSource.CommentDataSource
import com.egco428.ex7_25.Model.Comment

import java.util.*

class MainActivity : ListActivity() {

    private var dataSource:CommentDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataSource = CommentDataSource(this)
        dataSource!!.open()
        val values = dataSource!!.allComments
        val adapter = ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_1,values)
        setListAdapter(adapter)

    }
    fun OnClick(view: View){
        val adapter = getListAdapter() as ArrayAdapter<Comment>
        var comment : Comment? = null
        when(view.getId()){
            R.id.addBtn->{
                val comments = arrayOf("Summer","Winter","Spring","Fall","Raining")
                val nextInt = Random().nextInt(5)
                comment = dataSource!!.createComment(comments[nextInt])
                adapter.add(comment)

            }
            R.id.deleteBtn->{
                if(getListAdapter().getCount() > 0){
                    comment = getListAdapter().getItem(0) as Comment
                    dataSource!!.deleteComment(comment!!)
                    adapter.remove(comment)
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        dataSource!!.open()
    }

    override fun onPause() {
        super.onPause()
        dataSource!!.close()
    }
}
