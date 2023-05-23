package com.example.a7month_project.data.base

abstract class BaseRepo {
    protected fun <T> doRequest(request: () -> T) {

    }
}