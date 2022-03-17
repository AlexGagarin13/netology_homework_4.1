import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.IllegalStateException

class MainKtTest {

    @Test
    fun calculateCommission_MASTERCARD_MAESTRO_with_commission() {
        val accountType1 = "MASTERCARD/MAESTRO"
        val previousTransfersInMonthInPennies1 = 66_000_00.0
        val transferAmountInPennies1 = 11_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        assertEquals(80.0, result, 0.00001)
    }

    @Test
    fun calculateCommission_MASTERCARD_MAESTRO_without_commission() {
        val accountType1 = "MASTERCARD/MAESTRO"
        val previousTransfersInMonthInPennies1 = 66_000_00.0
        val transferAmountInPennies1 = 5_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        assertEquals(0.0, result, 0.00001)
    }

    @Test(expected = IllegalStateException::class)
    fun out_of_cards_limit_MASTERCARD_MAESTRO() {
        val accountType1 = "MASTERCARD/MAESTRO"
        val previousTransfersInMonthInPennies1 = 100_000_00.0
        val transferAmountInPennies1 = 550_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )
    }

    @Test
    fun calculateCommission_VISA_MIR() {
        val accountType1 = "VISA/MIR"
        val previousTransfersInMonthInPennies1 = 10_000_00.0
        val transferAmountInPennies1 = 10_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        assertEquals(75.0, result, 0.00001)
    }

    @Test
    fun calculateCommission_VISA_MIR_MINIMUM_COMMISSION() {
        val accountType1 = "VISA/MIR"
        val previousTransfersInMonthInPennies1 = 10_000_00.0
        val transferAmountInPennies1 = 1_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        assertEquals(35.0, result, 0.00001)
    }

    @Test(expected = IllegalStateException::class)
    fun out_of_cards_limit_VISA_MIR() {
        val accountType1 = "VISA/MIR"
        val previousTransfersInMonthInPennies1 = 500_000_00.0
        val transferAmountInPennies1 = 150_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )
    }

    @Test
    fun calculateCommission_VK_PAY() {
        val accountType1 = "VK Pay"
        val previousTransfersInMonthInPennies1 = 10_000_00.0
        val transferAmountInPennies1 = 1_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        assertEquals(0.0, result, 0.00001)
    }

    @Test(expected = IllegalStateException::class)
    fun out_of_limit_VK_PAY() {
        val accountType1 = "VK Pay"
        val previousTransfersInMonthInPennies1 = 41_000_00.0
        val transferAmountInPennies1 = 1_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )
    }

    @Test(expected = IllegalStateException::class)
    fun illegal_pay_system() {
        val accountType1 = "Pay Pal"
        val transferAmountInPennies1 = 1_000_00.0

        val result = calculateCommission(
            accountType = accountType1,
            transferAmountInPennies = transferAmountInPennies1
        )
    }
}

