package com.ndroid.quotesapp.mapper

import com.ndroid.quotesapp.models.QuoteDto

import com.ndroid.quotesapp.models.QuoteModel


/**
 * Created by Nityen on 08-05-2025.
 */


fun QuoteDto.toDomain(workType:String):QuoteModel{
    return  QuoteModel(author,id,quote,workType)
}