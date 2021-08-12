package com.app.checkcreditscore.domain.model.response

import com.app.checkcreditscore.domain.model.EntityMain

data class EntityMainResponse(
    val entityMain: EntityMain
) : EntityResponse()