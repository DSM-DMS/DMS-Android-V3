package dsm.android.v3.changePassword

import android.arch.core.executor.testing.InstantTaskExecutorRule
import dsm.android.v3.ui.changePassword.ChangePasswordViewModel
import dsm.android.v3.util.testObserver
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.MockitoAnnotations


class ChangePasswordTest {
    lateinit var viewModel: ChangePasswordViewModel

    @Rule
    @JvmField
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = ChangePasswordViewModel()
    }

    @Test
    fun `1) 새로운 패스워드랑 패스워드 확인이랑 다를 때`() {
        val inputStatus = viewModel.inputStatus.testObserver()

        viewModel.currentPassword.value = "asdfd"
        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdfdd"

        assertFalse(inputStatus.observedValues.last()!!)
    }

    @Test
    fun `2) 기존 비밀번호 입력 안 했을때`() {
        val inputStatus = viewModel.inputStatus.testObserver()

        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdf"

        assertFalse(inputStatus.observedValues.last()!!)
    }

    @Test
    fun `3) 입력을 올바르게 잘 했을 때`() {
        val inputStatus = viewModel.inputStatus.testObserver()

        viewModel.currentPassword.value = "asdfd"
        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdf"

        assertTrue(inputStatus.observedValues.last()!!)
    }


}