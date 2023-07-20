package com.example.recipeapp.adapter

import com.example.recipeapp.models.recipe.ResponseRecipes.Result
import com.example.recipeapp.utils.BaseDiffUtils
import com.example.recipeapp.utils.Constants
import com.example.recipeapp.utils.minToHour
import com.example.recipeapp.utils.setDynamicallyColor
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemRecipesBinding
import javax.inject.Inject

class RecentAdapter @Inject constructor() : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    private lateinit var binding: ItemRecipesBinding
    private var items = emptyList<Result>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.initAnimation()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clearAnimation()
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Result) {
            binding.apply {
                //Text
                recipeNameTxt.text = item.title
                val htmlFormatter = HtmlCompat.fromHtml(item.summary!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
                recipeDescTxt.text = htmlFormatter
                recipeLikeTxt.text = item.aggregateLikes.toString()
                recipeTimeTxt.text = item.readyInMinutes!!.minToHour()
                recipeHealthTxt.text = item.healthScore.toString()
                //Image
                val imageSplit = item.image!!.split("-")
                val imageSize = imageSplit[1].replace(Constants.OLD_IMAGE_SIZE, Constants.NEW_IMAGE_SIZE)
                recipeImg.load("${imageSplit[0]}-$imageSize") {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
                //Vegan
                if (item.vegan!!) {
                    recipeVeganTxt.setDynamicallyColor(R.color.caribbean_green)
                } else {
                    recipeVeganTxt.setDynamicallyColor(R.color.gray)
                }
                //Healthy
                when (item.healthScore) {
                    in 90..100 -> recipeHealthTxt.setDynamicallyColor(R.color.caribbean_green)
                    in 60..89 -> recipeHealthTxt.setDynamicallyColor(R.color.chineseYellow)
                    in 0..59 -> recipeHealthTxt.setDynamicallyColor(R.color.tart_orange)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let { it(item.id!!) }
                }
            }
        }

        fun initAnimation() {
            binding.root.animation = AnimationUtils.loadAnimation(context, R.anim.item_anim)
        }

        fun clearAnimation() {
            binding.root.clearAnimation()
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Result>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}