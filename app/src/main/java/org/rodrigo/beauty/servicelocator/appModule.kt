package org.rodrigo.beauty.servicelocator

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.rodrigo.beauty.model.repository.FirestoreVendorRepository
import org.rodrigo.beauty.model.repository.VendorRepository
import org.rodrigo.beauty.ui.main.MainViewModel

val appModule = module {
    factory {
        FirestoreVendorRepository(
            GlobalScope,
            FirebaseFirestore.getInstance()
        ) as VendorRepository
    }
    viewModel { MainViewModel(GlobalScope, get()) }
}