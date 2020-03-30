package org.rodrigo.beauty.model.repository

import kotlinx.coroutines.Deferred
import org.rodrigo.beauty.model.data.Vendor

interface VendorRepository {
    fun getVendorsAsync(): Deferred<List<Vendor>>
}