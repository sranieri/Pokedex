package com.sample.pokedex.presentation.ui

import io.uniflow.core.flow.data.UIEvent

sealed class PokedexEvent: UIEvent() {
    object GenericErrorEvent: PokedexEvent()
}