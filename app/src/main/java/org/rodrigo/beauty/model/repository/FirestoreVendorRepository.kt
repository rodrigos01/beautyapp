package org.rodrigo.beauty.model.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import org.rodrigo.beauty.extension.await
import org.rodrigo.beauty.model.data.Vendor

class FirestoreVendorRepository(
    private val coroutineScope: CoroutineScope,
    private val database: FirebaseFirestore
) : VendorRepository {
    override fun getVendorsAsync() = coroutineScope.async<List<Vendor>> {
        database.collection("users")
            .whereEqualTo("type", "provider")
            .get().await()
    }
}