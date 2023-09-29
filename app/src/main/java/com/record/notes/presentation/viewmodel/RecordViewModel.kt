package com.record.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.record.notes.domain.use_case.RecordUseCase
import com.record.notes.presentation.state.RecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class RecordViewModel @Inject constructor(private val recordUseCase: RecordUseCase) :
//    ViewModel() {
//
//    private var _subjectList = mutableStateOf(RecordState())
//    val subjectList: State<RecordState> get() = _subjectList
//
//    fun getSubject() {
//        subjectUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _subjectList.value = SubjectState(isLoading = true)
//                }
//
//                is Resource.Success -> {
////                    _subjectList.value = SubjectState(isData = it.data)
//                }
//
//                is Resource.Error -> {
//                    _subjectList.value = SubjectState(isError = it.message.toString())
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

//    fun insertSubject(subjectEntity: List<SubjectEntity>) {
//        subjectUseCase(listOfSubject = subjectEntity).launchIn(viewModelScope)
//    }
//
//    fun deleteSubject(id: Int) {
//        subjectUseCase(id).launchIn(viewModelScope)
//    }
//}

//fun insertSubject(
//    photoUrl: String?,
//    name: String?,
//    description: String?,
//    isIvy: Boolean?
//) {
//    getSubjectUseCase(photoUrl, name, description, isIvy).onEach {
//        when (it) {
//            is Resource.Loading -> {
//                _subjectList.value = SubjectState(isLoading = true)
//            }
//
//            is Resource.Success -> {
//                _subjectList.value = SubjectState(isData = it.data)
//            }
//
//            is Resource.Error -> {
//                _subjectList.value = SubjectState(isError = it.message.toString())
//            }
//        }
//    }.launchIn(viewModelScope)
//}
//
//
//fun deleteSubject(id: Int) {
//    getSubjectUseCase(id).onEach {
//        when (it) {
//            is Resource.Loading -> {
//                _subjectList.value = SubjectState(isLoading = true)
//            }
//
//            is Resource.Success -> {
//                _subjectList.value = SubjectState(isSuccess = true)
//            }
//
//            is Resource.Error -> {
//                _subjectList.value = SubjectState(isError = it.message.toString())
//            }
//        }
//    }.launchIn(viewModelScope)
//}