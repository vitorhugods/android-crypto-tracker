package xyz.schwaab.crypto.blockchain.model

enum class ChartTypeDTO(val serializedName: String) {
    TRANSACTIONS_PER_SECOND("transactions-per-second"),
    MARKET_PRICE("market-price")
}