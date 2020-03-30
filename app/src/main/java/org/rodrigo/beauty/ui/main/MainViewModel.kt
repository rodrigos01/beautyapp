package org.rodrigo.beauty.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import org.rodrigo.beauty.extension.asLiveData
import org.rodrigo.beauty.model.repository.VendorRepository

class MainViewModel(coroutineScope: CoroutineScope, repository: VendorRepository) : ViewModel() {
    val vendors =
        repository.getVendorsAsync().asLiveData(coroutineScope.coroutineContext)
}