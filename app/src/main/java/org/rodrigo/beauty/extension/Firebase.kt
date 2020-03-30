package org.rodrigo.beauty.extension

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend inline fun <reified T : Any> Task<QuerySnapshot>.await(): List<T> =
    suspendCoroutine { continuation ->
        addOnSuccessListener {
            continuation.resume(it.map { queryDocumentSnapshot -> queryDocumentSnapshot.toObject<T>() })
        }
        addOnFailureListener {
            throw it
        }
    }