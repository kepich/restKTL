package com.restful.rest.exceptions
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class DuplicateEntryException(message: String): RuntimeException(message) {
}