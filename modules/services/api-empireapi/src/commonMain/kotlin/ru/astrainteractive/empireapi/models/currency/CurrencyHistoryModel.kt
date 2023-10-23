package ru.astrainteractive.empireapi.models.currency

class CurrencyHistoryModel(
    val currencyModel: CurrencyModel,
    val entries: List<CurrencyHistoryEntry>
)
