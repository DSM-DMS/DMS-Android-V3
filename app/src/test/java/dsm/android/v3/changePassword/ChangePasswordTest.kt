package dsm.android.v3.changePassword

import android.arch.core.executor.testing.InstantTaskExecutorRule
import dsm.android.v3.ui.changePassword.ChangePasswordViewModel
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
        viewModel.originPassword.value = "asdfd"
        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdfdd"

        assertFalse(viewModel.statusMerger.value!!)
    }

    @Test
    fun whenOriginPasswordNotInput() {
        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdf"

        assertFalse(viewModel.statusMerger.value!!)
    }

    @Test
    fun whenCorrectInput() {
        viewModel.originPassword.value = "asdfd"
        viewModel.newPassword.value = "asdf"
        viewModel.confirmPassword.value = "asdf"

        assertTrue(viewModel.statusMerger.value!!)
    }
}