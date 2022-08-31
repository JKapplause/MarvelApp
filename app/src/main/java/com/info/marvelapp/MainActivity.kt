package com.info.marvelapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.info.marvelapp.adapter.CharacterListAdapter
import com.info.marvelapp.databinding.ActivityMainBinding
import com.info.marvelapp.domain.model.Character
import com.info.marvelapp.ui.CharacterList.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel :CharacterViewModel by viewModels()
    var flag = 3
    var paginatedValue = 0
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.characterRecyclerView
        layoutManager = GridLayoutManager(this,2)
        recyclerViewCharacters()
        recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()== layoutManager.itemCount-1)
                {
                    paginatedValue +=20
                    viewModel.getAllCharactersData(paginatedValue)
                    callApi()
                }
            }
        })
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag){
                viewModel._marvelValue.collect{
                    when{
                        it.isLoading->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
                            binding.progressBar.visibility  =View.GONE
                            flag = 0
                            Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_LONG).show()

                        }
                        it.characterList.isNotEmpty()->{
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            adapter.setData(it.characterList as ArrayList<Character>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    private fun recyclerViewCharacters() {
        adapter = CharacterListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}