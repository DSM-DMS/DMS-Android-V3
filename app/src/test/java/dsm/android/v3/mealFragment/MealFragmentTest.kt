package dsm.android.v3.mealFragment

import android.arch.core.executor.testing.InstantTaskExecutorRule
import dsm.android.v3.model.MealModel
import dsm.android.v3.ui.main.meal.MealFragmentViewModel
import dsm.android.v3.util.testObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Assert.*
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat
import java.util.*

class MealFragmentTest {
    lateinit var viewModel: MealFragmentViewModel

    @Rule
    @JvmField
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = MealFragmentViewModel()
    }

    @Test
    fun `뷰페이저 하나 넘겨졌을 때 날짜, 요일 바뀌는지 테스트`() {
        val dateLiveData = viewModel.dateLiveData.testObserver()
        val weekLiveData = viewModel.weekLiveData.testObserver()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(calendar.time)
        val tomorrowWeek = SimpleDateFormat("EEEE", Locale.KOREA).format(calendar.time)

        viewModel.pageStatusLiveData.value = viewModel.pageStatusLiveData.value!! + 1

        assertEquals(dateLiveData.observedValues.last(), tomorrow)
        assertEquals(weekLiveData.observedValues.last(), tomorrowWeek)

    }

    @Test
    fun `pageStatusLiveData가 아이템의 개수보다 더 커지지 않는지 테스트`(){
        val pagestatusLiveData = viewModel.pageStatusLiveData.testObserver()
        val list = arrayListOf("수수밥", "맑은무채국", "비엔나푸실리볶음", "조기구이", "사과", "배추겉절이")
        val meal = arrayListOf(
            MealModel(list, list, list),
            MealModel(list, list, list),
            MealModel(list, list, list),
            MealModel(list, list, list)
        )
        viewModel.meals.value = meal

        viewModel.nextIndex()
        viewModel.nextIndex()
        viewModel.nextIndex()
        viewModel.nextIndex()
        viewModel.nextIndex()
        viewModel.nextIndex()

        assertEquals(3, pagestatusLiveData.observedValues.last())
    }
}