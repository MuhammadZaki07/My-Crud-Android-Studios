package com.dev.smkpgri3.mycrud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.dev.smkpgri3.mycrud.Data.AppDatabase
import com.dev.smkpgri3.mycrud.Data.Entity.User
import com.dev.smkpgri3.mycrud.adapter.UserAdapter
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab:FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog viewws
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog,which ->
                    if (which == 0){
                        //Untuk Ubahhh
                        val intent = Intent(this@MainActivity,EditorActivity::class.java)
                        intent.putExtra("id",list[position].uid)
                        startActivity(intent)
                    }else if(which == 1){
                        // Untuk Hapuss
                        database.userDao().delete(list[position])
                        getData()
                    }else{
                        // Btalll

                        dialog.dismiss()
                    }
                })
                // tampilan dialog
                val dialogView = dialog.create()
                dialogView.show()
            }

        })


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext,VERTICAL,false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext,VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this,EditorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

@SuppressLint("NotifyDataSetChanged")
fun getData(){
    list.clear()
    list.addAll(database.userDao().getAll())
    adapter.notifyDataSetChanged()
}

}