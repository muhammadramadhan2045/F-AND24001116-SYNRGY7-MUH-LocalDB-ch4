package com.example.mychallenge3.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.FragmentDetailBinding
import com.example.mychallenge3.view.ViewModelFactory
import com.example.mychallenge3.view.home.HomeViewModel


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val name =
            com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).name
        val description =
            com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).description
        val image =
            com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).image
        activity?.title = name
        binding.textDetail.text = name
        binding.tvDesc.text = description

        Glide.with(this)
            .load(image)
            .into(binding.imgDetail)

        goToBrowser(name)
        detailViewModel.getPlaceFromFavorite(name)



        setUpFavoriteButton(name, description, image)
        detailViewModel.insertPlace.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Berhasil ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
        }

        detailViewModel.deletePlace.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Berhasil dihapus dari favorite", Toast.LENGTH_SHORT).show()
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun setUpFavoriteButton(name: String, description: String, image: String) {

        detailViewModel.placeLocal.observe(viewLifecycleOwner) { place ->
            binding.fabFavorite.setOnClickListener {
                if (place != null) {
                    detailViewModel.deletePlaceFromFavorite(place)
                } else {
                    detailViewModel.savePlaceToFavorite(name, description, image)
                }
            }
        }
    }

    private fun goToBrowser(name: String) {
        binding.btnGoToBrowser.setOnClickListener {
            //intent di fragment
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com/search?q=$name")
            startActivity(intent)
        }
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