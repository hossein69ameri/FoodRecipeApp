package com.example.recipeapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemPopularBinding
import com.example.recipeapp.model.ResponseRecipes
import com.example.recipeapp.model.ResponseRecipes.Result
import com.example.recipeapp.util.BaseDiffUtils
import com.example.recipeapp.util.Constants
import javax.inject.Inject

class PopularAdapter @Inject constructor() : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private lateinit var binding: ItemPopularBinding
    private var items = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Result) {
            binding.apply {
                //text
                popularNameTxt.text = item.title
                popularPriceTxt.text = "${item.pricePerServing} $"
                //image
                val imageSplit = item.image!!.split("-")
                val imageSize =
                    imageSplit[1].replace(Constants.OLD_IMAGE_SIZE, Constants.NEW_IMAGE_SIZE)
                popularImg.load("${imageSplit[0]}-$imageSize") {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
                //click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Result>) {
        val adapterDiffUtil = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtil)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }

}