package com.example.mychallenge3.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.R
import com.example.mychallenge3.adapter.ListPlaceAdapter
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.databinding.FragmentHomeBinding
import com.example.mychallenge3.view.ViewModelFactory


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvPlace: RecyclerView
    private val placeList = ArrayList<Place>()

    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        placeList.addAll(getListPlaces())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list ->{
                //recyclerView jadi list liear
                rvPlace.layoutManager = LinearLayoutManager(context)
            }
            R.id.action_grid -> {
                //recyclerView jadi grid
                rvPlace.layoutManager = GridLayoutManager(context, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvPlace = binding.rvPlace
        rvPlace.setHasFixedSize(true)
        showRecyclerList()

    }

    private fun getListPlaces():ArrayList<Place>{
        return homeViewModel.places.value ?: arrayListOf()
    }

    private fun showRecyclerList() {
        rvPlace.layoutManager = LinearLayoutManager(context)
        val listPlaceAdapter = ListPlaceAdapter(placeList)
        rvPlace.adapter = listPlaceAdapter



        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Place) {
                val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                toDetailFragment.name = data.name
                toDetailFragment.description = data.description
                toDetailFragment.image = data.image
                findNavController().navigate(toDetailFragment)
            }


        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}