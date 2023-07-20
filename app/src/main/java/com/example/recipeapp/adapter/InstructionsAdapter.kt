package com.example.recipeapp.adapter

import com.example.recipeapp.models.detail.ResponseDetail.ExtendedIngredient
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
import com.example.recipeapp.databinding.ItemInstructionBinding
import javax.inject.Inject

class InstructionsAdapter @Inject constructor() : RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {

    private lateinit var binding: ItemInstructionBinding
    private var items = emptyList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemInstructionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ExtendedIngredient) {
            binding.apply {
                //Text
                nameTxt.text = item.name
                countTxt.text = "${item.amount} ${item.unit}"
                //Image
                val image = "${Constants.BASE_URL_IMAGE_INGREDIENTS}${item.image}"
                coverImg.load(image) {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
            }
        }
    }

    fun setData(data: List<ExtendedIngredient>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}