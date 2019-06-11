package dsm.android.v3.presentation.viewModel.applyMusic

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import android.widget.Toast
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicModel
import dsm.android.v3.domain.repository.applyMusic.ApplyMusicRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent

class ApplyMusicViewModel(val applyMusicRepository: ApplyMusicRepository, val app: Application) : BaseViewModel(), LifecycleCallback {

    val introductionBaseString = "아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다."
    val actionMusicLogLiveEvent = SingleLiveEvent<Any>()
    val model = MutableLiveData<ApplyMusicModel>()
    val pageStatusLiveData = MutableLiveData<Int>()
    val dialogCallEvent = SingleLiveEvent<Any>()
    val weekLiveData = Transformations.map(pageStatusLiveData) {
        when (it) {
            0 -> "월요일 기상음악"
            1 -> "화요일 기상음악"
            2 -> "수요일 기상음악"
            3 -> "목요일 기상음악"
            4 -> "금요일 기상음악"
            else -> ""
        }
    }
    val introduceLiveData = Transformations.map(pageStatusLiveData) {
        when (it) {
            0 -> "월요일 $introductionBaseString"
            1 -> "화요일 $introductionBaseString"
            2 -> "수요일 $introductionBaseString"
            3 -> "목요일 $introductionBaseString"
            4 -> "금요일 $introductionBaseString"
            else -> ""
        }
    }

    val musicsLiveData = Transformations.map(pageStatusLiveData) {
        when (it) {
            0 -> model.value?.mon!!.addIfNotFive(emptyMusicDetailModel)
            1 -> model.value?.tue!!.addIfNotFive(emptyMusicDetailModel)
            2 -> model.value?.wed!!.addIfNotFive(emptyMusicDetailModel)
            3 -> model.value?.thu!!.addIfNotFive(emptyMusicDetailModel)
            4 -> model.value?.fri!!.addIfNotFive(emptyMusicDetailModel)
            else -> arrayListOf(emptyMusicDetailModel)
        }
    }

    var selectedIndex = MutableLiveData<Int?>()

    val emptyMusicDetailModel =
        ApplyMusicDetailModel(
            musicName = "신청곡이 없습니다.",
            singer = "눌러서 노래를 신청해주세요.",
            studentName = "신청없음",
            week = 0
        )
    val dataSetChangedLiveEvent = SingleLiveEvent<Any>()

    val inputMusicLiveData = MutableLiveData<String>()
    val inputArtistLiveData = MutableLiveData<String>()

    val inputMusicError = MutableLiveData<String>()
    val inputArtistError = MutableLiveData<String>()
    val fragmentDismissLiveEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                pageStatusLiveData.postValue(0)
                selectedIndex.postValue(null)
                getData()
            }
        }
    }

    fun actionMusicLogCall() = actionMusicLogLiveEvent.call()

    fun musicClicked(index: Int) {
        when {
            musicsLiveData.value!![index].id == "" -> dialogCallEvent.call()
            selectedIndex.value != index -> selectedIndex.value = index
            else -> selectedIndex.value = null
        }
        dataSetChangedLiveEvent.call()
        Log.d("SelectedIndex", "SelectedIndex: $selectedIndex")
    }

    fun cancelMusic() {
        add(applyMusicRepository.deleteMusic(hashMapOf("applyId" to musicsLiveData.value!![selectedIndex.value!!].id.toInt()))
            .subscribe({ response ->
                when (response.code()) {
                    200, 204 -> {
                        Toast.makeText(app.applicationContext, "성공적으로 취소되었습니다.", Toast.LENGTH_SHORT).show()
                        selectedIndex.postValue(null)
                        dataSetChangedLiveEvent.call()
                        getData()
                    }
                    403 ->{
                        selectedIndex.postValue(null)
                        dataSetChangedLiveEvent.call()
                        Toast.makeText(app.applicationContext, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }, {
                Toast.makeText(app.applicationContext, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }))
    }

    fun getData() {
        add(applyMusicRepository.getMusic()
            .subscribe({ response ->
                when (response.code()) {
                    200 -> {
                            for (model in response.body()!!.mon) {
                                model.week = 0
                                applyMusicRepository.saveMusic(model)
                            }
                        model.value = response.body()
                        pageStatusLiveData.value = pageStatusLiveData.value
                    }
                    204 -> {
                        model.value = ApplyMusicModel()
                        pageStatusLiveData.value = pageStatusLiveData.value
                    }
                }
            }, {
                Toast.makeText(app.applicationContext, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }))
    }

    fun applyMusic() {
        if (inputMusicLiveData.value.isNullOrBlank()) inputMusicError.value = "노래 제목을 입력해주세요."
        else inputMusicError.value = null
        if (inputArtistLiveData.value.isNullOrBlank()) inputArtistError.value = "아티스트를 입력해주세요."
        else inputArtistError.value = null
        if (inputMusicError.value.isNullOrBlank() and inputArtistError.value.isNullOrBlank()) {
            val map = hashMapOf(
                "singer" to inputArtistLiveData.value,
                "musicName" to inputMusicLiveData.value,
                "day" to pageStatusLiveData.value
            )
            add(applyMusicRepository.applyMusic(map)
                .subscribe({ response ->
                    when (response.code()) {
                        201 -> {
                            Toast.makeText(app.applicationContext, "기상음악 신청이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            fragmentDismissLiveEvent.call()
                            getData()
                        }
                        205 -> {
                            Toast.makeText(app.applicationContext, "기상음악 신청이 모두 완료되었어요 ㅠㅠ", Toast.LENGTH_SHORT).show()
                            fragmentDismissLiveEvent.call()
                            getData()
                        }
                        403 -> {
                            Toast.makeText(app.applicationContext, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            fragmentDismissLiveEvent.call()
                        }
                    }
                }, {
                    Toast.makeText(app.applicationContext, "인터넷이 원할하지 않거나 기상음악 신청이 모두 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                    fragmentDismissLiveEvent.call()
                    getData()
                }))
        }
    }

    fun ArrayList<ApplyMusicDetailModel>.addIfNotFive(model: ApplyMusicDetailModel): ArrayList<ApplyMusicDetailModel> {
        return if (size != 5) {
            add(model)
            this
        } else
            this
    }
}