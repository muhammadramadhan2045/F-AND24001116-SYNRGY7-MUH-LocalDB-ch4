package com.example.mychallenge3.view.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.R
import com.example.mychallenge3.adapter.ListPlaceAdapter
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.databinding.FragmentFavoriteBinding
import com.example.mychallenge3.databinding.FragmentHomeBinding
import com.example.mychallenge3.view.ViewModelFactory
import com.example.mychallenge3.view.home.HomeViewModel

class FavoriteFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvFavorite: RecyclerView
    private lateinit var listPlaceAdapter: ListPlaceAdapter
    private val placeList = ArrayList<Place>()


    private val favoriteViewModel: FavoriteViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeList.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set action bar title
        activity?.title = getString(R.string.favorite)


        rvFavorite = binding.rvFavorite
        rvFavorite.layoutManager = LinearLayoutManager(context)
        listPlaceAdapter = ListPlaceAdapter(placeList)
        rvFavorite.adapter = listPlaceAdapter

        // Observe data from ViewModel
        favoriteViewModel.places.observe(viewLifecycleOwner) { places ->
            // Update placeList and notify adapter
            placeList.clear()
            placeList.addAll(places)
            listPlaceAdapter.notifyDataSetChanged()
        }


        favoriteViewModel.loading.observe(viewLifecycleOwner) { loading ->
            Log.d("FavoriteFragment", "loading: $loading")
            // Show loading indicator
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        // Load data from ViewModel
        favoriteViewModel.getPlacesFromLocal()



        // Set item click listener
        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Place) {
                val toDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment()
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



}