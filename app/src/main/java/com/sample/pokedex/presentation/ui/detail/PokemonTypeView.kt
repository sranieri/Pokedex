package com.sample.pokedex.presentation.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.sample.pokedex.databinding.PokemonTypeBinding
import com.sample.pokedex.presentation.utils.getColorResByType
import com.sample.pokedex.presentation.utils.getTypeDrawable

class PokemonTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: PokemonTypeBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PokemonTypeBinding.inflate(inflater, this, true)
    }

    fun updateColors(type: String) {
        val colorResByType = getColorResByType(type)
        val color = ContextCompat.getColor(context, colorResByType)
        val img = getTypeDrawable(type)

        binding.typeCard.setCardBackgroundColor(color)
        binding.typeName.setChipBackgroundColorResource(colorResByType)
        binding.typeName.text = type.uppercase()
        binding.typeImg.setImageResource(img)
    }

}