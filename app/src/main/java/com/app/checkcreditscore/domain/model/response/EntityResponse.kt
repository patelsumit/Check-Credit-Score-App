package com.app.checkcreditscore.domain.model.response

abstract class EntityResponse (
    var status : Int = 0,
    var message : String = String(),
    var isOkay : Boolean = false
)