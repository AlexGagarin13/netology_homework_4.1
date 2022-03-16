import org.junit.Test

import org.junit.Assert.*
import java.text.DecimalFormat

class MainKtTest {

    @Test
    fun calculateCommission_MASTERCARD_MAESTRO_with_commission() {
//        arrange
        val LIMIT_WITHOUT_COMMISSION_MASTERCARD_MAESTRO = 75_000_00.0
        val COMMISSION_MASTERCARD_MAESTRO = 0.006
        val COMMISSION_MASTERCARD_MAESTRO_FIX = 20_00.0
        val accountType1 = "MASTERCARD/MAESTRO"
        val previousTransfersInMonthInPennies1 = 66_000_00.0
        val transferAmountInPennies1 = 11_000_00.0
        val df = DecimalFormat("#.##")

//        act
        val result = calculateCommission(
            accountType = accountType1,
            previousTransfersInMonthInPennies = previousTransfersInMonthInPennies1,
            transferAmountInPennies = transferAmountInPennies1
        )

        //        assert
        assertEquals(86.0, result, 0.00001)
    }
}