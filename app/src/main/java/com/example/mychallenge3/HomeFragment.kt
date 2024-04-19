package com.example.mychallenge3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.HomeFragmentDirections
import com.example.mychallenge3.adapter.ListPlaceAdapter
import com.example.mychallenge3.data.Place
import com.example.mychallenge3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvPlace: RecyclerView
    private val placeList = ArrayList<Place>()



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


        placeList.addAll(getListPlaces())
        showRecyclerList()


    }

    private fun getListPlaces():ArrayList<Place>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listPlace = ArrayList<Place>()
        for (i in dataName.indices){
            val place = Place(
                dataName[i],
                dataDescription[i],
                dataPhoto[i]
            )
            listPlace.add(place)
        }

        return listPlace
    }

    private fun showRecyclerList() {
        rvPlace.layoutManager = LinearLayoutManager(context)
        val listPlaceAdapter = ListPlaceAdapter(placeList)
        rvPlace.adapter = listPlaceAdapter



        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Place) {

            }


        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}