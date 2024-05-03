package com.example.mychallenge3.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mychallenge3.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

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


        val name = com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).name
        val description = com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).description
        val image = com.example.mychallenge3.view.detail.DetailFragmentArgs.fromBundle(arguments as Bundle).image

        binding.textDetail.text = name

        binding.tvDesc.text = description

        Glide.with(this)
            .load(image)
            .into(binding.imgDetail)

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