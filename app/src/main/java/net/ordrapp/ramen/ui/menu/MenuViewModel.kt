package net.ordrapp.ramen.ui.menu

import androidx.lifecycle.ViewModel
import net.ordrapp.ramen.repository.MenuRepository
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {
}