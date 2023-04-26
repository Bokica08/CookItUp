package com.sorsix.cookitup.model.dto

import lombok.AllArgsConstructor
import lombok.Data


@Data
@AllArgsConstructor
class MessageResponse(private val message: String? = null) {

}