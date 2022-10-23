package com.padc.ponnya.hellomoviebooking.dummy

import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.*

val dummySelectTime = listOf(
    Pair(
        "Available In", listOf("2D", "3D", "IMAX")
    ),
    Pair(
        "GC:Golden City",
        listOf("9:30 AM", "11:45 AM", "3:30 PM", "5:00 PM", "7:00 PM", "9:30 PM")
    ),
    Pair(
        "GC:West Point",
        listOf("9:30 AM", "10:30 AM", "1:30 PM", "3:30 PM", "5:00 PM", "8:30 PM")
    )
)

val dummyComboSet = listOf(
    mapOf(
        "type" to "Combo set M",
        "detail" to "Combo size M 22oz. Coke (X1) \nand medium popcorn (X1)",
        "amount" to "15$"
    ),
    mapOf(
        "type" to "Combo set L",
        "detail" to "Combo size M 32oz. Coke (X1) \nand large popcorn (X1)",
        "amount" to "18$"
    ),
    mapOf(
        "type" to "Combo for 2",
        "detail" to "Combo size 2 32oz. Coke (X2) \nand large popcorn (X1)",
        "amount" to "20$"
    )
)

val dummyPaymentMethod = listOf(
    mapOf(
        "icon" to R.drawable.ic_baseline_credit_card_white_24dp,
        "type" to "Credit card",
        "detail" to "visa, master card, JCB"
    ),
    mapOf(
        "icon" to R.drawable.ic_credit_card_white_24dp,
        "type" to "Internet banking(ATM card)",
        "detail" to "visa, master card, JCB"
    ),
    mapOf(
        "icon" to R.drawable.ic_baseline_account_balance_wallet_white_24dp,
        "type" to "E-wallet",
        "detail" to "Paypal"
    )
)

val dummyVisaCard = listOf(
    mapOf(
        "number" to "9 0 1 5",
        "name" to "Ponnya",
        "date" to "11/22"
    ),
    mapOf(
        "number" to "8 0 1 4",
        "name" to "Lily Johnson",
        "date" to "08/21"
    ),
    mapOf(
        "number" to "5 1 0 4",
        "name" to "James Franco",
        "date" to "09/21"
    ),
    mapOf(
        "number" to "1 1 6 4",
        "name" to "Johnny Depp",
        "date" to "11/21"
    )
)/*
val dummySeats = listOf(
    MovieSeatVO(title = "A", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_EMPTY),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_EMPTY),
    MovieSeatVO(title = "A", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "B", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "B", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "C", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "C", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "D", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "D", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "E", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_TAKEN),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "E", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "F", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "F", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "G", type = SEAT_TYPE_TEXT),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "", type = SEAT_TYPE_AVAILABLE),
    MovieSeatVO(title = "G", type = SEAT_TYPE_TEXT),

    )*/