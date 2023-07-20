package com.example.recipeapp.adapter

import com.example.recipeapp.models.detail.ResponseSimilar.ResponseSimilarItem
import com.example.recipeapp.utils.BaseDiffUtils
import com.example.recipeapp.utils.Constants
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemSimilarBinding
import javax.inject.Inject

class SimilarAdapter @Inject constructor() : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    private lateinit var binding: ItemSimilarBinding
    private var items = emptyList<ResponseSimilarItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseSimilarItem) {
            binding.apply {
                //Text
                nameTxt.text = item.title
                //Image
                val image = "${Constants.BASE_URL_IMAGE_RECIPE}${item.id}-${Constants.NEW_IMAGE_SIZE}"
                coverImg.load(image) {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let { it(item.id!!) }
                }
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<ResponseSimilarItem>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}