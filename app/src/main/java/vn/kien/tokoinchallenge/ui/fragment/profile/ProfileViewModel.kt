package vn.kien.tokoinchallenge.ui.fragment.profile

import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.model.User
import vn.kien.tokoinchallenge.ui.base.TokoinViewModel

class ProfileViewModel(private val appRepository: AppRepository): TokoinViewModel() {
    var user : User? get() = appRepository.user
        set(value) { appRepository.user = value }
}