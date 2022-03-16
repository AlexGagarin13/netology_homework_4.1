import java.text.DecimalFormat

const val MINIMUM_COMMISSION_VISA_MIR = 35_00.0
const val COMMISSION_VISA_MIR = 0.0075
const val LIMIT_WITHOUT_COMMISSION_MASTERCARD_MAESTRO = 75_000_00.0
const val COMMISSION_MASTERCARD_MAESTRO = 0.006
const val COMMISSION_MASTERCARD_MAESTRO_FIX = 20_00.0
const val CARDS_TRANSFER_LIMIT = 600_000_00.0
const val VK_PAY_LIMIT = 40_000_00.0

fun main() {

    val accountType1 = "MASTERCARD/MAESTRO"
    val accountType2 = "VISA/MIR"
    val previousTransfersInMonthInPennies1 = 66_000_00.0
    val previousTransfersInMonthInPennies2 = 40_000_00.0
    val transferAmountInPennies1 = 11_000_00.0
    val transferAmountInPennies2 = 50_023_50.0
    val transferAmountInPennies3 = 41_000_00.0
    val df = DecimalFormat("#.##")

    println(
        "Коммисия к оплате по $accountType1 ${
            df.format(
                calculateCommission(
                    accountType1,
                    previousTransfersInMonthInPennies1,
                    transferAmountInPennies1
                )
            )
        } руб."
    )

    println(
        "Коммисия к оплате $accountType2 ${
            df.format(
                calculateCommission(
                    accountType2,
                    previousTransfersInMonthInPennies2,
                    transferAmountInPennies2
                )
            )
        } руб."
    )
    println(
        "Коммисия к оплате ${
            df.format(
                calculateCommission(
                    transferAmountInPennies = transferAmountInPennies3
                )
            )
        } руб."
    )
}

fun calculateCommission(
    accountType: String = "VK Pay",
    previousTransfersInMonthInPennies: Double = 0.0,
    transferAmountInPennies: Double
): Double {
    val commissionToPay: Double = when (accountType) {
        "VK Pay" -> if (previousTransfersInMonthInPennies <= VK_PAY_LIMIT) {
            if (transferAmountInPennies <= VK_PAY_LIMIT) {
                0.0
            } else error("Месячный лимит для перевода VK Pay превышен!")
        } else error("Месячный лимит для перевода через VK Pay превышен!")
        "MASTERCARD/MAESTRO" -> if (previousTransfersInMonthInPennies <= CARDS_TRANSFER_LIMIT) {
            if (transferAmountInPennies >= LIMIT_WITHOUT_COMMISSION_MASTERCARD_MAESTRO || previousTransfersInMonthInPennies + transferAmountInPennies >= LIMIT_WITHOUT_COMMISSION_MASTERCARD_MAESTRO) {
                (transferAmountInPennies * COMMISSION_MASTERCARD_MAESTRO) + COMMISSION_MASTERCARD_MAESTRO_FIX
            } else 0.0
        } else error("Месячный лимит для перевода превышен!")
        "VISA/MIR" -> if (previousTransfersInMonthInPennies <= CARDS_TRANSFER_LIMIT) {
            if (transferAmountInPennies * COMMISSION_VISA_MIR < MINIMUM_COMMISSION_VISA_MIR) {
                (transferAmountInPennies * COMMISSION_VISA_MIR) + MINIMUM_COMMISSION_VISA_MIR
            } else transferAmountInPennies * COMMISSION_VISA_MIR
        } else error("Месячный лимит для перевода превышен!")
        else -> 0.0
    }
    return commissionToPay / 100
}