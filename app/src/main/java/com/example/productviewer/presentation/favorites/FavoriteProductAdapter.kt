package com.example.productviewer.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productviewer.R
import com.example.productviewer.databinding.ItemFavoriteProductBinding
import com.example.productviewer.domain.entity.Product
import com.example.productviewer.presentation.common.ProductDiffUtilCallback

class FavoriteProductAdapter(private val onProductClick: (Long) -> Unit) :
    ListAdapter<Product, FavoriteProductAdapter.FavoriteProductViewHolder>(ProductDiffUtilCallback) {
    inner class FavoriteProductViewHolder(private val binding: ItemFavoriteProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            with(binding) {
                Glide.with(root.context).load(item.imageUrl).into(ivImage)
                tvTitle.text = item.title
                tvPrice.text = root.context.getString(R.string.price, item.price.toString())
                root.setOnClickListener {
                    onProductClick.invoke(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder {
        return FavoriteProductViewHolder(
            ItemFavoriteProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}