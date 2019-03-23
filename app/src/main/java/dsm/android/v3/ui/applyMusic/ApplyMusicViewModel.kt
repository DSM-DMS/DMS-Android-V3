package dsm.android.v3.ui.applyMusic

import android.app.Application
import android.arch.lifecycle.*
import android.widget.Toast
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.ApplyMusicDetailModel
import dsm.android.v3.model.ApplyMusicModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyMusicViewModel(val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val introductionBaseString = "아침 기상 시에 나올 노래를 신청받습니다. 한 사람당 한 곡만 신청이 가능하며 적절하지 않은 노래나 부적절한 가사가 포함된 노래는 반려될 수 있습니다."
    val actionMusicLogLiveEvent = SingleLiveEvent<Any>()
    val model = MutableLiveData<ApplyMusicModel>()
    val pageStatusLiveData = MutableLiveData<Int>()
    val firstMusicIsClicked = MutableLiveData<Boolean>().apply { value = false }
    val secondMusicIsClicked = MutableLiveData<Boolean>().apply { value = false }
    val dialogCallEvent = SingleLiveEvent<Any>()
    val isMusicSelected = MediatorLiveData<Boolean>().apply {
        addSource(firstMusicIsClicked) {
            value = it
        }
        addSource(secondMusicIsClicked) {
            value = it
        }
    }
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
            0 -> model.value?.mon
            1 -> model.value?.tue
            2 -> model.value?.wed
            3 -> model.value?.thu
            4 -> model.value?.fri
            else -> ArrayList()
        }
    }

    val firstMusicLiveData =
        Transformations.map(musicsLiveData) { if (it!!.size >= 1) it[0].musicName else "신청곡이 없습니다." }
    val firstSingerLiveData =
        Transformations.map(musicsLiveData) { if (it!!.size >= 1) it[0].singer else "눌러서 노래를 신청해주세요." }
    val firstStudentLiveData = Transformations.map(musicsLiveData) { if (it!!.size >= 1) it[0].studentName else "신청없음" }

    val secondMusicLiveData =
        Transformations.map(musicsLiveData) { if (it!!.size >= 2) it[1].musicName else "신청곡이 없습니다." }
    val secondSingerLiveData =
        Transformations.map(musicsLiveData) { if (it!!.size >= 2) it[1].singer else "눌러서 노래를 신청해주세요." }
    val secondStudentLiveData =
        Transformations.map(musicsLiveData) { if (it!!.size >= 2) it[1].studentName else "신청없음" }

    val inputMusicLiveData = MutableLiveData<String>()
    val inputArtistLiveData = MutableLiveData<String>()
    val fragmentDismissLiveEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                pageStatusLiveData.postValue(0)
                firstMusicIsClicked.postValue(false)
                secondMusicIsClicked.postValue(false)
                getData()
            }
        }
    }

    fun actionMusicLogCall() = actionMusicLogLiveEvent.call()

    fun firstMusicClicked() {
        if (musicsLiveData.value!!.size == 0) {
            dialogCallEvent.call()
        } else if (firstMusicIsClicked.value == false) {
            if (secondMusicIsClicked.value == true) {
                secondMusicIsClicked.value = false
                firstMusicIsClicked.value = true
            } else {
                firstMusicIsClicked.value = true
            }
        } else
            firstMusicIsClicked.value = false
    }

    fun secondMusicClicked() {
        if (musicsLiveData.value!!.size <= 1) {
            dialogCallEvent.call()
        } else if (secondMusicIsClicked.value == false) {
            if (firstMusicIsClicked.value == true) {
                firstMusicIsClicked.value = false
                secondMusicIsClicked.value = true
            } else {
                secondMusicIsClicked.value = true
            }
        } else
            secondMusicIsClicked.value = false
    }

    fun cancelMusic() {
        if (firstMusicIsClicked.value!!) {
            Connecter.api.deleteMusic(
                getToken(app.applicationContext),
                hashMapOf("applyId" to musicsLiveData.value!![0].id.toInt())
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when (response.code()) {
                        200, 204 -> {
                            Toast.makeText(app.applicationContext, "성공적으로 취소되었습니다.", Toast.LENGTH_SHORT).show()
                            getData()
                            firstMusicClicked()
                        }
                        403 -> Toast.makeText(app.applicationContext, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(app.applicationContext, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

            })
        } else if (secondMusicIsClicked.value!!) {
            Connecter.api.deleteMusic(
                getToken(app.applicationContext),
                hashMapOf("applyId" to musicsLiveData.value!![1].id.toInt())
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when (response.code()) {
                        200, 204 -> {
                            Toast.makeText(app.applicationContext, "성공적으로 취소되었습니다.", Toast.LENGTH_SHORT).show()
                            getData()
                            secondMusicClicked()
                        }
                        403 -> Toast.makeText(app.applicationContext, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(app.applicationContext, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    fun getData() {
        Connecter.api.getMusic(getToken(app.applicationContext)).enqueue(object : Callback<ApplyMusicModel> {
            override fun onResponse(call: Call<ApplyMusicModel>, response: Response<ApplyMusicModel>) {
                when (response.code()) {
                    200 -> {
                        model.value = response.body()
                        pageStatusLiveData.value = pageStatusLiveData.value
                    }
                    204 ->
                        model.value = ApplyMusicModel()
                }
            }

            override fun onFailure(call: Call<ApplyMusicModel>, t: Throwable) {
                Toast.makeText(app.applicationContext, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun applyMusic() {
        val map = hashMapOf(
            "singer" to inputArtistLiveData.value,
            "musicName" to inputMusicLiveData.value,
            "day" to pageStatusLiveData.value
        )
        Connecter.api.applyMusic(getToken(app.applicationContext), map).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
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
                    400 -> {
                        Toast.makeText(app.applicationContext, "공란을 모두 채워주세요", Toast.LENGTH_SHORT).show()
                        fragmentDismissLiveEvent.call()
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(app.applicationContext, "네트워크 연결이 원할하지 않습니다.", Toast.LENGTH_SHORT).show()
                fragmentDismissLiveEvent.call()
            }

        })
    }
}