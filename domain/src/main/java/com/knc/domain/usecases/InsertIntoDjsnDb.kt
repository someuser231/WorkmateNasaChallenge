package com.knc.domain.usecases

import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.DjsnProductModel

class InsertIntoDjsnDb(val djsnRepo: DjsnRepoItf) {
    fun execute(model: DjsnProductModel) {
        djsnRepo.insertIntoDb(model)
    }
}