package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.*
import android.util.Log
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.ApplyMusicDetailModel
import dsm.android.v3.model.ApplyMusicModel
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyMusicViewModel : ViewModel(), LifecycleCallback {

    val toastLiveEvent = SingleLiveEvent<String>()

    val applyPagerModelLiveData = MutableLiveData<ArrayList<ApplyPagerModel>>()

    val actionMusicLogLiveEvent = SingleLiveEvent<Any>()
    val model = MutableLiveData<ApplyMusicModel>()
    val pageStatusLiveData = MutableLiveData<Int>()
    val dialogCallEvent = SingleLiveEvent<Any>()
    val weekLiveData: LiveData<String> = Transformations.map(pageStatusLiveData) {
        when (it) {
            0 -> "월요일 기상음악"
            1 -> "화요일 기상음악"
            2 -> "수요일 기상음악"
            3 -> "목요일 기상음악"
            4 -> "금요일 기상음악"
            else -> ""
        }
    }

    val introduceLiveData: LiveData<String> = Transformations.map(pageStatusLiveData) {
        when (it) {
            0 -> "월요일 $introductionBaseString"
            1 -> "화요일 $introductionBaseString"
            2 -> "수요일 $introductionBaseString"
            3 -> "목요일 $introductionBaseString"
            4 -> "금요일 $introductionBaseString"
            else -> ""
        }
    }

    val musicsLiveData: LiveData<ArrayList<ApplyMusicDetailModel>> = Transformations.map(pageStatusLiveData) {
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

    val dataSetChangedLiveEvent = SingleLiveEvent<Any>()

    val inputMusicLiveData = MutableLiveData<String>()
    val inputArtistLiveData = MutableLiveData<String>()

    val inputMusicError = MutableLiveData<String>()
    val inputArtistError = MutableLiveData<String>()
    val fragmentDismissLiveEvent = SingleLiveEvent<Any>()

    private val introductionBaseString =
        "아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다."
    private val emptyMusicDetailModel =
        ApplyMusicDetailModel(musicName = "신청곡이 없습니다.", singer = "눌러서 노래를 신청해주세요.", studentName = "신청없음")

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
        Connecter.api.deleteMusic(
            hashMapOf("applyId" to musicsLiveData.value!![selectedIndex.value!!].id.toInt())
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    200, 204 -> {
                        toastLiveEvent.postValue("성공적으로 취소되었습니다.")
                        selectedIndex.postValue(null)
                        dataSetChangedLiveEvent.call()
                        getData()
                    }
                    403 -> {
                        selectedIndex.postValue(null)
                        dataSetChangedLiveEvent.call()
                        toastLiveEvent.postValue("권한이 없습니다.")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) =
                toastLiveEvent.postValue("네트워크 상태를 확인해주세요.")
        })
    }

    fun getData() {
        Connecter.api.getMusic().enqueue(object : Callback<ApplyMusicModel> {
            override fun onResponse(call: Call<ApplyMusicModel>, response: Response<ApplyMusicModel>) =
                when (response.code()) {
                    200 -> {
                        model.value = response.body()
                        setApplyGoingData(response.body()!!)
                        pageStatusLiveData.value = pageStatusLiveData.value
                    }
                    204 -> {
                        model.value = ApplyMusicModel()
                        setApplyGoingData(response.body()!!)
                        pageStatusLiveData.value = pageStatusLiveData.value
                    }
                    else -> {
                    }
                }

            override fun onFailure(call: Call<ApplyMusicModel>, t: Throwable) =
                toastLiveEvent.postValue("네트워크 상태를 확인해주세요.")
        })
    }

    fun applyMusic() {
        if (inputMusicLiveData.value.isNullOrBlank())
            inputMusicError.value = "노래 제목을 입력해주세요."
        else inputMusicError.value = null
        if (inputArtistLiveData.value.isNullOrBlank())
            inputArtistError.value = "아티스트를 입력해주세요."
        else inputArtistError.value = null
        if (inputMusicError.value.isNullOrBlank() and inputArtistError.value.isNullOrBlank()) {
            val map = hashMapOf(
                "singer" to inputArtistLiveData.value,
                "musicName" to inputMusicLiveData.value,
                "day" to pageStatusLiveData.value
            )
            Connecter.api.applyMusic(map).enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    when (response.code()) {
                        201 -> {
                            toastLiveEvent.postValue("기상음악 신청이 완료되었습니다.")
                            fragmentDismissLiveEvent.call()
                            getData()
                        }
                        205 -> {
                            toastLiveEvent.postValue("기상음악 신청이 모두 완료되었어요 ㅠㅠ")
                            fragmentDismissLiveEvent.call()
                            getData()
                        }
                        403 -> {
                            toastLiveEvent.postValue("인터넷이 원할하지 않거나 기상음악 신청이 모두 완료되었습니다.")
                            fragmentDismissLiveEvent.call()
                        }
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    toastLiveEvent.postValue("인터넷이 원할하지 않거나 기상음악 신청이 모두 완료되었습니다.")
                    fragmentDismissLiveEvent.call()
                    getData()
                }
            })
        }
    }

    fun setApplyGoingData(applyMusicModel: ApplyMusicModel) {
        applyPagerModelLiveData.value = arrayListOf(
            ApplyPagerModel(
                "월요일",
                "월요일 아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다.",
                applyMusicModel.mon.size
            ),
            ApplyPagerModel(
                "화요일",
                "화요일 아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다.",
                applyMusicModel.tue.size
            ),
            ApplyPagerModel(
                "수요일",
                "수요일 아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다.",
                applyMusicModel.wed.size
            ),
            ApplyPagerModel(
                "목요일",
                "목요일 아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다.",
                applyMusicModel.thu.size
            ),
            ApplyPagerModel(
                "금요일",
                "금요일 아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다.",
                applyMusicModel.fri.size
            )
        )
    }

    private fun ArrayList<ApplyMusicDetailModel>.addIfNotFive(model: ApplyMusicDetailModel): ArrayList<ApplyMusicDetailModel> =
        if (size != 5) {
            add(model)
            this
        } else this
}