package natto.com.roomsmple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import natto.com.roomsmple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding }
    val dao by lazy { ItemDatabase.getInstance(this)?.itemDao() }
    val adapter by lazy { ItemAdapter() }

    private var cnt = 0

    private val job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.adapter = adapter

        load()

        binding.insertBtn.setOnClickListener {
            //val item = Item(cnt, "title", "content")
            CoroutineScope(Dispatchers.Main + job).launch {
                dao?.insert(Item(0, "title:$cnt", "content"))
                load()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun load() {
        adapter.clear()
        CoroutineScope(Dispatchers.IO + job).launch {
            dao?.all?.let {
                it.forEach { item ->
                    adapter.add(item)
                }
                cnt = it.size
            }
        }
        adapter.notifyDataSetChanged()
    }
}
