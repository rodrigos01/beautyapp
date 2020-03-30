package org.rodrigo.beauty.model.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test

internal class FirestoreVendorRepositoryTest {

    private val coroutineScope = TestCoroutineScope()

    private val firebaseDatabase: FirebaseFirestore = mock()

    private val subject = FirestoreVendorRepository(coroutineScope, firebaseDatabase)

    @Test
    fun getVendors_shouldQueryOnlyVendorType() {
        val collectionReference: CollectionReference = mock()
        whenever(firebaseDatabase.collection(any())).thenReturn(collectionReference)
        val query: Query = mock()
        whenever(collectionReference.whereEqualTo(any<String>(), any())).thenReturn(query)
        whenever(query.get()).thenReturn(mock())

        coroutineScope.launch { subject.getVendorsAsync().await() }

        verify(firebaseDatabase).collection("users")
        verify(collectionReference).whereEqualTo("type", "provider")
    }
}